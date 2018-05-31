package org.zgl.logic.hall.siginin.data;

import org.zgl.utils.builder_clazz.ann.DataTable;
import org.zgl.utils.builder_clazz.excel_init_data.DataTableMessage;
import org.zgl.utils.builder_clazz.excel_init_data.StaticConfigMessage;

@DataTable
public class SignInDataTable implements DataTableMessage {
    private final int id;
    private final long gold;
    private final long diamond;

    public SignInDataTable() {
        id = 0;
        gold = 0L;
        diamond = 0L;
    }
    public static SignInDataTable get(int id){
        return StaticConfigMessage.getInstance().get(SignInDataTable.class,id);
    }
    public int getId() {
        return id;
    }

    public long getGold() {
        return gold;
    }

    public long getDiamond() {
        return diamond;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public void AfterInit() {

    }
}
