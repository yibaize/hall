package org.zgl.logic.hall.weath.dto;


import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class RoomWeathDto {
    private String account;
    private long gold;
    private long diamond;
    private long integral;
    /**是否赢了*/
    private boolean hasWin;
    /**赢钱的牌类型类型*/
    private int winCardType;
    public RoomWeathDto() {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isHasWin() {
        return hasWin;
    }

    public void setHasWin(boolean hasWin) {
        this.hasWin = hasWin;
    }

    public int getWinCardType() {
        return winCardType;
    }

    public void setWinCardType(int winCardType) {
        this.winCardType = winCardType;
    }
}