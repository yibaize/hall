package org.zgl.logic.room_connection;

import org.zgl.error.RPCError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.ISession;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.room.RoomManager;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * 重房间进入大厅
 */
@Protocol("10002")
public class IntoHall extends OperateCommandAbstract {
    public IntoHall(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap userMap= SessionManager.getSession(getAccount());
        RoomManager.exit(userMap.getAccount(),userMap.getScenesId());
        RoomManager.enter(0,getAccount());//进入大厅
        userMap.setScenesId(IntoRoom.ScenesEnum.HALL.id());
//        PlayerManager.intoHall(account,getSession());
        return null;
    }
}
