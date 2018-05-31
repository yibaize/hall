package org.zgl.orm.core;

public class MySqlTypeConvertor implements TypeConvertor {
    @Override
    public String databaseType2JavaType(String columnType) {
        if(columnType.equalsIgnoreCase("varchar") || columnType.equalsIgnoreCase("char"))
            return "String";
        else if(columnType.equalsIgnoreCase("int") ||
                columnType.equalsIgnoreCase("tinyint") ||
                columnType.equalsIgnoreCase("smallint"))
            return "Integer";
        else if(columnType.equalsIgnoreCase("bigint"))
            return "Long";
        else if(columnType.equalsIgnoreCase("double") ||
                columnType.equalsIgnoreCase("float"))
            return "Double";
        else if(columnType.equalsIgnoreCase("clob"))
            return "java.sql.Clob";
        else if(columnType.equalsIgnoreCase("blob"))
            return "java.sql.Blob";
        else if(columnType.equalsIgnoreCase("date"))
            return "java.sql.Date";
        else if(columnType.equalsIgnoreCase("datetime"))
            return "java.sql.Date";
        else if(columnType.equalsIgnoreCase("time"))
            return "java.sql.Time";
        else if(columnType.equalsIgnoreCase("timestamp"))
            return "java.sql.Timestamp";
        else if(columnType.equalsIgnoreCase("json"))
            return "String";
        return null;
    }

    @Override
    public String javaType2DatabaseType(String javaDataType) {
        return null;
    }
}