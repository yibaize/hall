package org.zgl.logic.hall.task.manager;

import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.player.UserMap;

public interface ITask {
    boolean listener(UserMap userMap, TaskDataTable dataTable);
    void getAward(UserMap userMap, int taskId);
    void update(UserMap userMap);
}
