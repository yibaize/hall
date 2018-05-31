package org.zgl.logic.hall.task.manager;

import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.logic.hall.task.logic.everyday.*;
import org.zgl.logic.hall.task.logic.system.SysTask;
import org.zgl.player.UserMap;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private static TaskManager instance;
    private Map<Integer,ITask> taskMap = new HashMap<>();
    public static TaskManager getInstance() {
        if(instance == null)
            instance = new TaskManager();
        return instance;
    }

    private TaskManager() {
        taskMap.put(1,new SysTask());
        taskMap.put(13,new Task_13());
        taskMap.put(14,new Task_14());
        taskMap.put(15,new Task_15());
        taskMap.put(16,new Task_16());
        taskMap.put(17,new Task_17());
        taskMap.put(18,new Task_18());
        taskMap.put(19,new Task_19());
        taskMap.put(20,new Task_20());
    }
    //TODO... 1,4,8,18,19,20没做
    public void listener(UserMap userMap, int taskId){
        TaskDataTable dataTable = TaskDataTable.get(taskId);
        if(dataTable == null)
            new GenaryAppError(AppErrorCode.SERVER_ERR);
        if(dataTable.getTaskType() == 1) {
            ITask task = taskMap.get(1);
            task.listener(userMap,dataTable);
        }else {
            //每日任务的所有任务都在监听这个事件
            for(Map.Entry<Integer,ITask> e : taskMap.entrySet()){
                int id = e.getKey();
                dataTable = TaskDataTable.get(taskId);
                if(dataTable == null)
                    new GenaryAppError(AppErrorCode.SERVER_ERR);
                ITask task1 = e.getValue();
                if(id == 1)
                    continue;
                task1.listener(userMap,dataTable);
            }
        }
    }
}
