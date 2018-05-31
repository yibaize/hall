package org.zgl.logic.hall.task.po;

import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class TaskModel {
    /**任务id*/
    private String taskId;
    /**是否已经完成 */
    private boolean hasDone;
    /**是否已经领取奖励*/
    private boolean hasAward;
    /**任务状态*/
    private int taskState;
    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    public boolean isHasAward() {
        return hasAward;
    }

    public void setHasAward(boolean hasAward) {
        this.hasAward = hasAward;
    }
    public void insertTaskState(){
        taskState++;
    }

}
