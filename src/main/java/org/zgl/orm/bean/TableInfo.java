package org.zgl.orm.bean;

import java.util.List;
import java.util.Map;

public class TableInfo {
    /**表名*/
    private String tname;
    /**字段信息*/
    private Map<String,ColumnInfo> columns;
    /**唯一主键*/
    private ColumnInfo onlyPriKey;
    /**联合主键*/
    private List<ColumnInfo> priKeys;

    public TableInfo() {
    }

    public TableInfo(String tname, Map<String, ColumnInfo> columns, List<ColumnInfo> priKeys) {
        this.tname = tname;
        this.columns = columns;
        this.priKeys = priKeys;
    }

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }

    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }
}