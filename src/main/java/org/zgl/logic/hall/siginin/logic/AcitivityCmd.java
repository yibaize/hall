package org.zgl.logic.hall.siginin.logic;

import org.zgl.error.LogAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.frineds.po.SQLFrinedsModel;
import org.zgl.logic.hall.shop.data.CommodityDataTable;
import org.zgl.logic.hall.vip.dto.VIPDto;
import org.zgl.logic.hall.vip.po.VipDataTable;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

import java.util.HashMap;
import java.util.Map;

@Protocol("10")
public class AcitivityCmd extends OperateCommandAbstract {
    public enum ActivityEnum{
        NONE(0),
        GIFT_BAG_10(1),
        GIFT_BAG_100(2),
        SYSTEM_ANNOUNCEMENT(3),
        MY_MESSAGE(4);
        private int id;
        private static final Map<Integer,ActivityEnum> map;
        static {
            map = new HashMap<>(6);
            for(ActivityEnum e:ActivityEnum.values()){
                map.putIfAbsent(e.id,e);
            }
        }
        ActivityEnum(int id) {
            this.id = id;
        }
        public int id(){
            return id;
        }
        public static ActivityEnum getEnum(int i){
            return map.get(i);
        }
    }
    /**活动中点那个按钮 1：10元礼包 2：星爷礼包 3：系统公告 4：我的消息（邮件）*/
    private final int id;
    public AcitivityCmd(int id, String account) {
        super(account);
        this.id = id;
    }

    @Override
    public Object execute() {
        ActivityEnum activityEnum = ActivityEnum.getEnum(id);
        UserMap userMap = SessionManager.getSession(getAccount());
        SQLWeathModel weath =userMap.getWeath();
        switch (activityEnum){
            case GIFT_BAG_10:
                CommodityDataTable dataTable = CommodityDataTable.get(22);
                f(userMap.getWeath(),dataTable,userMap);
                weath.addIntegral(10);
                weath.addGoldOrDiamond(1,1200000);
                userMap.update(new String[]{"friends","weath"});
                break;
            case GIFT_BAG_100:
                CommodityDataTable dataTable1 = CommodityDataTable.get(24);
                f(userMap.getWeath(),dataTable1,userMap);
                weath.addIntegral(100);
                weath.addGoldOrDiamond(1,13000000);
                weath.update(userMap,true);
                userMap.update(new String[]{"friends","weath"});
                break;
            case SYSTEM_ANNOUNCEMENT:
                break;
            case MY_MESSAGE:
                break;
        }
        return null;
    }
    private void f(SQLWeathModel weath,CommodityDataTable dataTable,UserMap userMap){

        //返回vip当前等级和经验
        VIPDto o = bayVip(weath, dataTable, dataTable.getCount());
        //好友上限人数
        VipDataTable vipDataTable = VipDataTable.get(o.getVipLv());
        SQLFrinedsModel frineds = userMap.getFriends();
        int frintCount = frineds.getCountLimit();
        if (vipDataTable != null) {
            frintCount = vipDataTable.getFriendCount();
        }
        frineds.setCountLimit(frintCount);
    }
    /**
     * 购买vip
     */
    private VIPDto bayVip(SQLWeathModel weath, CommodityDataTable dataTable, int count) {
        int exp = 0;
        try {
            exp = Integer.parseInt(dataTable.getEffect());
        } catch (NumberFormatException e) {
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
            } else {
                break;
            }
        }
        //设置vip等级
        weath.setVipLv(vipLv);
        //设置vip经验
        weath.setVipExp(vipExp);
        return new VIPDto(vipLv, vipExp);
    }

    /**
     * 星爷礼包
     */
    private void giftBag100(){}

    /**
     * 系统公告
     */
    private void systemAnnouncement(){}

    /**
     * 我的消息
     */
    private void myMessage(){}

}
