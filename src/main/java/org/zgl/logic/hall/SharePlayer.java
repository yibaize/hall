package org.zgl.logic.hall;


import org.zgl.player.UserMap;

/**
 * 场景公用玩家信息
 */
public class SharePlayer {
    private int id;
    /**当前在那个场景*/
    private int inScenes;
    private String account;
    private String userName;
    private String headIcon;
    private int vip;
    private long gold;
    private long diamond;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getInScenes() {
        return inScenes;
    }

    public void setInScenes(int inScenes) {
        this.inScenes = inScenes;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
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
    public SharePlayer getSharePlayerInit(int scenes,UserMap u){
        this.setInScenes(scenes);
        this.setAccount(u.getAccount());
        this.setUserName(u.getBaseInfo().getUserName());
//        this.setDiamond(u.getDiamond());
//        this.setGold(u.getGold());
//        this.setVip(u.getVip());
        this.setHeadIcon(u.getBaseInfo().getHeadIcon());
        this.setId(u.getId());
        return this;
    }
}
