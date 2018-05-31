package org.zgl.logic.hall.onlineAward;

import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;

@DataTable
public class OnlineAwardTimerDataTable implements DataTableMessage {
    private final int id;
    private final int timer;
    public OnlineAwardTimerDataTable() {
        this.id = 0;
        this.timer = 0;
    }

    public int getId() {
        return id;
    }

    public int getTimer() {
        return timer;
    }

    public static OnlineAwardTimerDataTable get(int id){
        return StaticConfigMessage.getInstance().get(OnlineAwardTimerDataTable.class,id);
    }
    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
