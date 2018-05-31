package org.zgl.logic.room_connection;


import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class IntoRoomDto {
    /**房间号*/
    private int roomId;

    public IntoRoomDto() {
    }

    public IntoRoomDto(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

}
