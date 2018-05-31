package org.zgl.orm.core;

public interface TypeConvertor {
    /**
     * 数据库转java类型
     * @param columnType
     * @return
     */
    String databaseType2JavaType(String columnType);

    /**
     * java转数据库类型
     * @param javaDataType
     * @return
     */
    String javaType2DatabaseType(String javaDataType);
}
