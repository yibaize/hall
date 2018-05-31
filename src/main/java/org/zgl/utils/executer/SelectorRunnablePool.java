package org.zgl.utils.executer;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @作者： big
 * @创建时间： 2018/5/19
 * @文件描述：线程池管理类
 */
public class SelectorRunnablePool {
    /**
     * worker线程数组
     */
    private final AtomicInteger workerIndex = new AtomicInteger();
    private Worker[] workeres;
    public SelectorRunnablePool(Executor worker, int count) {
        int c = count == 0 ? Runtime.getRuntime().availableProcessors() * 2 : count;
        initWorker(worker, c);
    }
    /**
     * 初始化worker线程
     * @param worker
     * @param count
     */
    private void initWorker(Executor worker, int count) {
        this.workeres = new ServerWorker[count];
        for (int i = 0; i < workeres.length; i++) {
            workeres[i] = new ServerWorker(worker, "worker thread " + (i+1), this);
        }
    }
    /**
     * 获取一个boss
     * @return
     */
    public Worker nextWorker() {
        return workeres[Math.abs(workerIndex.getAndIncrement() % workeres.length)];
    }
}
