package org.zgl.logic.hall.task.logic.everyday;

import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.player.UserMap;

public class Task_19 extends EveryDayTaskAbs {
    @Override
    public boolean listener(UserMap userMap, TaskDataTable dataTable) {
        return atWill(userMap,dataTable);
    }
}
