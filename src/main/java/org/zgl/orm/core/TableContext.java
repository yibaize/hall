package org.zgl.orm.core;


import org.zgl.orm.bean.ColumnInfo;
import org.zgl.orm.bean.TableInfo;
import org.zgl.orm.utils.JavaFieldUtils;
import org.zgl.utils.StringUtils;
import org.zgl.utils.logger.LoggerUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableContext {
    public static Map<String,TableInfo> tables = new HashMap<>();
    public static Map<Class,TableInfo> poClassTableMap = new HashMap<>();

    private TableContext() {
    }
    static {
        //加载表信息
        try {
            Connection conn = DBManager.getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet tableSet = dbmd.getTables(null,null,"%",null);
            while (tableSet.next()){
                String tableName = (String)tableSet.getObject("TABLE_NAME");
                TableInfo ti = new TableInfo(tableName,new HashMap<String, ColumnInfo>(),new ArrayList<ColumnInfo>());

                tables.put(tableName,ti);

                ResultSet set = dbmd.getColumns(null,"%",tableName,"%");//表中所有字段
                while (set.next()){
                    ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0);

                    ti.getColumns().put(set.getString("COLUMN_NAME"),ci);

                }
                ResultSet set1 = dbmd.getPrimaryKeys(null,"%",tableName);//表中所有主键

                while (set1.next()){
                    ColumnInfo ci = ti.getColumns().get(set1.getObject("COLUMN_NAME"));
                    ci.setKeyType(1);

                    ti.getPriKeys().add(ci);

                }
                if(ti.getPriKeys().size()>0){
                    //设置主键
                    ti.setOnlyPriKey(ti.getPriKeys().get(0));
                }
            }
        }catch (Exception e){
            LoggerUtils.getPlatformLog().error("负责管理数据库并生成类结构",e);
        }
        //更新类结构
//        updateJavaPOField();
    }
    public static void updateJavaPOField(){
        Map<String,TableInfo> map = TableContext .tables;
        for(TableInfo t:map.values()) {
            JavaFieldUtils.createJavaPOFieldInfo(t, new MySqlTypeConvertor());
        }
    }

    /**
     * 加载po包下的类
     */
    public static void loadPOTables(){
        for(TableInfo t:tables.values()){
            try {
                Class c = Class.forName(DBManager.getConfig().getPoPackage()+"."
                        + StringUtils.firstChar2UpperCase(t.getTname()));
                poClassTableMap.put(c,t);
            } catch (ClassNotFoundException e) {
                LoggerUtils.getPlatformLog().error("加载po包下的类",e);
            }
        }
    }
}