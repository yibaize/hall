package org.zgl.logic.hall.siginin.po;


public class SQLSignInModel {
    /**签到第几天*/
    private int signDay;
    /**上次签到时间*/
    private int signInTime;

    private int onlineAwardDay;
    /**在线抽奖次数*/
    private int onlineAwardNum;
    /**在线抽奖上次抽奖时间*/
    private long onlineAwardTimer;

    /**首充奖励领取第几天*/
    private int firstBayDay;
    /**首充奖励当天是否已经领取*/
    private boolean hasFirstBay;

    /**幸运大转盘剩余次数*/
    private int dialNum;
    public SQLSignInModel() {
    }

    public SQLSignInModel(int signDay, int singInTime) {
        this.signDay = signDay;
        this.signInTime = singInTime;
    }

    public int getSignDay() {
        return signDay;
    }

    public void setSignDay(int signDay) {
        this.signDay = signDay;
    }

    public int getSignInTime() {
        return signInTime;
    }

    public int getOnlineAwardNum() {
        return onlineAwardNum;
    }

    public void setOnlineAwardNum(int onlineAwardNum) {
        this.onlineAwardNum = onlineAwardNum;
    }

    public long getOnlineAwardTimer() {
        return onlineAwardTimer;
    }

    public void setOnlineAwardTimer(long onlineAwardTimer) {
        this.onlineAwardTimer = onlineAwardTimer;
    }

    public void setSignInTime(int signInTime) {
        this.signInTime = signInTime;
    }

    public int getFirstBayDay() {
        return firstBayDay;
    }

    public void setFirstBayDay(int firstBayDay) {
        this.firstBayDay = firstBayDay;
    }

    public boolean isHasFirstBay() {
        return hasFirstBay;
    }

    public void setHasFirstBay(boolean hasFirstBay) {
        this.hasFirstBay = hasFirstBay;
    }

    public int getDialNum() {
        return dialNum;
    }

    public void setDialNum(int dialNum) {
        this.dialNum = dialNum;
    }

    public int getOnlineAwardDay() {
        return onlineAwardDay;
    }

    public void setOnlineAwardDay(int onlineAwardDay) {
        this.onlineAwardDay = onlineAwardDay;
    }
}
