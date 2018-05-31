package org.zgl.logic.hall.ranking;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.utils.builder_clazz.ann.Protocol;

@Protocol("11")
public class RankingCmd extends OperateCommandAbstract {
    public RankingCmd(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        RankingManager manager = RankingManager.getInstance();
        return manager.rankingList();
    }
}
