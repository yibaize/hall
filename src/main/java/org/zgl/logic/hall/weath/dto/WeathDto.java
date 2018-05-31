package org.zgl.logic.hall.weath.dto;


import org.zgl.logic.hall.weath.po.ResourceModel;
import org.zgl.utils.builder_clazz.ann.Protostuff;

import java.util.List;
@Protostuff
public class WeathDto {
    /**vip当前等级经验*/
    private long vipExp;
    /**座驾 对应商城id*/
    private List<ResourceModel> autos;
    /**礼物*/
    private List<ResourceModel> gifts;
    /**道具*/
    private List<ResourceModel> props;

    public WeathDto() {
    }

    public long getVipExp() {
        return vipExp;
    }

    public void setVipExp(long vipExp) {
        this.vipExp = vipExp;
    }


    public List<ResourceModel> getAutos() {
        return autos;
    }

    public void setAutos(List<ResourceModel> autos) {
        this.autos = autos;
    }

    public List<ResourceModel> getGifts() {
        return gifts;
    }

    public void setGifts(List<ResourceModel> gifts) {
        this.gifts = gifts;
    }

    public List<ResourceModel> getProps() {
        return props;
    }

    public void setProps(List<ResourceModel> props) {
        this.props = props;
    }
}
