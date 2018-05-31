package org.zgl.logic.hall.weath.cmd;


import org.zgl.error.RPCError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.giftBag.manaer.GiftBagManager;
import org.zgl.logic.hall.task.manager.TaskManager;
import org.zgl.logic.hall.weath.dto.RoomWeathDto;
import org.zgl.logic.hall.weath.dto.RoomWeathDtos;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.User;
import org.zgl.player.UserMap;
import org.zgl.utils.ArrayUtils;
import org.zgl.utils.JsonUtils;
import org.zgl.utils.builder_clazz.ann.Protocol;

import java.util.List;

/**
 * 同步更新房间的财富
 */
@Protocol("10001")
public class RoomUpdateWeath extends OperateCommandAbstract {
    private final String roomWeathDto;
    public RoomUpdateWeath(String roomWeathDto,String account) {
        super(account);
        this.roomWeathDto = roomWeathDto;
    }

    @Override
    public Object execute() {
        RoomWeathDtos weathDtos = JsonUtils.jsonDeserialization(roomWeathDto,RoomWeathDtos.class);
        List<RoomWeathDto> weathDtos1 = weathDtos.getWeathDtos();
        Query query = QueryFactory.createQuery();
        for(RoomWeathDto r : weathDtos1){
            UserMap userMap = SessionManager.getSession(r.getAccount());
            if(userMap == null){
                new RPCError(getCmdId());
            }
            SQLWeathModel weathModel = userMap.getWeath();
            weathModel.updateResource(r.getGold(),r.getDiamond(),r.getIntegral());
            User u = userMap.entity2map();
            GiftBagManager.getInstance().executeTask(userMap);//成长礼包
            String[] str = new String[]{"weath"};
            if(r.isHasWin()) {
                //任务监听器
                TaskManager.getInstance().listener(userMap, 15);
                TaskManager.getInstance().listener(userMap, 5);// 体闲游戏一次
                str = ArrayUtils.add(str,"task");
            }
            userMap.update(str);
        }
        return null;
    }
}
