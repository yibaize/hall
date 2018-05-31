package org.zgl.logic.hall.vip.po;

import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;

@DataTable
public class VipDataTable implements DataTableMessage {
    private final int id;
    /**vip专属标识*/
    private final String vipIcon;
    /**额外签到奖励*/
    private final int singIn;
    /**摇钱树等级*/
    private final int moneyTreeLv;
    /**好友上限*/
    private final int friendCount;
    /**踢人权限*/
    private final boolean kicking;
    /**座驾特权*/
    private final int auto;
    /**赠送特权*/
    private final boolean giveGift;
    /**私人包房特权*/
    private final boolean privateRoom;
    /**售价*/
    private final int price;
    /**经验*/
    private final long exp;
    /**摇钱树每小时生产金币数额*/
    private final int moneyTreeMultiple;
    public static VipDataTable get(int id){
        return StaticConfigMessage.getInstance().get(VipDataTable.class,id);
    }
    public VipDataTable() {
        id = 0;
        vipIcon = "";
        moneyTreeLv = 0;
        singIn = 0;
        friendCount = 0;
        kicking = false;
        auto = 0;
        giveGift = false;
        privateRoom = false;
        this.price = 0;
        this.exp = 0L;
        this.moneyTreeMultiple = 0;
    }

    public int getId() {
        return id;
    }

    public String getVipIcon() {
        return vipIcon;
    }

    public int getSingIn() {
        return singIn;
    }

    public int getMoneyTreeLv() {
        return moneyTreeLv;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public boolean isKicking() {
        return kicking;
    }

    public int getAuto() {
        return auto;
    }

    public boolean isGiveGift() {
        return giveGift;
    }

    public boolean isPrivateRoom() {
        return privateRoom;
    }

    public int getPrice() {
        return price;
    }

    public int getMoneyTreeMultiple() {
        return moneyTreeMultiple;
    }

    public long getExp() {
        return exp;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
