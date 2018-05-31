package org.zgl.utils.executer;

import org.zgl.jetty.operation.OperateCommandAbstract;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
/**
 * @作者： big
 * @创建时间： 2018/5/19
 * @文件描述：抽象selector线程类
 */
public abstract class AbstractSelector implements Runnable{
    /**
     * 线程池
     */
    private final Executor executor;

    /**
     * 任务队列
     */
    private final Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    /**
     * 线程名称
     */
    private String threadName;
    /**
     * 线程管理对象
     */
    protected SelectorRunnablePool selectorRunnablePool;
    AbstractSelector(Executor executor, String threadName, SelectorRunnablePool selectorRunnablePool) {
        this.executor = executor;
        this.threadName = threadName;
        this.selectorRunnablePool = selectorRunnablePool;
        openSelector();
    }
    /**
     * 获取selector并启动线程
     */
    private void openSelector() {
        executor.execute(this);
    }
    @Override
    public void run() {
        Thread.currentThread().setName(this.threadName);
        while (true) {
            try {
//                select();

                processTaskQueue();

            } catch (Exception e) {
            }
        }

    }
    /**
     * 注册一个任务并激活selector
     *
     * @param task
     */
    protected final void registerTask(Runnable task) {
        taskQueue.add(task);
    }
    /**
     * select抽象方法
     *
     * @param
     * @return
     * @throws
     */
//    protected abstract int select();
    /**
     * 执行队列里的任务
     */
    private void processTaskQueue() {
        for (;;) {
            final Runnable task = taskQueue.poll();
            if (task == null) {
                break;
            }
            task.run();
        }
    }
    /**
     * 收到用户操作请求操作 然后将操作加入线程
     *
     * @param
     * @throws
     */
//    protected void process(){
//    }
}
