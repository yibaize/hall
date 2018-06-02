package org.zgl.player;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.Db_user;
import org.zgl.utils.builder_clazz.ann.Protocol;

@Protocol("21")
public class RequestPlayerInfo extends OperateCommandAbstract {
    public RequestPlayerInfo(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap um = SessionManager.getSession(getAccount());
        if(um == null){
            Query query = QueryFactory.createQuery();
            Db_user u = (Db_user) query.queryUniqueRow("SELECT * FROM db_user WHERE account=?",Db_user.class,new Object[]{getAccount()});
            um = PlayerInit.initUserMap(u);
        }
        if(um == null)
            new GenaryAppError(AppErrorCode.FRIEND_NOT_USER_ERR);
        OtherInfoModel dto = new OtherInfoModel(um);
        return dto;
    }
}
