package org.zgl.logic.hall.giftBag.data;

import org.zgl.utils.StringUtils;
import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.ann.OverlookField;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;

import java.util.ArrayList;
import java.util.List;

@DataTable
public class GiftBagAwardDataTable implements DataTableMessage {
    private final int id;
    /**描述*/
    private final String describe;
    /**初级场次*/
    private final int primary;
    /**中级场次*/
    private final int intermediate;
    /**高级场次*/
    private final int advanced;
    @OverlookField("注释不生成")
    private final List<GiftBagAwardModel> award;
    public static GiftBagAwardDataTable get(int id){
        return StaticConfigMessage.getInstance().get(GiftBagAwardDataTable.class,id);
    }

    public GiftBagAwardDataTable() {
        this.id = 0;
        this.award = null;
        this.primary = 0;
        this.describe = "";
        this.intermediate = 0;
        this.advanced = 0;
    }

    public String getDescribe() {
        return describe;
    }

    public int getPrimary() {
        return primary;
    }

    public int getIntermediate() {
        return intermediate;
    }

    public int getAdvanced() {
        return advanced;
    }

    public int getId() {
        return id;
    }

    public List<GiftBagAwardModel> getAward() {
        return award;
    }

    private List<GiftBagAwardModel> award4Init(String value){
        String[] str = StringUtils.split(value,";");
        List<GiftBagAwardModel> obj = new ArrayList<>(8);
        for(int i = 0;i<str.length;i++){
            String[] str1 = StringUtils.split(str[i],",");
            obj.add(new GiftBagAwardModel(Integer.parseInt(str1[0]),Integer.parseInt(str1[1]),Integer.parseInt(str1[2])));
        }
        return obj;
    }
    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }

    public static void main(String[] args) {
        String str = "1,2,3;4,5,6;7,8,9";
        String[] str1 = StringUtils.split(str,",");
    }
}
