package org.zgl.logic.hall.shop.cmd;

import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.error.LogAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.shop.data.CommodityDataTable;
import org.zgl.logic.hall.task.manager.TaskManager;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * @作者： big
 * @创建时间： 2018/5/21
 * @文件描述：
 */
@Protocol("22")
public class ShopBuy_auto extends OperateCommandAbstract {
    private final int commodityId;
    public ShopBuy_auto(int commodityId, String account) {
        super(account);
        this.commodityId = commodityId;
    }

    @Override
    public Object execute() {
        CommodityDataTable dataTable = CommodityDataTable.get(commodityId);
        if(dataTable == null)
            new LogAppError("获取不到id为:"+commodityId+" 商城对应的物品");
        UserMap userMap = SessionManager.getSession(getAccount());
        int count = dataTable.getCount();
        bayAuto(userMap.getWeath(),dataTable,count);
        TaskManager.getInstance().listener(userMap,11);//购买座驾
        userMap.update(new String[]{"task"});
        return null;
    }
    /**
     * 购买座驾
     */
    private Object bayAuto(SQLWeathModel weath, CommodityDataTable dataTable, int count){
        if(weath.getVipLv() < dataTable.getVIPLimitLv())
            new GenaryAppError(AppErrorCode.VIP_LV_ERR);//throw new RuntimeException("您的vip等级不足");
        if(!weath.reduceGold(dataTable.getSelling()*count))
            new GenaryAppError(AppErrorCode.GOLD_NOT_ERR);
        //购买第一个时默认使用
        if(weath.getAuto() == 0)
            weath.setAuto(dataTable.getId());
        return null;
    }
}
