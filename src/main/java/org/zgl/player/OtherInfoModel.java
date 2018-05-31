package org.zgl.player;


import org.zgl.logic.hall.frineds.dto.FriendBaseInfoDto;
import org.zgl.logic.hall.weath.po.ResourceModel;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.logic.login.po.SQLUserBaseInfo;
import org.zgl.utils.builder_clazz.ann.Protostuff;

import java.util.List;
@Protostuff
public class OtherInfoModel {
    private int id;
    private String account;
    private String userName;
    private String headIcon;
    private int vipLv;
    private String gender;
    private long gold;
    private long diamond;
    private long integral;
    /**个人签名*/
    private String signAture;
    /**联系方式*/
    private String relation;
    /**座驾*/
    private List<ResourceModel> autos;
    /**礼物*/
    private List<ResourceModel> gifts;
    /**道具*/
    private List<ResourceModel> props;
    /**负债*/
    private long blood;

    public OtherInfoModel() {
    }
    public OtherInfoModel(UserMap um) {
        SQLUserBaseInfo baseInfo = um.getBaseInfo();
        SQLWeathModel weath = um.getWeath();
        this.id = um.getId();
        this.account = um.getAccount();
        this.userName = baseInfo.getUserName();
        this.gender = baseInfo.getGender();
        this.headIcon = baseInfo.getHeadIcon();
        this.relation = baseInfo.getRelation();
        this.signAture = baseInfo.getSignAture();
        this.gold = weath.getGold();
        this.integral = weath.getIntegral();
        this.diamond = weath.getDiamond();
        this.vipLv = weath.getVipLv();
        this.autos = weath.getAutos();
        this.gifts = weath.getGifts();
        this.props = weath.getProps();
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getSignAture() {
        return signAture;
    }

    public void setSignAture(String signAture) {
        this.signAture = signAture;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<ResourceModel> getAutos() {
        return autos;
    }

    public void setAutos(List<ResourceModel> autos) {
        this.autos = autos;
    }

    public List<ResourceModel> getGifts() {
        return gifts;
    }

    public void setGifts(List<ResourceModel> gifts) {
        this.gifts = gifts;
    }

    public List<ResourceModel> getProps() {
        return props;
    }

    public void setProps(List<ResourceModel> props) {
        this.props = props;
    }

    public long getBlood() {
        return blood;
    }

    public void setBlood(long blood) {
        this.blood = blood;
    }

    public FriendBaseInfoDto baseInfoDto(){
        FriendBaseInfoDto dto = new FriendBaseInfoDto();
        dto.setAccount(account);
        dto.setSignAture(signAture);
        dto.setGender(gender);
        dto.setUserName(userName);
        dto.setVipLv(vipLv);
        dto.setBlood(blood);
        return dto;
    }
}
