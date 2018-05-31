package org.zgl.logic.room_connection;


import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.ISession;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.room.RoomManager;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;
import org.zgl.utils.logger.LoggerUtils;

/**
 * 通知玩家结束游戏了
 */
@Protocol("10003")
public class RoomPlayerErrorLogout extends OperateCommandAbstract {
    public RoomPlayerErrorLogout(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(getAccount());
        if(userMap != null){
            RoomManager.exit(getAccount(),userMap.getScenesId());
            SessionManager.removeSession(getAccount());
        }
        LoggerUtils.getLogicLog().info("玩家："+getAccount()+":下线");
        return null;
    }
}
