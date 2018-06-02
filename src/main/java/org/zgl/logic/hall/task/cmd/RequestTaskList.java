package org.zgl.logic.hall.task.cmd;

import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.task.TaskListDto;
import org.zgl.logic.hall.task.po.SQLTaskModel;
import org.zgl.logic.hall.task.po.TaskModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.Db_user;
import org.zgl.player.UserMap;
import org.zgl.utils.DateUtils;
import org.zgl.utils.builder_clazz.ann.Protocol;

import java.util.ArrayList;
import java.util.List;

@Protocol("14")
public class RequestTaskList extends OperateCommandAbstract {
    public RequestTaskList(String account) {
        super(account);
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(getAccount());
        SQLTaskModel task = userMap.getTask();
        //每日任务清空
        if(DateUtils.currentDay() != task.getTime()) {
            task.getEveryDayTask().clear();
            task.setTime(DateUtils.currentDay());
            Query query = QueryFactory.createQuery();
            Db_user user = userMap.entity2map();
            query.update(user,new String[]{"task"});
        }
        List<TaskModel> systm = new ArrayList<>(task.getSystemTask().values());
        List<TaskModel> everyDay = new ArrayList<>(task.getEveryDayTask().values());
        return new TaskListDto(systm,everyDay);
    }
}
