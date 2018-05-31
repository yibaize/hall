package org.zgl;

import org.zgl.jetty.JettyServer;
import org.zgl.utils.builder_clazz.excel_init_data.ExcelUtils;
import org.zgl.utils.logger.LoggerUtils;

/**
 * @作者： big
 * @创建时间： 2018/5/19
 * @文件描述：
 */
public class GameMain {
    public static void main(String[] args) throws Exception {
        LoggerUtils.getPlatformLog().warn("静态数据加载...");
        ExcelUtils.init("excel");
        LoggerUtils.getPlatformLog().warn("------------------------------------静态数据加载成功---------------------------------");
//        HttpInit.getInstance().start();
        LoggerUtils.getPlatformLog().warn("大厅服务器启动...");
        JettyServer.start();
        LoggerUtils.getPlatformLog().warn("------------------------------------大厅服务器启动成功---------------------------------");
    }
}
