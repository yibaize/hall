package org.zgl.logic.hall.siginin.data;

import org.zgl.utils.StringUtils;
import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;

import java.util.ArrayList;
import java.util.List;

@DataTable
public class FirstBuyDataTable implements DataTableMessage {
    /**id*/
    private final int id;
    /**赠送积分*/
    private final int integral;
    private final List<FirstBuyAwardDataTable> award;
    public FirstBuyDataTable() {
        this.id = 0;
        this.integral = 0;
        this.award = null;

    }
    public static FirstBuyDataTable get(int id){
        return StaticConfigMessage.getInstance().get(FirstBuyDataTable.class,id);
    }

    public int getId() {
        return id;
    }

    public int getIntegral() {
        return integral;
    }

    public List<FirstBuyAwardDataTable> getAward() {
        return award;
    }
    private List<FirstBuyAwardDataTable> award4Init(String value){
        String[] str1 = StringUtils.split(value,";");
        List<FirstBuyAwardDataTable> list = new ArrayList<>(2);
        for(int i = 0;i<str1.length;i++){
            String[] str2 = StringUtils.split(str1[i],",");
            list.add(new FirstBuyAwardDataTable(Integer.parseInt(str2[0]),Integer.parseInt(str2[1]),Integer.parseInt(str2[2])));
        }
        return list;
    }
    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
