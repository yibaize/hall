package org.zgl.logic.room;

public class Room {
    private static final int MAX_NUM = 5;
    private int roomId;
    private int playerNum;

    public Room(int roomId) {
        this.roomId = roomId;
        this.playerNum = 1;//创建出来就有一位玩家在里边
    }
    public boolean enter(){
        if(playerNum >= MAX_NUM)
            return false;
        playerNum++;
        return true;
    }
    public boolean exit(){
        playerNum--;
        if(playerNum <= 0)
            return false;
        return true;
    }
    public int playerNum(){
        return playerNum;
    }
    public int roomId(){
        return roomId;
    }

    @Override
    public boolean equals(Object obj) {
        Room r = (Room) obj;
        return r.roomId == roomId;
    }
}
