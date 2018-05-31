package org.zgl.logic.login.po;

public class SQLUserBaseInfo {
    private int loginType;
    private String userName;
    private String headIcon;
    private String gender;
    /**个人签名*/
    private String signAture;
    /**联系方式*/
    private String relation;
    /**金币排行*/
    private int goldRanking;
    /**人品排行*/
    private int characterRanking;
    public SQLUserBaseInfo() {
    }

    public SQLUserBaseInfo(int loginType, String userName, String headIcon, String gender, String signAture, String relation, int goldRanking, int characterRanking) {
        this.loginType = loginType;
        this.userName = userName;
        this.headIcon = headIcon;
        this.gender = gender;
        this.signAture = signAture;
        this.relation = relation;
        this.goldRanking = goldRanking;
        this.characterRanking = characterRanking;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
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

    public int getGoldRanking() {
        return goldRanking;
    }

    public void setGoldRanking(int goldRanking) {
        this.goldRanking = goldRanking;
    }

    public int getCharacterRanking() {
        return characterRanking;
    }

    public void setCharacterRanking(int characterRanking) {
        this.characterRanking = characterRanking;
    }
}
