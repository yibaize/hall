package org.zgl.logic.room_connection;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.player.PlayerInfoDto;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * 房间玩家进入房间之后由房间服务器请求这里并返回PlayerInfoDto
 */
@Protocol("10000")
public class RoomPlayerInfo extends OperateCommandAbstract {
    public RoomPlayerInfo(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap u = SessionManager.getSession(getAccount());
        if(!SessionManager.isOnlinePlayer("-999999999")) {
            UserMap connection = new UserMap();
            connection.setAccount("-999999999");
            SessionManager.putSession(connection.getAccount(), u);
        }
        return new PlayerInfoDto(u);
    }
}
