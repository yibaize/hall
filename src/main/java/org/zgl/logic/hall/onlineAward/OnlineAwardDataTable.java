package org.zgl.logic.hall.onlineAward;

import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;

@DataTable
public class OnlineAwardDataTable implements DataTableMessage {
    private final int id;
    private final int awardId;
    private final int num;
    public OnlineAwardDataTable() {
        this.id = 0;
        this.awardId = 0;
        this.num = 0;
    }

    public int getId() {
        return id;
    }

    public int getAwardId() {
        return awardId;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int id() {
        return id;
    }
    public static OnlineAwardDataTable get(int id){
        return StaticConfigMessage.getInstance().get(OnlineAwardDataTable.class,id);
    }
    @Override
    public void AfterInit() {

    }
}
