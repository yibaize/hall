package org.zgl.orm.bean;

public class Configration {
    private String user;
    private String pwd;
    private String driver;
    private String url;
    private int poolMaxSize;
    private int poolMinSize;
    /**使用哪个数据库*/
    private String usingDB;
    /**源码路径*/
    private String srcPath;
    /**生成的源码在哪个包*/
    private String poPackage;
    private String queryClass;
    public Configration() {
    }

    public Configration(String user, String pwd, String driver, String url, int poolMaxSize, int poolMinSize, String usingDB, String srcPath, String poPackage, String queryClass) {
        this.user = user;
        this.pwd = pwd;
        this.driver = driver;
        this.url = url;
        this.poolMaxSize = poolMaxSize;
        this.poolMinSize = poolMinSize;
        this.usingDB = usingDB;
        this.srcPath = srcPath;
        this.poPackage = poPackage;
        this.queryClass = queryClass;
    }

    public int getPoolMaxSize() {
        return poolMaxSize;
    }

    public void setPoolMaxSize(int poolMaxSize) {
        this.poolMaxSize = poolMaxSize;
    }

    public int getPoolMinSize() {
        return poolMinSize;
    }

    public void setPoolMinSize(int poolMinSize) {
        this.poolMinSize = poolMinSize;
    }

    public String getQueryClass() {
        return queryClass;
    }

    public void setQueryClass(String queryClass) {
        this.queryClass = queryClass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsingDB() {
        return usingDB;
    }

    public void setUsingDB(String usingDB) {
        this.usingDB = usingDB;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getPoPackage() {
        return poPackage;
    }

    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }
}