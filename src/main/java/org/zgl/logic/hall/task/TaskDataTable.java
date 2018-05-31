package org.zgl.logic.hall.task;

import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;

@DataTable
public class TaskDataTable implements DataTableMessage {
    private final int id;
    /**任务类型*/
    private final int taskType;
    /**描述*/
    private final String describe;
    /**奖励*/
    private final int award;
    private final int taskLimit;

    public TaskDataTable() {
        this.id = 0;
        this.taskType = 0;
        this.describe = "";
        this.award = 0;
        this.taskLimit = 0;
    }
    public static TaskDataTable get(int id){
        return StaticConfigMessage.getInstance().get(TaskDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public int getTaskType() {
        return taskType;
    }

    public String getDescribe() {
        return describe;
    }

    public int getAward() {
        return award;
    }

    public int getTaskLimit() {
        return taskLimit;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
