package org.zgl.logic.login.logic;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * 下线
 */
@Protocol("17")
public class Logout extends OperateCommandAbstract {
    public Logout(String account) {
        super(account);
    }
    @Override
    public Object execute() {
        UserMap userMap = SessionManager.removeSession(getAccount());
        if(userMap == null)
            new GenaryAppError(AppErrorCode.SERVER_ERR);
        return null;
    }
}
