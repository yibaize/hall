package org.zgl.logic.hall.weath.dto;


import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class MoneyTreeDto {
    /**摇钱树等级*/
    private int lv;
    /**多久未领取*/
    private int timer;
    /**可以领取的金额*/
    private int goldNum;

    public MoneyTreeDto() {
    }

    public MoneyTreeDto(int lv, int timer, int goldNum) {
        this.lv = lv;
        this.timer = timer;
        this.goldNum = goldNum;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(int goldNum) {
        this.goldNum = goldNum;
    }
}
