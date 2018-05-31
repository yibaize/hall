package org.zgl.logic.room.three_cards.three_cards_1;

/***
 * 初级场
 */
public class RoomFirstManage {
    /**房间号对应的小房间*/
//    private static Map<Integer,IRoom> roomMap = new HashMap<>();
//    private static final Object lock = new Object();
//    public static IRoom roomForId(int id){
//        return roomMap.getOrDefault(id,null);
//    }
//    public static IRoom createRoom(IPlayer player){
//        synchronized (lock){
//            int roomId = roomMap.size() + 1;
//            IRoom room = new Rooms(player,roomId);
//            roomMap.putIfAbsent(roomId,room);
//            return room;
//        }
//    }
//
//    /**
//     * 删除房间
//     * @param roomId
//     * @return
//     */
//    public static boolean clearRoom(int roomId){
//        synchronized (lock){
//            if(roomMap.containsKey(roomId)) {
//                roomMap.remove(roomId);
//                return true;
//            }
//            return false;
//        }
//    }
//
//    /**
//     * 进入房间
//     * @param player
//     */
//    public static IRoom intoRoom(IPlayer player){
//        PlayerInfoDto p = (PlayerInfoDto) player;
//        for(IRoom room : roomMap.values()){
//            if(room.roomId() != p.getRoomId() && room.roomPlayerNumber() < 5){
//                room.intoRoom(p);
//                return room;
//            }
//        }
//        //房间满了
//        return createRoom(player);//创建房间
//    }
//
//    /**
//     * 离开房间
//     * @param player
//     */
//    public static void leaveRoom(IPlayer player){
//        PlayerInfoDto p = (PlayerInfoDto) player;
//        int roomId = p.getRoomId();
//        if(!roomMap.containsKey(roomId))
//            return;
//        IRoom room = roomMap.get(roomId);
//        room.leave(player);
//        if(room.roomPlayerNumber() <= 0)
//            clearRoom(roomId);
//    }
//    public static void changeRoom(IPlayer player){
//        leaveRoom(player);
//        intoRoom(player);
//    }
}
