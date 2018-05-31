package org.zgl.logic.hall.task.logic.everyday;

import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.logic.hall.task.logic.TaskAbs;
import org.zgl.logic.hall.task.po.SQLTaskModel;
import org.zgl.logic.hall.task.po.TaskModel;
import org.zgl.player.UserMap;

public abstract class EveryDayTaskAbs extends TaskAbs {
    /**
     * 任意场类型任务
     */
    protected boolean atWill(UserMap userMap, TaskDataTable dataTable){
        SQLTaskModel task = userMap.getTask();
        TaskModel taskModel = task.getTaskModel(dataTable.getTaskType(),dataTable.getId()+"");
        int taskState =  taskModel.getTaskState();//增加完成次数
        int limit =  dataTable.getTaskLimit();
       if(taskState < limit)
           taskModel.insertTaskState();
        if(taskModel.getTaskState() >= limit){
            taskModel.setHasDone(true);
            return true;//任务完成
        }
        return false;
    }
}
