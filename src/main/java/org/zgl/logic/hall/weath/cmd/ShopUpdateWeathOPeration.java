package org.zgl.logic.hall.weath.cmd;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.weath.dto.ShopBuySyncDto;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * @作者： big
 * @创建时间： 18-5-31
 * @文件描述：
 */
@Protocol("10008")
public class ShopUpdateWeathOPeration extends OperateCommandAbstract {
    private String targetAccount;

    public ShopUpdateWeathOPeration(String targetAccount, String account) {
        super(account);
        this.targetAccount = targetAccount;
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(targetAccount);
        SQLWeathModel weathModel = userMap.getWeath();
        ShopBuySyncDto dto = new ShopBuySyncDto();
        dto.setAccount(targetAccount);
        dto.setDiamond(weathModel.getDiamond());
        dto.setGold(weathModel.getGold());
        dto.setIntegral(weathModel.getIntegral());
        dto.setVipLv(weathModel.getVipLv());
        return dto;
    }
}
