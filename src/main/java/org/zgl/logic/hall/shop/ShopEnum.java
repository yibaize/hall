package org.zgl.logic.hall.shop;

import java.util.HashMap;
import java.util.Map;

public enum ShopEnum {
    NENO(0,"没有"),
    GOLD(1,"金币商城"),
    AUTO(2,"座驾"),
    PROP(3,"道具"),
    VIP(4,"vip"),
    EXCHANGE(5,"兑换"),
    FIRST_BUY(100,"首充"),
    MONEY_TREE(200,"摇钱树");
    private int id;
    private String name;
    private static final Map<Integer,ShopEnum> map;
    static {
        map = new HashMap<>(6);
        for(ShopEnum e:ShopEnum.values()){
            map.putIfAbsent(e.id,e);
        }
    }
    ShopEnum(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public int id(){
        return id;
    }
    public String getNmae(){
        return name;
    }
    public static ShopEnum getEnum(int i){
        return map.get(i);
    }
}
