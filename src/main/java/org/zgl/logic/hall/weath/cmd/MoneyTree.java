package org.zgl.logic.hall.weath.cmd;

import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.shop.ShopEnum;
import org.zgl.logic.hall.weath.dto.MoneyTreeDto;
import org.zgl.logic.hall.weath.po.SQLMoenyTree;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.player.UserMap;
import org.zgl.utils.DateUtils;
import org.zgl.utils.builder_clazz.ann.Protocol;

@Protocol("8")
public class MoneyTree extends OperateCommandAbstract {
    /**
     * 1:加载显示数据
     * 2：点击领取
     */
    private final int tag;
    private final String account;

    public MoneyTree(int tag, String account) {
        super(account);
        this.tag = tag;
        this.account = account;
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(account);
        SQLWeathModel weath = userMap.getWeath();
        SQLMoenyTree moenyTree = weath.getMoneyTree();
        switch (tag){
            case 1:
                return moenyTree.getDto();
            case 2:
                return receive(userMap,weath);
                default:
                    new GenaryAppError(AppErrorCode.DATA_ERR);
        }
        return null;
    }

    /**
     * 领取摇钱树奖励
     * @return
     */
    private MoneyTreeDto receive(UserMap userMap, SQLWeathModel weath){
        SQLMoenyTree moenyTree = weath.getMoneyTree();
        if(moenyTree.leadTtimer() <= 0)
            new GenaryAppError(AppErrorCode.TIMER_ERR);
        weath.addResource(ShopEnum.GOLD,1,moenyTree.goldNumber());
        moenyTree.setTimer(DateUtils.currentTime());
        weath.update(userMap,true);
        return null;
    }
}
