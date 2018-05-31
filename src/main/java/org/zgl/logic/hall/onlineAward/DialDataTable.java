package org.zgl.logic.hall.onlineAward;

import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;
import org.zgl.utils.weightRandom.IWeihtRandom;

@DataTable
public class DialDataTable implements DataTableMessage,IWeihtRandom {
    /**id*/
    private final int id;
    /**奖品id*/
    private final int awardId;
    /**奖品数量*/
    private final int count;
    /**中奖概率*/
    private final int probability;
    /**奖品类型*/
    private final int awardType;
    public DialDataTable() {
        this.id = 0;
        this.awardId = 0;
        this.count = 0;
        this.probability = 0;
        this.awardType = 0;
    }
    public static DialDataTable get(int id){
        return StaticConfigMessage.getInstance().get(DialDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public int getAwardId() {
        return awardId;
    }

    public int getCount() {
        return count;
    }

    public int getProbability() {
        return probability;
    }

    public int getAwardType() {
        return awardType;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public int elementId() {
        return id;
    }

    @Override
    public int probability() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
