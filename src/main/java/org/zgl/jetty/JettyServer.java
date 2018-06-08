package org.zgl.jetty;

import org.eclipse.jetty.server.Server;
import org.zgl.utils.logger.LoggerUtils;

/**
 * @作者： big
 * @创建时间： 2018/5/18
 * @文件描述：
 */
public class JettyServer {
    public static void start() throws Exception {
        //客户端 8080 自己 5050
        final int port = 8080;
        Server server = new Server(port);
        server.setHandler(new MyHandler());
        // 启动服务器
        server.start();
        LoggerUtils.getLogicLog().info("端口："+port+"绑定成功");
        server.join();
    }
}
