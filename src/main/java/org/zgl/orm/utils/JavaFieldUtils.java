package org.zgl.orm.utils;

import org.zgl.orm.bean.ColumnInfo;
import org.zgl.orm.bean.JavaFieldGetSet;
import org.zgl.orm.bean.TableInfo;
import org.zgl.orm.core.DBManager;
import org.zgl.orm.core.MySqlTypeConvertor;
import org.zgl.orm.core.TableContext;
import org.zgl.orm.core.TypeConvertor;
import org.zgl.utils.StringUtils;
import org.zgl.utils.logger.LoggerUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaFieldUtils {
    /**
     * 根据字段信息生java源码 如 varchar ---> private String name;和相应的get set方法
     * @param columnInfo 字段信息
     * @param convertor 字段转换器
     * @return java属性和getset方法
     */
    public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo columnInfo, TypeConvertor convertor){
        JavaFieldGetSet jfjs = new JavaFieldGetSet();
        String javaFieldType = convertor.databaseType2JavaType(columnInfo.getDataType());
        jfjs.setFieldInfo("\tprivate "+javaFieldType+" "+columnInfo.getName()+";\n");

        //get源码
        StringBuilder getSrc = new StringBuilder();
        getSrc.append("\tpublic "+javaFieldType+" get"+StringUtils.firstChar2UpperCase(columnInfo.getName())+"(){\n");
        getSrc.append("\t\treturn "+columnInfo.getName()+";\n");
        getSrc.append("\t}\n");
        jfjs.setGetInfo(getSrc.toString());

        //set源码
        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic void set"+StringUtils.firstChar2UpperCase(columnInfo.getName())+"(");
        setSrc.append(javaFieldType + " "+columnInfo.getName()+"){\n");
        setSrc.append("\t\tthis. "+columnInfo.getName()+" = "+columnInfo.getName()+";\n");
        setSrc.append("\t}\n");
        jfjs.setSetInfo(setSrc.toString());
        return jfjs;
    }

    /**
     * 根据表信息生成源码
     * @param tableInfo
     * @param convertor
     * @return
     */
    public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor){
        Map<String,ColumnInfo> colums = tableInfo.getColumns();
        List<JavaFieldGetSet> javaFields = new ArrayList<>();
        for(ColumnInfo c:colums.values()){
            javaFields.add(createFieldGetSetSRC(c,convertor));
        }
        StringBuilder src = new StringBuilder();
        src.append("package "+ DBManager.getConfig().getPoPackage()+";\n\n");
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n");
        src.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+" {\n\n");

        for(JavaFieldGetSet f:javaFields){
            src.append(f.getFieldInfo());
        }
        src.append("\n");
        for(JavaFieldGetSet f:javaFields){
            src.append(f.getGetInfo());
            src.append(f.getSetInfo());
        }
        src.append("}\n");
        return src.toString();
    }
    public static void createJavaPOFieldInfo(TableInfo tableInfo,TypeConvertor convertor){
        String src = createJavaSrc(tableInfo,convertor);
        String srcPath = DBManager.getConfig().getSrcPath()+"//";
        String packagePath = DBManager.getConfig().getPoPackage().replaceAll("\\.","//");
        File f = new File(srcPath+packagePath);
        if(f.exists()){//指定目录不存在 创建目录
            f.mkdirs();
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"//"+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java"));
            bw.write(src);
            LoggerUtils.getPlatformLog().info("建立表"+tableInfo.getTname()+"对应的java类"+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println(e);
                LoggerUtils.getPlatformLog().error("关闭bw流时异常",e);
            }
        }
    }
    public static void main(String[] args) {
//        ColumnInfo ci = new ColumnInfo("username","varchar",0);
//        JavaFieldGetSet javaFieldGetSet = createFieldGetSetSRC(ci,new MySqlTypeConvertor());
//        System.out.println(javaFieldGetSet);
        Map<String,TableInfo> map = TableContext.tables;
        for(TableInfo t:map.values()) {
            createJavaPOFieldInfo(t, new MySqlTypeConvertor());
        }
    }
}
