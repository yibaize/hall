package org.zgl.logic.login.logic;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.login.dto.LoginCmdBody;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.AdminInfo;
import org.zgl.orm.po.User;
import org.zgl.player.LoginDto;
import org.zgl.player.PlayerInit;
import org.zgl.player.UserMap;
import org.zgl.utils.DateUtils;
import org.zgl.utils.builder_clazz.ann.Protocol;

import java.sql.Date;

/**
 * 登陆
 */
@Protocol("1")
public class LoginHandler extends OperateCommandAbstract {
    /**登陆类型*/
    private final int loginType;
    private final String password;
    private final String username;
    /**头像*/
    private final String headIcon;
    /**性别*/
    private final String gender;

    public LoginHandler(int loginType, String account, String password, String username, String headIcon, String gender) {
        super(account);
        this.loginType = loginType;
        this.password = password;
        this.username = username;
        this.headIcon = headIcon;
        this.gender = gender;
    }

    @Override
    public Object execute() {
        if(SessionManager.isOnlinePlayer(getAccount()))
            new GenaryAppError(AppErrorCode.LOGIN_ERR);
        LoginCmdBody cmdBody = new LoginCmdBody();
        cmdBody.setLoginType(loginType);
        cmdBody.setAccount(getAccount());
        cmdBody.setHeadIcon(headIcon);
        cmdBody.setPassword(password);
        cmdBody.setUsername(username);
        cmdBody.setGender(gender);
        Query query = QueryFactory.createQuery();
        //查找用户是否存在
        User u = (User) query.queryUniqueRow("SELECT * FROM user WHERE account=?",User.class,new Object[]{getAccount()});
        if(u == null){
            Date date = new Date(new java.util.Date().getTime());
            AdminInfo adminInfo = (AdminInfo) query.queryUniqueRow("SELECT * FROM adminInfo WHERE timer=?",AdminInfo.class,new Object[]{date});
            if(adminInfo == null){
                adminInfo = new AdminInfo();
                adminInfo.setTimer(date);
                adminInfo.setRegistNum(1);
                query.insert(adminInfo);
            }
            adminInfo.setRegistId(getAccount());
            adminInfo.setRegistNum(adminInfo.getRegistNum() + 1);
            query.update(adminInfo,new String[]{"registId","registNum"});
            u = PlayerInit.getUserInitInfo(cmdBody);
            query.insert(u);//插入数据库
        }
        UserMap um = PlayerInit.initUserMap(u);
        //绑定链接
        SessionManager.putSession(getAccount(),um);
        return new LoginDto().getLoginDto(um);
    }
}
