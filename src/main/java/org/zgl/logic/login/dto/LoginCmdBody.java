package org.zgl.logic.login.dto;

public class LoginCmdBody {
    private int loginType;
    private String account;
    private String password;
    private String username;
    private String headIcon;
    private String gender;
    public LoginCmdBody() {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    @Override
    public String toString() {
        return "LoginCmdBody{" +
                "loginType=" + loginType +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
