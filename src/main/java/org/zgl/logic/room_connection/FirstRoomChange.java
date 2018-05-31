package org.zgl.logic.room_connection;


import org.zgl.error.RPCError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.room.RoomManager;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * 房间交换
 */
@Protocol("10006")
public class FirstRoomChange extends OperateCommandAbstract {
    public FirstRoomChange(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(getAccount());
        if(userMap == null)
            new RPCError(getCmdId());
        int scenesId = userMap.getScenesId();
        RoomManager.exit(getAccount(),scenesId);
        int roomId = RoomManager.changeRoom(scenesId,0,getAccount());
        return new IntoRoomDto(roomId);
    }
}
