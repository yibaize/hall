package org.zgl.logic.hall.onlineAward;


import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class DialDto {
    private int position;
    private int awardId;
    public DialDto() {
    }

    public DialDto(int position, int awardId) {
        this.position = position;
        this.awardId = awardId;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
