package org.zgl.error;

public class RPCError extends ErrorAbs {
    public RPCError(int errorCode) {
        super(errorCode);
        throw this;
    }
}
