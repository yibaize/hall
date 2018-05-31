package org.zgl.utils.executer;

import org.zgl.jetty.operation.OperateCommandAbstract;

import java.util.concurrent.Executor;

/**
 * @作者： big
 * @创建时间： 2018/5/19
 * @文件描述：
 */
public class ServerWorker extends AbstractSelector implements Worker {
    ServerWorker(Executor executor, String threadName, SelectorRunnablePool selectorRunnablePool) {
        super(executor, threadName, selectorRunnablePool);
    }

    /**
     * 接受到客户端消息组装成用户 之后加进来 这里其实可以不加的了
     * @param runnable
     */
    @Override
    public void registerNewChannelTask(Runnable runnable) {
        //加入一个新用户
        registerTask(runnable);
    }
}
