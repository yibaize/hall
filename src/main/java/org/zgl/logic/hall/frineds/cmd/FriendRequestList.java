package org.zgl.logic.hall.frineds.cmd;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.ISession;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.frineds.dto.FriendBaseInfoDto;
import org.zgl.logic.hall.frineds.dto.FriendListDto;
import org.zgl.logic.hall.frineds.po.SQLFrinedsModel;
import org.zgl.player.OtherInfoModel;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

import java.util.ArrayList;
import java.util.List;
@Protocol("4")
public class FriendRequestList extends OperateCommandAbstract {
    public FriendRequestList(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap um = SessionManager.getSession(getAccount());
        SQLFrinedsModel frinedsModel = um.getFriends();
        List<OtherInfoModel> friendList = new ArrayList<>(frinedsModel.getFriendsInfos().values());
        List<FriendBaseInfoDto> f = new ArrayList<>(friendList.size());
        for(OtherInfoModel ff:friendList){
            FriendBaseInfoDto dto = ff.baseInfoDto();
            UserMap friend = SessionManager.getSession(dto.getAccount());
            if(friend != null) {
                dto.setHasOnline(true);
            }
            f.add(dto);
        }
        friendsIsOnline(f);
        List<OtherInfoModel> enemyList = new ArrayList<>(frinedsModel.getEnemys().values());
        List<FriendBaseInfoDto> e = new ArrayList<>(enemyList.size());
        for(OtherInfoModel ee:enemyList){
            FriendBaseInfoDto dto = ee.baseInfoDto();
            UserMap friend = SessionManager.getSession(dto.getAccount());
            if(friend != null) {
                dto.setHasOnline(true);
            }
            e.add(dto);
        }
        friendsIsOnline(e);
        return new FriendListDto(f,e);
    }

    /**
     * 好友敌人是否在线
     * @param models
     */
    private void friendsIsOnline(List<FriendBaseInfoDto> models){
        for(int i = 0;i<models.size();i++){
            FriendBaseInfoDto model = models.get(i);
            model.setHasOnline(SessionManager.isOnlinePlayer(model.getAccount()));
        }
    }
}
