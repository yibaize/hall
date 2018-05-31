package org.zgl.error;

import org.zgl.utils.builder_clazz.ann.Protostuff;

/**
 * @作者： big
 * @创建时间： 2018/5/19
 * @文件描述：
 */
@Protostuff
public class ErrorCodeDto {
    private short errCode;

    public ErrorCodeDto(short errCode) {
        this.errCode = errCode;
    }

    public ErrorCodeDto() {
    }

    public short getErrCode() {
        return errCode;
    }

    public void setErrCode(short errCode) {
        this.errCode = errCode;
    }
}
