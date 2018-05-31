package org.zgl.logic.hall.giftBag.po;

import org.zgl.utils.DateUtils;

public class SQLGiftBagModel {
    /**登陆第几天*/
    private int day;
    /**上次登陆时间*/
    private int time;
    /**初级场*/
    private int primary;
    /**中级场*/
    private int intermedite;
    /**高级场*/
    private int advanced;
    public SQLGiftBagModel() {
    }
    public int getPrimary() {
        return primary;
    }

    /**
     * 奖励是否已经领取
     * @return
     */
    public boolean isHasGet() {
        return time == DateUtils.currentDay();
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public int getIntermedite() {
        return intermedite;
    }

    public void setIntermedite(int intermedite) {
        this.intermedite = intermedite;
    }

    public int getAdvanced() {
        return advanced;
    }

    public void setAdvanced(int advanced) {
        this.advanced = advanced;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
