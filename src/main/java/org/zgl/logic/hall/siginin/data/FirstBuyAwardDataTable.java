package org.zgl.logic.hall.siginin.data;


import org.zgl.utils.builder_clazz.ann.DataTable;

@DataTable
public class FirstBuyAwardDataTable {
    private int id;
    private int shopId;
    private int count;
    public FirstBuyAwardDataTable() {
    }

    public FirstBuyAwardDataTable(int id, int shopId, int count) {
        this.id = id;
        this.shopId = shopId;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
