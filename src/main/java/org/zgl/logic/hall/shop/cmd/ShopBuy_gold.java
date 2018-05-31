package org.zgl.logic.hall.shop.cmd;

import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.error.LogAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.shop.data.CommodityDataTable;
import org.zgl.logic.hall.weath.dto.WeathResourceDto;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * @作者： big
 * @创建时间： 2018/5/21
 * @文件描述：
 */
@Protocol("24")
public class ShopBuy_gold extends OperateCommandAbstract {
    private final int commodityId;
    public ShopBuy_gold(int commodityId, String account) {
        super(account);
        this.commodityId = commodityId;
    }

    @Override
    public Object execute() {
        CommodityDataTable dataTable = CommodityDataTable.get(commodityId);
        if(dataTable == null)
            new LogAppError("获取不到id为:"+commodityId+" 商城对应的物品");
        UserMap userMap = SessionManager.getSession(getAccount());

        SQLWeathModel weath = userMap.getWeath();
        if(!weath.reduceDiamond(dataTable.getSelling())){
            new GenaryAppError(AppErrorCode.DIAMOND_ERR);
        }
        weath.addGoldOrDiamond(1,dataTable.getCount());
        userMap.update(new String[]{"weath"});
        return new WeathResourceDto(weath.getGold(),weath.getDiamond(),weath.getIntegral());
    }
}
