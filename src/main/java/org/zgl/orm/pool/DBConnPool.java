package org.zgl.orm.pool;


import org.zgl.orm.core.DBManager;
import org.zgl.utils.logger.LoggerUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnPool {
    /**
     * 连接池对象
     */
    private List<Connection> pool;

    /**
     * 最大连接数
     */
    private static final int POOL_MAX_SIZE = DBManager.getConfig().getPoolMaxSize();
    /**
     * 最小连接池
     */
    private static final int POOL_MIN_SIZE = DBManager.getConfig().getPoolMinSize();


    /**
     * 初始化连接池，使池中的连接数达到最小值
     */
    public void initPool() {
        if (pool == null) {
            pool = new ArrayList<Connection>();
        }

        while (pool.size() < DBConnPool.POOL_MIN_SIZE) {
            pool.add(DBManager.createConn());
            System.out.println("初始化池，池中连接数：" + pool.size());
        }
    }


    /**
     * 从连接池中取出一个连接
     *
     * @return
     */
    public synchronized Connection getConnection() {
        int last_index = pool.size() - 1;
        Connection conn = pool.get(last_index);
        pool.remove(last_index);

        return conn;
    }

    /**
     * 将连接放回池中
     *
     * @param conn
     */
    public synchronized void close(Connection conn) {

        if (pool.size() >= POOL_MAX_SIZE) {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LoggerUtils.getPlatformLog().error("将数据库链接放回池中时异常",e);
            }
        } else {
            pool.add(conn);
        }
    }


    public DBConnPool() {
        initPool();
    }
}