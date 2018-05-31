package org.zgl.logic.hall.ranking;

import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.logic.login.po.SQLUserBaseInfo;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class RankingCharactorPlayerDto {
    private String userName;
    private String headIcon;
    private int vipLv;
    private int autoId;
    private long charactorNum;
    private long giftNum;
    private int id;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public int getVipLv() {
        return vipLv;
    }

    public void setVipLv(int vipLv) {
        this.vipLv = vipLv;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public long getCharactorNum() {
        return charactorNum;
    }

    public void setCharactorNum(long charactorNum) {
        this.charactorNum = charactorNum;
    }

    public long getGiftNum() {
        return giftNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGiftNum(long giftNum) {
        this.giftNum = giftNum;
    }
    public RankingCharactorPlayerDto dto(UserMap userMap){
        SQLWeathModel weath = userMap.getWeath();
        this.autoId = weath.getAuto();
        this.vipLv = weath.getVipLv();
        this.charactorNum = weath.getCharacterNum();
        this.giftNum = weath.getGiftNum();

        SQLUserBaseInfo base = userMap.getBaseInfo();
        this.headIcon = base.getHeadIcon();
        this.userName = base.getUserName();
        this.id = userMap.getId();
        return this;
    }
}
