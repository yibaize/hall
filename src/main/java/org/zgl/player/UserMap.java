package org.zgl.player;


import org.zgl.logic.hall.frineds.po.SQLFrinedsModel;
import org.zgl.logic.hall.giftBag.po.SQLGiftBagModel;
import org.zgl.logic.hall.siginin.po.SQLSignInModel;
import org.zgl.logic.hall.task.po.SQLTaskModel;
import org.zgl.logic.hall.weath.po.SQLWeathModel;
import org.zgl.logic.login.po.SQLUserBaseInfo;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.Db_user;
import org.zgl.utils.DateUtils;
import org.zgl.utils.JsonUtils;
import org.zgl.utils.executer.Worker;

public class UserMap {
    private int scenesId;
    private int id;
    private String account;
    private String password;
    private SQLUserBaseInfo baseInfo;
    private SQLSignInModel signIn;
    private SQLWeathModel weath;
    private SQLFrinedsModel friends;
    private SQLGiftBagModel giftBag;
    private SQLTaskModel task;
    private long loginTime;
    private Worker worker;
    public UserMap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SQLUserBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(SQLUserBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public SQLGiftBagModel getGiftBag() {
        return giftBag;
    }

    public void setGiftBag(SQLGiftBagModel giftBag) {
        this.giftBag = giftBag;
    }

    public SQLSignInModel getSignIn() {
        return signIn;
    }

    public void setSignIn(SQLSignInModel signIn) {
        this.signIn = signIn;
    }

    public SQLWeathModel getWeath() {
        return weath;
    }

    public void setWeath(SQLWeathModel weath) {
        this.weath = weath;
    }

    public SQLFrinedsModel getFriends() {
        return friends;
    }

    public void setFriends(SQLFrinedsModel friends) {
        this.friends = friends;
    }

    public SQLTaskModel getTask() {
        return task;
    }

    public void setTask(SQLTaskModel task) {
        this.task = task;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public int getScenesId() {
        return scenesId;
    }

    public void setScenesId(int scenesId) {
        this.scenesId = scenesId;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /**
     * 实体转换成映射类
     * @return 数据库存储类
     */
    public Db_user entity2map(){
        Db_user u = new Db_user();
        u.setId(id);
        u.setAccount(account);
        u.setPassword(password);
        u.setBaseInfo(JsonUtils.jsonSerialize(baseInfo));
        u.setWeath(JsonUtils.jsonSerialize(weath));
        u.setFriends(JsonUtils.jsonSerialize(friends));
        u.setSignIn(JsonUtils.jsonSerialize(signIn));
        u.setGiftBag(JsonUtils.jsonSerialize(giftBag));
        u.setTask(JsonUtils.jsonSerialize(task));
        return u;
    }

    /**
     * 将数据库类换成实体类
     * @return
     */
    public UserMap map2entity(Db_user u){
        this.id = u.getId();
        this.account = u.getAccount();
        this.password = u.getPassword();
        this.baseInfo = JsonUtils.jsonDeserialization(u.getBaseInfo(),SQLUserBaseInfo.class);
        this.signIn = JsonUtils.jsonDeserialization(u.getSignIn(),SQLSignInModel.class);
        this.weath = JsonUtils.jsonDeserialization(u.getWeath(),SQLWeathModel.class);
        this.friends = JsonUtils.jsonDeserialization(u.getFriends(),SQLFrinedsModel.class);
        this.giftBag = JsonUtils.jsonDeserialization(u.getGiftBag(),SQLGiftBagModel.class);
        this.task = JsonUtils.jsonDeserialization(u.getTask(),SQLTaskModel.class);
        this.loginTime = DateUtils.currentTime();
        return this;
    }
    public UserMap systemUser(){
        this.id = -999999999;
        this.account = "-999999999";
        this.password = "-999999999";
        this.baseInfo = new SQLUserBaseInfo(0,"房间账号链接","a","妹子","个人签名","联系方式",0,0);
        return this;
    }
    public void update(String[] args){
        Query query = QueryFactory.createQuery();
        query.update(entity2map(),args);
    }
}
