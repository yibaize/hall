package org.zgl.logic.hall.task.cmd;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.operation.OperateCommandAbstract;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.logic.hall.task.po.SQLTaskModel;
import org.zgl.logic.hall.task.po.TaskModel;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.User;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.Protocol;

/**
 * 点击领取任务奖励
 */
@Protocol("15")
public class RequestTaskGetAward extends OperateCommandAbstract {
    private final int taskId;

    public RequestTaskGetAward(int taskId, String account) {
        super(account);
        this.taskId = taskId;
    }

    @Override
    public Object execute() {
        UserMap userMap = SessionManager.getSession(getAccount());
        SQLTaskModel task = userMap.getTask();
        TaskDataTable dataTable = TaskDataTable.get(taskId);
        TaskModel tm = task.task(dataTable.getTaskType(),taskId);
        if(tm == null || tm.isHasDone())
            new GenaryAppError(AppErrorCode.TASK_NOT_DONE);
        if(dataTable == null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        if(tm.isHasAward())
            new GenaryAppError(AppErrorCode.AWARD_GET_ERR);
        SQLWeathModel weath = userMap.getWeath();
        weath.addGoldOrDiamond(1,dataTable.getAward());
        weath.update(userMap,true);
        tm.setHasAward(true);
        Query query = QueryFactory.createQuery();
        User user = userMap.entity2map();
        query.update(user,new String[]{"task"});
        return null;
    }
}
