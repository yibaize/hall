package org.zgl.jetty.session;

import org.zgl.player.UserMap;
import org.zgl.utils.DateUtils;
import org.zgl.utils.logger.LoggerUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public class SessionManager {
    private static final ConcurrentHashMap<String, UserMap> onlineSessions = new ConcurrentHashMap<>();
    public static boolean putSession(String playerId,UserMap userMap){
        boolean success = false;
        if(!onlineSessions.containsKey(playerId))
            success = onlineSessions.putIfAbsent(playerId,userMap) == null ? true : false;
        LoggerUtils.getLogicLog().info(playerId + "添加成功"+" : "+success);
        return success;
    }
    public static UserMap removeSession(String playerId){
        LoggerUtils.getLogicLog().info("正在删除"+" : ");
        if(!onlineSessions.containsKey(playerId)) return null;
        UserMap session = onlineSessions.remove(playerId);
        LoggerUtils.getLogicLog().info("删除成功"+" : ");
        return session;
    }
    public static UserMap getSession(String account){
        return onlineSessions.getOrDefault(account,null);
    }
    /**
     * 是否在线
     * @param playerId
     * @return
     */
    public static boolean isOnlinePlayer(String playerId){
        return onlineSessions.containsKey(playerId);
    }

    /**
     * 获取所有在线玩家
     * @return
     */
    public static Set<String> onlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
    public static int onLinePlayerNum(){
        return onlineSessions == null ? 0 : onlineSessions.size();
    }
    public static Map<String,UserMap> map(){
        return onlineSessions;
    }
    static class FutureThread implements Runnable{
        @Override
        public void run() {
            Set<String> accounts = new HashSet<>();
            for(Map.Entry<String,UserMap> e : onlineSessions.entrySet()){
                if(DateUtils.currentDay() - e.getValue().getLoginTime() >= 1800000){
                    accounts.add(e.getKey());
                }
            }
            //长时间不在线系统自动删除
            Iterator<String> iterator = accounts.iterator();
            while (iterator.hasNext()){
                String account = iterator.next();
                onlineSessions.remove(account);
            }
        }
    }
    static {
        FutureThread future = new FutureThread();
        Thread t = new Thread(future);
        t.setDaemon(true);//设置为守护线程
        t.start();
    }
}
