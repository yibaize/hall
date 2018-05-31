package org.zgl.logic.hall.weath.cmd;

import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.User;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * 使用座驾
 */
@Protocol("19")
public class AutoUse extends OperateCommandAbstract {
    private final int id;
    private final String account;
    public AutoUse(int id, String account) {
        super(account);
        this.id = id;
        this.account = account;
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(account);
        SQLWeathModel weath = userMap.getWeath();
        if(!weath.autoUse(id))
            new GenaryAppError(AppErrorCode.NOT_AUTO_ERR);
        User user = userMap.entity2map();
        Query query = QueryFactory.createQuery();
        query.update(user,new String[]{"weath"});
        return null;
    }
}
