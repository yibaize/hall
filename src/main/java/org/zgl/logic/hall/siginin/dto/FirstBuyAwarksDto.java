package org.zgl.logic.hall.siginin.dto;


import org.zgl.utils.builder_clazz.ann.Protostuff;

import java.util.List;

@Protostuff
public class FirstBuyAwarksDto {
    private List<FirstBuyAwardDto> award;

    public FirstBuyAwarksDto() {
    }

    public FirstBuyAwarksDto(List<FirstBuyAwardDto> award) {
        this.award = award;
    }

    public List<FirstBuyAwardDto> getAward() {
        return award;
    }

    public void setAward(List<FirstBuyAwardDto> award) {
        this.award = award;
    }
}
