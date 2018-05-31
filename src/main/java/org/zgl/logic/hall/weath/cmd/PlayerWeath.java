package org.zgl.logic.hall.weath.cmd;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

@Protocol("20")
public class PlayerWeath extends OperateCommandAbstract {
    private final String account;

    public PlayerWeath(String account) {
        super(account);
        this.account = account;
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(account);
        return userMap.getWeath().weathDto();
    }
}
