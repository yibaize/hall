package org.zgl.logic.hall.frineds.cmd;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.frineds.logic.FriendsManager;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

import javax.servlet.http.HttpServletResponse;

@Protocol("6")
public class FriendHandler extends OperateCommandAbstract {
    private final int cmd;
    /**要加的好友的id*/
    private final String targetAccount;
    private final String selfAccount;

    public FriendHandler(int cmd, String targetAccount, String selfAccount) {
        super(selfAccount);
        this.cmd = cmd;
        this.targetAccount = targetAccount;
        this.selfAccount = selfAccount;
    }

    @Override
    public Object execute() {
        FriendsManager manager = FriendsManager.getInstance();
        UserMap userMap = SessionManager.getSession(selfAccount);
        switch (cmd){
            case 1:
                return manager.addFriend(userMap,targetAccount);
            case 2:
                return manager.removeFriend(userMap,targetAccount);
            case 3:
                return manager.addEnemy(userMap,targetAccount);
            case 4:
                return manager.removeEnemy(userMap,targetAccount);
            case 5:
                return manager.selectFriend(userMap,targetAccount);
                default:
                    return null;
        }
    }
}
