package org.zgl.logic.hall.giftBag.manaer;

import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.logic.hall.giftBag.data.GiftBagAwardDataTable;
import org.zgl.logic.hall.giftBag.data.GiftBagAwardModel;
import org.zgl.logic.hall.giftBag.po.SQLGiftBagModel;
import org.zgl.logic.hall.shop.ShopEnum;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.logic.room_connection.IntoRoom;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.User;
import org.zgl.player.UserMap;
import org.zgl.utils.DateUtils;

import java.util.List;

public class GiftBagManager {
    private static GiftBagManager instance;

    public static GiftBagManager getInstance() {
        if(instance == null)
            instance = new GiftBagManager();
        return instance;
    }

    public void handler(UserMap um){
        SQLGiftBagModel giftBagModel = um.getGiftBag();
        if(giftBagModel.isHasGet())
            new GenaryAppError(AppErrorCode.AWARD_GET_ERR);
        int day = giftBagModel.getDay();
        if(day >= 7)
            day = 0;
        day++;
        GiftBagAwardDataTable dataTable = GiftBagAwardDataTable.get(day);
        if(dataTable == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        if(giftBagModel.getPrimary() == dataTable.getPrimary()
                && giftBagModel.getIntermedite() == dataTable.getIntermediate()
                && giftBagModel.getAdvanced() == dataTable.getAdvanced()){
            //加资源
            SQLWeathModel weathModel = um.getWeath();
            List<GiftBagAwardModel> list = dataTable.getAward();
            for (GiftBagAwardModel m:list){
                weathModel.addResource(ShopEnum.getEnum(m.getShopId()),m.getCommondity(),m.getCount());
            }
            giftBagModel.setPrimary(0);
            giftBagModel.setAdvanced(0);
            giftBagModel.setIntermedite(0);
            giftBagModel.setDay(day);
            giftBagModel.setTime(DateUtils.currentDay());
           weathModel.update(um,true);
        }
    }

    /**
     * 任务进行
     * @param um 用户
     */
    public void executeTask(UserMap um){
        SQLGiftBagModel giftBagModel = um.getGiftBag();
        if(giftBagModel.isHasGet())
            return;
        if(giftBagModel.getTime() != DateUtils.currentDay()){
            giftBagModel.setPrimary(0);
            giftBagModel.setAdvanced(0);
            giftBagModel.setIntermedite(0);
        }
        IntoRoom.ScenesEnum scenesEnum = IntoRoom.ScenesEnum.get(um.getScenesId());
        switch (scenesEnum){
            case ROOM_FIRST:
                giftBagModel.setPrimary(giftBagModel.getPrimary() + 1);
                break;
            case ROOM_TWO:
                giftBagModel.setIntermedite(giftBagModel.getIntermedite() + 1);
                break;
            case ROOM_LAST:
                giftBagModel.setAdvanced(giftBagModel.getAdvanced() + 1);
                break;
        }
        //TODO 如果达到领取奖品条件 下发通知客户端
        updateData(um.entity2map(),"giftBag");
    }
    private void updateData(User u, String fieldName){
        //更新数据
        Query query = QueryFactory.createQuery();
        query.update(u,new String[]{fieldName});
    }
    /**
     * 检查是否完成任务
     */
    private void checkTack(){}
}
