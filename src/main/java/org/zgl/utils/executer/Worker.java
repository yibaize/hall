package org.zgl.utils.executer;


/**
 * @作者： big
 * @创建时间： 2018/5/19
 * @文件描述：
 */
public interface Worker {
    /**
     * 加入一个新的客户端会话
     * @param
     */
    void registerNewChannelTask(Runnable runnable);
}
