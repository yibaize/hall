package org.zgl.logic.hall.frineds.po;


import org.zgl.player.OtherInfoModel;

import java.util.HashMap;
import java.util.Map;

public class SQLFrinedsModel {
    /**好友上限*/
    private int countLimit = 5;
    /**好友列表*/
    private Map<String,OtherInfoModel> friendsInfos;
    /**仇家*/
    private Map<String,OtherInfoModel> enemys;

    public SQLFrinedsModel() {
        friendsInfos = new HashMap<>();
        enemys = new HashMap<>();
    }

    public Map<String, OtherInfoModel> getFriendsInfos() {
        return friendsInfos;
    }

    public void setFriendsInfos(Map<String, OtherInfoModel> friendsInfos) {
        this.friendsInfos = friendsInfos;
    }

    public Map<String, OtherInfoModel> getEnemys() {
        return enemys;
    }

    public void setEnemys(Map<String, OtherInfoModel> enemys) {
        this.enemys = enemys;
    }
    public boolean hasFriend(String account){
        return friendsInfos.containsKey(account);
    }
    public boolean hasEnemy(String account){
        return enemys.containsKey(account);
    }

    public int getCountLimit() {
        return countLimit;
    }

    public void setCountLimit(int countLimit) {
        this.countLimit = countLimit;
    }

    /**
     * 添加好友
     * @param friend 目标账号
     */
    public void addFriend(OtherInfoModel friend){
        friendsInfos.putIfAbsent(friend.getAccount(),friend);
    }

    /**
     * 删除好友
     * @param account 目标账号
     * @return
     */
    public boolean removeFriend(String account){
        return friendsInfos.remove(account) != null;
    }
    public void addEnemy(OtherInfoModel enemy){
        enemys.putIfAbsent(enemy.getAccount(),enemy);
    }
    public boolean removeEnemy(String account){
        return enemys.remove(account) != null;
    }
}
