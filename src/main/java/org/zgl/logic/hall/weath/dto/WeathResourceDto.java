package org.zgl.logic.hall.weath.dto;


import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class WeathResourceDto {
    private long gold;
    private long diamond;
    private long integral;

    public WeathResourceDto() {
    }

    public WeathResourceDto(long gold, long diamond, long integral) {
        this.gold = gold;
        this.diamond = diamond;
        this.integral = integral;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getDiamond() {
        return diamond;
    }

    public void setDiamond(long diamond) {
        this.diamond = diamond;
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }
}
