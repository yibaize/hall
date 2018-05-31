package org.zgl.logic.hall.giftBag;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.giftBag.manaer.GiftBagManager;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

@Protocol("3")
public class GiftBagCmd extends OperateCommandAbstract {
    private final int type;

    public GiftBagCmd(int type, String account) {
        super(account);
        this.type = type;
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(getAccount());
        GiftBagManager.getInstance().handler(userMap);
        return null;
    }
}
