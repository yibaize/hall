package org.zgl.logic.room_connection;
import org.zgl.error.AppErrorCode;
import org.zgl.error.CloseConnectionError;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.logic.room.RoomManager;
import org.zgl.player.PlayerInfoDto;
import org.zgl.player.UserMap;
import org.zgl.utils.ProtostuffUtils;
import org.zgl.utils.builder_clazz.ann.Protocol;

import java.util.HashMap;
import java.util.Map;


/**
 * 返回玩家进入房间的数据,这里是大厅请求入口 返回自身的信息,之后需要请求CloseHallConnection关闭连接
 */
@Protocol("18")
public class IntoRoom extends OperateCommandAbstract {
    public enum ScenesEnum{
        HALL(0,1),
        ROOM_FIRST(1,2),
        ROOM_TWO(2,2),
        ROOM_LAST(3,2),
        DICE(4,1),
        THOUSANDS_OF(5,1),
        TO_ROOM_1(6,2),
        TO_ROOM_2(7,2),
        TO_ROOM_3(8,2);
        private int id;
        private int type;

        ScenesEnum(int id, int type) {
            this.id = id;
            this.type = type;
        }

        public int id(){
            return id;
        }
        public int type(){
            return type;
        }
        private static final Map<Integer,ScenesEnum> map;
        static {
            map = new HashMap<>(ScenesEnum.values().length);
            for(ScenesEnum s : ScenesEnum.values()){
                map.putIfAbsent(s.id,s);
            }
        }
        public static ScenesEnum get(int id){
            if(map.containsKey(id))
                return map.get(id);
            return null;
        }
    }
    /**场景id 1：初级场 2：中级场 3：高级场 4：骰子场 5：万人场 6：千王场1 7：千王场2 8：千王场3
     * 1，万人场类型 2，开房间类型*/
    private final int scenesId;
    public IntoRoom(int scenesId,String account) {
        super(account);
        this.scenesId = scenesId;
    }
    @Override
    public Object execute() {
//        UserMap userMap = (UserMap) getSession().getAttachment();
//        if(userMap == null)
//            new GenaryAppError(AppErrorCode.ACCOUNT_NOT_LOGIN_ERROR);
//        PlayerInfoDto player = new PlayerInfoDto(userMap);
//        int roomId = RoomManager.enter(scenesId, player.getAccount());
//        player.setScenesId(scenesId);
//        player.setRoomId(roomId);
//        userMap.setScenesId(scenesId);
//        userMap.setRoomId(roomId);
//        byte[] buf = ProtostuffUtils.serializer(new IntoRoomDto(roomId));//返回
////        Response response = new Response(getCmdId(),buf);
////        getSession().write(response);
//        PlayerManager.intoRoom(userMap.getAccount());
        //进入房间关闭大厅链接
        new CloseConnectionError(AppErrorCode.EXCHANGE_SCENES_SUCCEED);
        return null;
    }
}
