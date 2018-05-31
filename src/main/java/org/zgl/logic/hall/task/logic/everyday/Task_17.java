package org.zgl.logic.hall.task.logic.everyday;

import org.zgl.logic.hall.task.TaskDataTable;
import org.zgl.logic.room_connection.IntoRoom;
import org.zgl.player.UserMap;

/**
 * 中级场赢10局
 */
public class Task_17 extends EveryDayTaskAbs {
    @Override
    public boolean listener(UserMap userMap, TaskDataTable dataTable) {
        //如果不是中级场则无效
        if(userMap.getScenesId() != IntoRoom.ScenesEnum.ROOM_TWO.id())
            return false;
        return atWill(userMap,dataTable);
    }
}
