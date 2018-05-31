package org.zgl.logic.hall.task;
import org.zgl.logic.hall.task.po.TaskModel;
import org.zgl.utils.builder_clazz.ann.Protostuff;

import java.util.List;
@Protostuff
public class TaskListDto {
    private List<TaskModel> systemTasks;
    private List<TaskModel> everyDayTasks;

    public TaskListDto() {
    }

    public TaskListDto(List<TaskModel> systemTasks, List<TaskModel> everyDayTasks) {
        this.systemTasks = systemTasks;
        this.everyDayTasks = everyDayTasks;
    }

    public List<TaskModel> getSystemTasks() {
        return systemTasks;
    }

    public void setSystemTasks(List<TaskModel> systemTasks) {
        this.systemTasks = systemTasks;
    }

    public List<TaskModel> getEveryDayTasks() {
        return everyDayTasks;
    }

    public void setEveryDayTasks(List<TaskModel> everyDayTasks) {
        this.everyDayTasks = everyDayTasks;
    }
}
