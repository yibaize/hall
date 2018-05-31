package org.zgl.jetty;

import org.eclipse.jetty.server.Server;

/**
 * @作者： big
 * @创建时间： 2018/5/18
 * @文件描述：
 */
public class JettyServer {
    public static void start() throws Exception {
        Server server = new Server(8080);
        server.setHandler(new MyHandler());
        // 启动服务器
        server.start();
        server.join();
    }
}
