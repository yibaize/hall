package org.zgl.logic.hall.task.po;


import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;

public class SQLTaskModel {
    /**系统任务已经完成的任务*/
    private Map<String,TaskModel> systemTask;
    /**每日任务 已经完成的任务*/
    private Map<String,TaskModel> everyDayTask;
    /**每日任务计时器*/
    private int time;
    public SQLTaskModel() {
        systemTask = new HashMap<>();
        everyDayTask = new HashMap<>();
    }

    public Map<String, TaskModel> getSystemTask() {
        return systemTask;
    }

    public void setSystemTask(Map<String, TaskModel> systemTask) {
        this.systemTask = systemTask;
    }

    public Map<String, TaskModel> getEveryDayTask() {
        return everyDayTask;
    }

    public void setEveryDayTask(Map<String, TaskModel> everyDayTask) {
        this.everyDayTask = everyDayTask;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public TaskModel task(int taskType, int id){
        switch (taskType){
            case 1:
                return systemTask.getOrDefault(id,null);
            case 2:
                return everyDayTask.getOrDefault(id,null);
        }
        return null;
    }
    public void doneTask(int taskType, String id){
        switch (taskType){
            case 1:
                TaskModel tm = systemTask.getOrDefault(id,null);
                if(tm != null)
                    new GenaryAppError(AppErrorCode.DATA_ERR);
                tm = new TaskModel();
                tm.setHasDone(true);
                systemTask.put(id,tm);
                break;
            case 2:
                break;
        }
    }
    public void systemTaskListener(String taskId){
        TaskModel tm = systemTask.getOrDefault(taskId,null);
        if(tm != null)
            new GenaryAppError(AppErrorCode.DATA_ERR);
        tm = new TaskModel();
        tm.setHasDone(true);
        systemTask.put(taskId,tm);
    }
    public TaskModel getTaskModel(int taskType,String taskId){
        TaskModel tm = null;
        switch (taskType){
            case 1:
                tm = systemTask.getOrDefault(taskId,null);
                if(tm == null) {
                    tm = new TaskModel();
                    tm.setTaskId(taskId);
                    systemTask.put(taskId,tm);
                }
                break;
            case 2:
                //每日清0
                int day = DateUtils.currentDay();
                if(time != day){
                    everyDayTask.clear();
                    time = day;
                }
                tm = everyDayTask.getOrDefault(taskId,null);
                if(tm == null) {
                    tm = new TaskModel();
                    tm.setTaskId(taskId);
                    everyDayTask.put(taskId,tm);
                }
                break;
        }

        return tm;
    }

    @Override
    public String toString() {
        return "SQLTaskModel{" +
                "systemTask=" + systemTask +
                ", everyDayTask=" + everyDayTask +
                ", time=" + time +
                '}';
    }
}
