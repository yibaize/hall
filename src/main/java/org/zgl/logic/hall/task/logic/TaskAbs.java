package org.zgl.logic.hall.task.logic;

import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.logic.hall.task.manager.ITask;
import org.zgl.logic.hall.task.po.SQLTaskModel;
import org.zgl.logic.hall.task.po.TaskModel;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.Db_user;
import org.zgl.player.UserMap;

public abstract class TaskAbs implements ITask {
    @Override
    public void update(UserMap userMap) {
        Db_user user = userMap.entity2map();
        Query query = QueryFactory.createQuery();
        query.update(user,new String[]{"task"});
    }
    @Override
    public void getAward(UserMap userMap, int taskId) {
        TaskDataTable dataTable = TaskDataTable.get(taskId);
        if(dataTable == null)
            new GenaryAppError(AppErrorCode.SERVER_ERR);
        SQLTaskModel task = userMap.getTask();
        TaskModel model = task.getTaskModel(dataTable.getTaskType(),taskId+"");
        if(model.isHasDone() && !model.isHasAward()){
            SQLWeathModel weath = userMap.getWeath();
            weath.addGoldOrDiamond(1,dataTable.getAward());
            model.setHasAward(true);//领取奖励成功
            weath.update(userMap,true);
        }else {
            new GenaryAppError(AppErrorCode.TASK_NOT_DONE);
        }
    }
}
