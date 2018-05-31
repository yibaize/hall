package org.zgl.logic.hall.siginin.dto;


import org.zgl.utils.builder_clazz.ann.Protostuff;

@Protostuff
public class FirstBuyAwardDto {
    private int id;
    private int count;

    public FirstBuyAwardDto() {
    }

    public FirstBuyAwardDto(int id, int count) {
        this.id = id;
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
}
