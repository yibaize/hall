package org.zgl.logic.hall.task.logic.system;


import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.logic.hall.task.logic.TaskAbs;
import org.zgl.logic.hall.task.po.SQLTaskModel;
import org.zgl.logic.hall.task.po.TaskModel;
import org.zgl.player.UserMap;

/**
 * 系统任务
 */
public class SysTask extends TaskAbs {
    @Override
    public boolean listener(UserMap userMap, TaskDataTable dataTable) {
        SQLTaskModel task = userMap.getTask();
        TaskModel model = task.getTaskModel(dataTable.getTaskType(),dataTable.getId()+"");
        if(model.isHasDone())
            return true;
        model.setHasDone(true);
        model.setTaskId(dataTable.getId()+"");
        return true;
        //任务一完成
    }
}
