package org.zgl.logic.hall.frineds.logic;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.error.LogAppError;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.frineds.po.SQLFrinedsModel;
import org.zgl.logic.hall.shop.ShopEnum;
import org.zgl.logic.hall.shop.data.CommodityDataTable;
import org.zgl.logic.hall.task.manager.TaskManager;
import org.zgl.logic.hall.vip.po.VipDataTable;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.Db_user;
import org.zgl.player.OtherInfoModel;
import org.zgl.player.PlayerInit;
import org.zgl.player.UserMap;

public class FriendsManager {
    private static FriendsManager instance;

    public static FriendsManager getInstance() {
        if(instance == null)
            instance = new FriendsManager();
        return instance;
    }

    /***
     * 添加好友
     */
    public Object addFriend(UserMap selfMap, String targetAccount){
        SQLFrinedsModel frinedsModel = selfMap.getFriends();
        int firendMax = frinedsModel.getCountLimit();
        VipDataTable vipDataTable = VipDataTable.get(selfMap.getWeath().getVipLv());
        if(vipDataTable != null)
            firendMax = vipDataTable.getFriendCount();
        if(firendMax <= frinedsModel.getFriendsInfos().size())
            new GenaryAppError(AppErrorCode.FRIEND_LIMIT_ERR);

        UserMap targetMap = SessionManager.getSession(targetAccount);
        if(targetMap == null){
            targetMap = selectUser(targetAccount);
            if(targetMap == null)
                new GenaryAppError(AppErrorCode.FRIEND_NOT_USER_ERR);
        }
        if(frinedsModel == null){
            frinedsModel = new SQLFrinedsModel();
        }else if(frinedsModel.hasFriend(targetAccount))
            new GenaryAppError(AppErrorCode.FRIEND_IS_FRIEND_ERR);//throw new RuntimeException("该用户已经是您的好友");

        OtherInfoModel friendsBaseInfoModel = new OtherInfoModel(targetMap);
        frinedsModel.addFriend(friendsBaseInfoModel);
        //任务3监听
        if(frinedsModel.getFriendsInfos().size() ==5){
            TaskManager.getInstance().listener(selfMap,3);
            Query query = QueryFactory.createQuery();
            Db_user user = selfMap.entity2map();
            query.update(user,new String[]{"task"});
        }
        //更新字段
        updateUser(selfMap);
        return friendsBaseInfoModel.baseInfoDto();
    }

    /**
     * 删除好友
     */
    public Object removeFriend(UserMap um,String targetAccount){
        //更新字段
        if(um.getFriends().removeFriend(targetAccount))
            updateUser(um);
        return null;
    }

    /**
     * 赠送礼物
     */
    public Object sendGift(UserMap selfMap,String targetAccount,int giftId,int count){

        UserMap targetMap = SessionManager.getSession(targetAccount);
        if(targetMap == null){
            targetMap = selectUser(targetAccount);
            if(targetMap == null)
                new GenaryAppError(AppErrorCode.FRIEND_NOT_USER_ERR);
        }
        CommodityDataTable dataTable = CommodityDataTable.get(giftId);
        if(dataTable == null)
            new LogAppError("获取不到id为："+giftId+" 的礼物数据");
        int shopId = dataTable.getShopId();
        //自己
            //获取礼物对象
        selfMap.getWeath().sendGift(ShopEnum.getEnum(shopId),giftId,count);
            //减少数量
        //目标
        targetMap.getWeath().addResource(ShopEnum.getEnum(shopId),giftId,count);
            //邮件通知好友
            //增加礼物数量
        if(giftId == 42){
            //玫瑰
            TaskManager.getInstance().listener(selfMap,2);
            Query query = QueryFactory.createQuery();
            Db_user user = selfMap.entity2map();
            query.update(user,new String[]{"task"});
        }else if(dataTable.getShopId() == 2){
            //座驾
            TaskManager.getInstance().listener(selfMap,7);
            Query query = QueryFactory.createQuery();
            Db_user user = selfMap.entity2map();
            query.update(user,new String[]{"task"});
        }
         return null;
    }

    /**
     * 好友追踪
     */
    public Object selectFriend(UserMap userMap,String targetAccount){
        return null;
    }

    /**
     * 加入仇人列队
     * @param enemyAccount 要加入仇人的账号
     */
    public Object addEnemy(UserMap self,String enemyAccount){
        UserMap targetMap = SessionManager.getSession(enemyAccount);
        if(targetMap == null){
            targetMap = selectUser(enemyAccount);
        }
        SQLFrinedsModel frinedsModel = self.getFriends();
        if(frinedsModel.hasEnemy(enemyAccount))
            new GenaryAppError(AppErrorCode.FRIEND_IS_ENEMY_ERR);
        if(frinedsModel.hasFriend(enemyAccount)){
            //从好友列表移除
            frinedsModel.removeFriend(enemyAccount);
        }
        OtherInfoModel targetModel = new OtherInfoModel(targetMap);
        targetMap.getFriends().addFriend(new OtherInfoModel(self));
        frinedsModel.addEnemy(targetModel);
        updateUser(targetMap);
        updateUser(self);
        return targetModel;
    }

    /**
     * 移除仇人列队
     * @param targetAccount
     */
    public Object removeEnemy(UserMap um,String targetAccount){
        if(um.getFriends().removeEnemy(targetAccount))
            updateUser(um);
        return null;
    }
    private UserMap selectUser(String account){
        Query query = QueryFactory.createQuery();
        Db_user u = (Db_user) query.queryUniqueRow("SELECT * FROM db_user WHERE id=?",Db_user.class,new Object[]{account});
        if(u == null)
            new GenaryAppError(AppErrorCode.FRIEND_NOT_USER_ERR);
        return PlayerInit.initUserMap(u);
    }
    private boolean updateUser(UserMap um){
        Query query = QueryFactory.createQuery();
        Db_user user = um.entity2map();
        return query.update(user,new String[]{"friends"}) > 0;
    }
}
