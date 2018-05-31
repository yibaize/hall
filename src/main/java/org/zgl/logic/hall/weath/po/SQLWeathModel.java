package org.zgl.logic.hall.weath.po;


import org.zgl.logic.hall.shop.ShopEnum;
import org.zgl.logic.hall.weath.dto.WeathDto;
import org.zgl.orm.core.Query;
import org.zgl.orm.core.QueryFactory;
import org.zgl.orm.po.User;
import org.zgl.player.UserMap;
import org.zgl.utils.builder_clazz.ann.OverlookField;

import java.util.ArrayList;
import java.util.List;
public class SQLWeathModel {
    private long gold;
    private long diamond;
    private int vipLv;
    /**vip当前等级经验*/
    private long vipExp;
    /**积分*/
    private long integral;
    /**座驾 对应商城id*/
    private List<ResourceModel> autos;
    /**当前使用座驾id*/
    private int auto;
    /**礼物*/
    private List<ResourceModel> gifts;
    /**道具*/
    private List<ResourceModel> props;
    /**摇钱树*/
    @OverlookField("忽略生成字段")
    private SQLMoenyTree moneyTree;
    /**礼物值*/
    private long giftNum;
    /**人品值*/
    private long characterNum;
    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getDiamond() {
        return diamond;
    }

    public void setDiamond(long diamond) {
        this.diamond = diamond;
    }

    public int getVipLv() {
        return vipLv;
    }

    public void setVipLv(int vipLv) {
        this.vipLv = vipLv;
    }

    public long getVipExp() {
        return vipExp;
    }

    public void setVipExp(long vipExp) {
        this.vipExp = vipExp;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public List<ResourceModel> getAutos() {
        if(autos == null)
            autos = new ArrayList<>(0);
        return autos;
    }

    public void setAutos(List<ResourceModel> autos) {
        this.autos = autos;
    }

    public List<ResourceModel> getGifts() {
        if(gifts == null)
            gifts = new ArrayList<>(0);
        return gifts;
    }

    public void setGifts(List<ResourceModel> gifts) {
        this.gifts = gifts;
    }

    public List<ResourceModel> getProps() {
        if(props == null)
            props = new ArrayList<>(0);
        return props;
    }

    public void setProps(List<ResourceModel> props) {
        this.props = props;
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }

    public long getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(long giftNum) {
        this.giftNum = giftNum;
    }

    public long getCharacterNum() {
        return characterNum;
    }

    public void setCharacterNum(long characterNum) {
        this.characterNum = characterNum;
    }

    public SQLMoenyTree getMoneyTree() {
        if(moneyTree == null)
            moneyTree = new SQLMoenyTree();
        return moneyTree;
    }

    public void setMoneyTree(SQLMoenyTree moneyTree) {
        this.moneyTree = moneyTree;
    }

    /**
     *
     * @param shopEnum 商城id
     * @param CommondiryId 物品id
     * @param count
     */
    public void addResource(ShopEnum shopEnum, int CommondiryId, int count){
        if(count <= 0)
            return;
        switch (shopEnum){
            case GOLD:
                gold += count;
                break;
            case AUTO:
                checkAndAddResource(autos,CommondiryId,shopEnum.id(),count);
                break;
            case PROP:
                checkAndAddResource(props,CommondiryId,shopEnum.id(),count);
                break;
        }
    }


    /**
     * 添加资源
     * @param models
     * @param commondiryId
     * @param shopId
     * @param count
     * @return
     */
    private boolean checkAndAddResource(List<ResourceModel> models,int commondiryId,int shopId,int count){
        for(ResourceModel model:models){
            if(model.getId() == commondiryId){
                model.setCount(model.getCount() + count);
                return true;
            }
        }
        models.add(new ResourceModel(commondiryId,shopId,count));
        return false;
    }

    /**
     * 赠送他人礼物
     * @param shopEnum
     * @param CommondiryId
     * @param count
     */
    public boolean sendGift(ShopEnum shopEnum,int CommondiryId,int count){
        if(count <= 0)
            return false;
        switch (shopEnum){
            case AUTO:
                return checkAddSendGif(autos,CommondiryId,count);
            case PROP:
                return checkAddSendGif(props,CommondiryId,count);
                default:
                    return false;
        }
    }
    private boolean checkAddSendGif(List<ResourceModel> models,int CommondiryId,int count){
        for(ResourceModel model:models){
            if(model.getId() == CommondiryId && model.getCount() >= count){
                model.setCount(model.getCount() - count);
                return true;
            }
        }
        return false;
    }
    /**
     * 添加积分
     */
    public void addIntegral(long count){
        integral += count;
    }

    /**
     * 检查并减少积分
     * @param num
     * @return
     */
    public boolean reduceIntegral(long num){
        if(integral >= num) {
            integral -= num;
            return true;
        }
        return false;
    }
    /**
     * 减少金币
     * @param count
     * @return
     */
    public boolean reduceGold(long count){
        if(gold >= count) {
            gold -= count;
            return true;
        }
        return false;
    }
    /**
     * 减少钻石
     * @param count
     * @return
     */
    public boolean reduceDiamond(long count){
        if(diamond >= count) {
            diamond -= count;
            return true;
        }
        return false;
    }
    public void update(UserMap um, boolean isNotify){
        Query query = QueryFactory.createQuery();
        User u = um.entity2map();
        query.update(u,new String[]{"weath"});
//        if(isNotify && um.getSession().isConnected()) {
////            WeathResourceDto dto = new WeathResourceDto(gold, diamond, integral);
////            byte[] buf = ProtostuffUtils.serializer(dto);
////            Response response = new Response(NotifyCode.WEATH_XCHANGE, buf);
////            um.getSession().write(response);
//        }
    }
    public void addGoldOrDiamond(int id,long count){
        if(id == 1)
            gold += count;
        else if(id == 48)
            diamond += count;
    }
    public WeathDto weathDto(){
        WeathDto dto = new WeathDto();
        dto.setAutos(autos);
        dto.setGifts(getGifts());
        dto.setProps(props);
        dto.setVipExp(vipExp);
        return dto;
    }
    public boolean autoUse(int id){
        if(autos.contains(id)) {
            auto = id;
            return true;
        }
        return false;
    }
    public void addResouce(long gold,long diamond,long integral){
        this.gold += gold;
        this.diamond += diamond;
        this.integral += integral;
    }
    public void updateResource(long gold,long diamond,long integral){
        this.gold = gold;
        this.diamond = diamond;
        this.integral = integral;
    }
}
