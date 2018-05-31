package org.zgl.logic.hall.frineds.dto;

import org.zgl.utils.builder_clazz.ann.Protostuff;

import java.util.List;
@Protostuff
public class FriendListDto {
    private List<FriendBaseInfoDto> friends;
    private List<FriendBaseInfoDto> enemys;

    public FriendListDto() {
    }

    public FriendListDto(List<FriendBaseInfoDto> friends, List<FriendBaseInfoDto> enemys) {
        this.friends = friends;
        this.enemys = enemys;
    }

    public List<FriendBaseInfoDto> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendBaseInfoDto> friends) {
        this.friends = friends;
    }

    public List<FriendBaseInfoDto> getEnemys() {
        return enemys;
    }

    public void setEnemys(List<FriendBaseInfoDto> enemys) {
        this.enemys = enemys;
    }
}
