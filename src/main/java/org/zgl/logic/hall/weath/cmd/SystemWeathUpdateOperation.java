package org.zgl.logic.hall.weath.cmd;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * @作者： big
 * @创建时间： 2018/6/11
 * @文件描述：
 */
@Protocol("10004")
public class SystemWeathUpdateOperation extends OperateCommandAbstract {
    private final int scenesId;
    private final long todayMoney;
    private final long jackpot;
    private final long bloodGroove;
    public SystemWeathUpdateOperation(String account, int scenesId, long todayMoney, long jackpot, long bloodGroove) {
        super(account);
        this.scenesId = scenesId;
        this.todayMoney = todayMoney;
        this.jackpot = jackpot;
        this.bloodGroove = bloodGroove;
    }
    @Override
    public Object execute() {
        return null;
    }
}
