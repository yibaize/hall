package org.zgl.logic.room;

import org.zgl.logic.room_connection.IntoRoom;
import org.zgl.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomManager {
    private static final Object lock = new Object();
    public static final int ERR_ROOM_ID = -123654;
    private static final int HALL = 0;
    private static final int THOUSANDS_OF_ROOM = 1;//万人场模式
    private static final int ROOM_BLOCK = 2;//开房模式
    private static int initRoomId = 1000;
    private static Map<Integer,List<Room>> roomMap;//场景嵌套玩家账号对应房间(开房模式)
    private static Map<String,Room> accounMap = new ConcurrentHashMap<>();
    static {
        roomMap = new ConcurrentHashMap<>();//场景嵌套玩家账号对应房间(开房模式)
        roomMap.put(IntoRoom.ScenesEnum.ROOM_FIRST.id(),new ArrayList<>());
        roomMap.put(IntoRoom.ScenesEnum.ROOM_TWO.id(),new ArrayList<>());
        roomMap.put(IntoRoom.ScenesEnum.ROOM_LAST.id(),new ArrayList<>());
        roomMap.put(IntoRoom.ScenesEnum.TO_ROOM_1.id(),new ArrayList<>());
        roomMap.put(IntoRoom.ScenesEnum.TO_ROOM_2.id(),new ArrayList<>());
        roomMap.put(IntoRoom.ScenesEnum.TO_ROOM_3.id(),new ArrayList<>());
    }
    //TODO 万人场模式
    public static int enter(int scenesId,String account){
        synchronized (lock) {
            IntoRoom.ScenesEnum scenesEnum = IntoRoom.ScenesEnum.get(scenesId);
            if (scenesEnum != null) {
                if (scenesEnum.type() == THOUSANDS_OF_ROOM) {
                    return 0;
                } else if (scenesEnum.type() == ROOM_BLOCK) {
                    return roomBlock(scenesId, account);
                }else if(scenesEnum.type() == HALL){
                    //大厅
                }
            }
            if (roomMap.containsKey(scenesId)) {

            }
            return ERR_ROOM_ID;
        }
    }
    public static void exit(String account,int scenesId){
        synchronized (lock) {
            Room r = accounMap.getOrDefault(account, null);
            if (r != null) {
                if (!r.exit()) {
                    List<Room> list = roomMap.get(scenesId);
                    if (list.contains(r)) {
                        list.remove(r);
                    }
                }
            }
        }
    }
    private static int roomBlock(int scenesId,String account){
        if(roomMap.containsKey(scenesId)){
            List<Room> list = roomMap.get(scenesId);
            for(Room r : list){
                if(r.enter()) {
                    accounMap.putIfAbsent(account,r);
                    return r.roomId();
                }
            }
            Room r = new Room(initRoomId++);
            list.add(r);
            accounMap.putIfAbsent(account,r);
            return r.roomId();
        }
        return ERR_ROOM_ID;
    }
    public static int changeRoom(int scenesId,int id,String account){
        List<Room> r = roomMap.get(scenesId);
        Room room = null;
        int temp = 0;
        int size = r.size();
        while (size > 3 ) {
            int roomIdTemp = RandomUtils.randomIndex(r.size());
            room = r.get(roomIdTemp);
            if(room.roomId() != id && room.enter()) {
                return room.roomId();
            }
            temp++;
            if( temp >= size)
                break;
        }
        if(room == null)
            room = new Room(initRoomId++);
        room.enter();
        accounMap.put(account,room);
        r.add(room);
        return room.roomId();
    }
}
