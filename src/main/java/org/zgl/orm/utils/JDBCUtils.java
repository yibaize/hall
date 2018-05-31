package org.zgl.orm.utils;

import org.zgl.utils.logger.LoggerUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtils {
    public static void handleParams(PreparedStatement ps, Object[] params){
        if(params != null){
            for(int i = 0;i<params.length;i++)
                try {
                    ps.setObject(1 + i, params[i]);
                } catch (SQLException e) {
                    LoggerUtils.getPlatformLog().error("设置对象时异常", e);
                }
        }
    }
}