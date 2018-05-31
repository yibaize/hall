package org.zgl.logic.hall.vip.dto;


import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class VIPDto {
    private int vipLv;
    private long vipExp;

    public VIPDto() {
    }

    public VIPDto(int vipLv, long vipExp) {
        this.vipLv = vipLv;
        this.vipExp = vipExp;
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
}
