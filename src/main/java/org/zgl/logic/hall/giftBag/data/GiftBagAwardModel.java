package org.zgl.logic.hall.giftBag.data;

/**
 * 奖励模型
 */
public class GiftBagAwardModel {
    /**物品对应的商城id*/
    private int shopId;
    /**物品的id*/
    private int commondity;
    /**奖励的数量*/
    private int count;

    public GiftBagAwardModel() {
    }

    public GiftBagAwardModel(int shopId, int commondity, int count) {
        this.shopId = shopId;
        this.commondity = commondity;
        this.count = count;
    }
    public int getShopId() {
        return shopId;
    }
    public int getCommondity() {
        return commondity;
    }
    public int getCount() {
        return count;
    }
}
