package org.zgl.orm.core;


import org.zgl.orm.bean.Configration;
import org.zgl.orm.pool.DBConnPool;
import org.zgl.utils.logger.LoggerUtils;

import java.sql.*;
import java.util.Properties;

public class DBManager {
    private static Configration conf;
    private static DBConnPool pool;
    static {
        Properties pros = new Properties();
        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dp.properties"));
        } catch (Exception e) {
            LoggerUtils.getPlatformLog().error(e);
        }
        conf = new Configration();
        conf.setDriver(pros.getProperty("driver"));
        conf.setUrl(pros.getProperty("url"));
        conf.setUser(pros.getProperty("user"));
        conf.setPwd(pros.getProperty("pwd"));
        conf.setSrcPath(pros.getProperty("srcPath"));
        conf.setPoPackage(pros.getProperty("poPackage"));
        conf.setUsingDB(pros.getProperty("usingDB"));
        conf.setQueryClass(pros.getProperty("queryClass"));
        conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
        conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));

    }
    /**
     * 获得Connection对象
     * @return
     */
    public static Connection getConnection(){
        if(pool==null){
            pool = new DBConnPool();
        }
        return pool.getConnection();
    }
    /**
     * 创建新的Connection对象
     * @return
     */
    public static Connection createConn(){
        try {
            Class.forName(conf.getDriver());
            return DriverManager.getConnection(conf.getUrl(),
                    conf.getUser(),conf.getPwd());     //直接建立连接，后期增加连接池处理，提高效率！！！
        } catch (Exception e) {
            LoggerUtils.getPlatformLog().error("创建数据库链接是异常",e);
            return null;
        }
    }
    public static Configration getConfig(){
        return conf;
    }
    public static void close(Connection conn){
        pool.close(conn);
    }
    /**
     * 关闭传入的ResultSet、Statement、Connection对象
     * @param rs
     * @param ps
     * @param conn
     */
    public static void close(ResultSet rs, Statement ps, Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
            LoggerUtils.getPlatformLog().error("关闭数据库链接是异常",e);
        }
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
            LoggerUtils.getPlatformLog().error("关闭数据库链接是异常",e);
        }
        pool.close(conn);
    }

    /**
     * 关闭传入的Statement、Connection对象
     * @param ps
     * @param conn
     */
    public static void close(Statement ps,Connection conn){
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
            LoggerUtils.getPlatformLog().error("关闭数据库链接是异常",e);
        }
        pool.close(conn);
    }
}