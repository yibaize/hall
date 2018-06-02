package org.zgl.logic.login.logic;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.Db_user;
import org.zgl.player.LoginDto;
import org.zgl.player.PlayerInit;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

@Protocol("2")
public class LoginHandler_2 extends OperateCommandAbstract {
    private final String password;

    public LoginHandler_2(String account, String password) {
        super(account);
        this.password = password;
    }

    @Override
    public Object execute() {
        if(SessionManager.isOnlinePlayer(getAccount()))
            new GenaryAppError(AppErrorCode.LOGIN_ERR);
        //查找用户是否存在
        Query query = QueryFactory.createQuery();
        Db_user u = (Db_user) query.queryUniqueRow("SELECT * FROM db_user WHERE account=?",Db_user.class,new Object[]{getAccount()});
        if(u == null || !u.getPassword().equals(password))
            new GenaryAppError(AppErrorCode.PASSWORD_ERR);
        UserMap um = PlayerInit.initUserMap(u);
        //绑定链接
        SessionManager.putSession(um.getAccount(),um);
        return new LoginDto().getLoginDto(um);
    }
}
