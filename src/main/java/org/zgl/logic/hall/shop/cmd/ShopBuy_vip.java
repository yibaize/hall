package org.zgl.logic.hall.shop.cmd;

import org.zgl.error.LogAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.frineds.po.SQLFrinedsModel;
import org.zgl.logic.hall.shop.data.CommodityDataTable;
import org.zgl.logic.hall.task.manager.TaskManager;
import org.zgl.logic.hall.vip.dto.VIPDto;
import org.zgl.logic.hall.vip.po.VipDataTable;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;
@Protocol("7")
public class ShopBuy_vip extends OperateCommandAbstract {
    private final int commodityId;
    public ShopBuy_vip(int commodityId, String account) {
        super(account);
        this.commodityId = commodityId;
    }

    @Override
    public Object execute() {
        CommodityDataTable dataTable = CommodityDataTable.get(commodityId);
        if(dataTable == null)
            new LogAppError("获取不到id为:"+commodityId+" 商城对应的物品");
        UserMap userMap = SessionManager.getSession(getAccount());
        //返回vip当前等级和经验
        VIPDto o = bayVip(userMap.getWeath(),dataTable,dataTable.getCount());
        //好友上限人数
        VipDataTable vipDataTable = VipDataTable.get(((VIPDto) o).getVipLv());
        SQLFrinedsModel frineds = userMap.getFriends();
        int frintCount = frineds.getCountLimit();
        if(vipDataTable != null){
            frintCount = vipDataTable.getFriendCount();
        }
        frineds.setCountLimit(frintCount);
        userMap.update(new String[]{"weath","friends"});
        TaskManager.getInstance().listener(userMap,10);//vip勋章
        return o;
    }
    /**
     * 购买vip
     */
    private VIPDto bayVip(SQLWeathModel weath, CommodityDataTable dataTable, int count){
        int exp = 0;
        try {
            exp = Integer.parseInt(dataTable.getEffect());
        }catch (NumberFormatException e){
            new LogAppError("获取不到vip的经验值");
        }
        //vip等级、经验
        int vipLv = weath.getVipLv();
        long vipExp = weath.getVipExp();
        long vipBuyExp = exp * count;//购买到的经验
        //vip升到下一级所需经验
        vipExp += vipBuyExp;//vip本次购买之后的总经验（用这个去算能升几级）
        while (true) {
            VipDataTable vipDataTable = VipDataTable.get(vipLv + 1);
            //升级下一级所需经验减去vip当前经验+本次购买获得的经验如果小于0说明要升级
            long vipTemp = vipDataTable.getExp() - vipExp;
            if (vipTemp < 0) {
                //vip等级+1
                vipLv++;
                vipExp -= vipDataTable.getExp();
                //经验值归0
            }else {
                break;
            }
        }
        //设置vip等级
        weath.setVipLv(vipLv);
        //设置vip经验
        weath.setVipExp(vipExp);
        return new VIPDto(vipLv,vipExp);
    }
}
