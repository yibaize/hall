package org.zgl.utils;

import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.AdminInfo;

public class IDFactory {
    private static int inInit = 0;
    private static final Object lock = new Object();
    private static int tag = 0;
    private static int today = DateUtils.currentDay();
    private final static Query query = QueryFactory.createQuery();
    public static int getId(){
        synchronized (lock) {
            int day = DateUtils.currentDay();
            String idInitStr = "" + (day - 20000000);
            if(tag == 0){
                //说明重启了
                //select max(id) from tablename
                AdminInfo info = (AdminInfo) query.queryUniqueRow("SELECT * FROM adminInfo WHERE id=(SELECT MAX(id) FROM adminInfo)",
                        AdminInfo.class,null);
                if(info != null){
                    inInit = info.getRegistNum();
                }
            }
            if (today != day || inInit >= 9999) {
                inInit = 1;
                today = day;
            }
            inInit++;
            if (inInit < 10) {
                idInitStr += "00" + inInit;
            } else if (inInit >= 10 && inInit < 100) {
                idInitStr += "0" + inInit;
            } else if (inInit >= 100) {
                idInitStr += inInit;
            }
            tag++;
            return Integer.parseInt(idInitStr);
        }
    }

    public static void main(String[] args) {
//        for(int i = 0;i<1500;i++) {
//            System.out.println(getId());
//        }
    }
}
