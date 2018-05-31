package org.zgl.logic.hall.weath.po;

import org.zgl.error.LogAppError;
import org.zgl.logic.hall.vip.po.VipDataTable;
import org.zgl.logic.hall.weath.dto.MoneyTreeDto;
import org.zgl.utils.DateUtils;

public class SQLMoenyTree {

    /**上次领取时间*/
    private long timer;
    /**摇钱树等级 等级大于0说明已经购买*/
    private int lv;
    public SQLMoenyTree() {
    }

    public SQLMoenyTree(long timer, int lv) {
        this.timer = timer;
        this.lv = lv;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    /**
     * 可领取金额
     * @return
     */
    public int goldNumber(){
        int time = leadTtimer() / 2;
        if(lv <= 0 || time <= 0)
            return 0;
        VipDataTable vipDataTable = VipDataTable.get(lv);
        if(vipDataTable == null)
            new LogAppError("数据异常，没有vip为:"+lv+"等级对应的数据");
        return time * vipDataTable.getMoneyTreeMultiple();
    }

    /**
     * 多久没领取/以小时计算
     * @return
     */
    public int leadTtimer(){
        long nowTime = DateUtils.currentTime();
        int hour = (int) (nowTime - timer)/(3600000);
        return hour = hour > 0 ? hour : 0;
    }
    public MoneyTreeDto getDto(){
        return new MoneyTreeDto(lv,leadTtimer(),goldNumber());
    }
}
