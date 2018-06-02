package org.zgl.orm;

import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;

public class ORMTest {
    public static void main(String[] args) {
        QueryFactory.initDatabases();
        Query q = QueryFactory.createQuery();
//        User u = new User();
//        u.setAccount("123456");
//        u.setPassword("123456");
//        u.setGiftBag(1);
//        u.setDiamond(10000L);
//        u.setGold(1000000L);
//        u.setHeadIcon("aaaaaaa");
//        u.setUsername("用户1");
//        u.setVip(10);
//        u.setLoginType(1);
//        q.insert(u);
//        User us = (User) q.queryUniqueRow("SELECT * FROM user WHERE account=?",User.class,new Object[]{"12323456"});
    }
}
