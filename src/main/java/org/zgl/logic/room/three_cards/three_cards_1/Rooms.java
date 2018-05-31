package org.zgl.logic.room.three_cards.three_cards_1;

public class Rooms {
//    @Override
//    public void intoRoom() {
//
//    }
//
//    @Override
//    public void leave() {
//
//    }
//
//    @Override
//    public int roomId() {
//        return 0;
//    }
//
//    @Override
//    public int roomPlayerNumber() {
//        return 0;
//    }
////    /**每个房间最多有5人*/
////    private PlayerInfoDto[] players = new PlayerInfoDto[5];
//    private int roomId;
//    public Rooms(IPlayer players, int roomId) {
//        this.roomId = roomId;
//        this.players[0] = (PlayerInfoDto) players;
//    }
//
//    @Override
//    public boolean intoRoom(IPlayer player) {
//        PlayerInfoDto play = (PlayerInfoDto) player;
//        for(int i = 0;i < players.length;i++){
//            if(players[i] != null) {
//                players[i] = play;
//                play.setRoomId(roomId);
//                play.setRoomPosition(i);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean leave(IPlayer player) {
//        PlayerInfoDto play = (PlayerInfoDto) player;
//        players[play.getRoomPosition()] = null;
//        play.setRoomId(0);
//        play.setRoomPosition(0);
//        return true;
//    }
//
//    @Override
//    public int roomId() {
//        return roomId;
//    }
//
//    @Override
//    public int roomPlayerNumber() {
//        int count = 0;
//        for(int i = 0;i < players.length;i++){
//            if(players[i] != null)
//                count++;
//        }
//        return count;
//    }
//
//    @Override
//    public IRoom roomInfo() {
//        return this;
//    }
//
//    @Override
//    public PlayerInfoDto player(String account) {
//        for(int i = 0;i<players.length;i++){
//            PlayerInfoDto p = players[i];
//            if(p != null && p.getAccount().equals(account))
//                return p;
//        }
//        return null;
//    }
}
