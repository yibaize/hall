package org.zgl.jetty.operation;


import org.zgl.error.*;
import org.zgl.jetty.JettyHandlerImpl;
import org.zgl.jetty.session.SessionManager;
import org.zgl.logic.login.po.SQLUserBaseInfo;
import org.zgl.player.UserMap;
import org.zgl.utils.logger.LoggerUtils;

import javax.servlet.http.HttpServletResponse;

public abstract class OperateCommandAbstract implements IOperationCommand{
    private short cmdId;
    private final String account;
    private HttpServletResponse httpServletResponse;
    private final JettyHandlerImpl jettyHandler = new JettyHandlerImpl();

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public short getCmdId() {
        return cmdId;
    }
    public void setCmdId(short cmdId) {
        this.cmdId = cmdId;
    }
    public OperateCommandAbstract(String account) {
        this.account = account;
    }
    public void run() {
        try {
            Object o = execute();
            write(o);
            broadcast();
        }catch (Exception e){
            errRecive(e);
        }
    }
    public String getAccount(){
        return account;
    }
    private void write(Object o){
        jettyHandler.write(httpServletResponse,cmdId,o);
    }
    /**
     * 异常处理
     * @param e
     * @param
     */
    private void errRecive(Exception e){
        UserMap userMap = SessionManager.getSession(account);
        if(userMap == null){
            userMap = new UserMap().systemUser();
        }
        SQLUserBaseInfo base = userMap.getBaseInfo();
        if(e instanceof ErrorAbs) {
            ErrorAbs errorAbs = (ErrorAbs) e;
            if (e instanceof GenaryAppError) {
                GenaryAppError error = (GenaryAppError) e;
                error((short) error.getErrorCode());
            } else if (e instanceof LogAppError) {
                LogAppError error = (LogAppError) e;
                LoggerUtils.getLogicLog().error(error.getMessage());
                error((short) AppErrorCode.DATA_ERR);
            } else if (e instanceof CloseConnectionError) {
                CloseConnectionError error = (CloseConnectionError) e;
                error((short) error.getErrorCode());
            }else if(e instanceof RPCError){
                RPCError error = (RPCError) e;
                error(cmdId);
            }
            LoggerUtils.getLogicLog().info("用户id：<<"+userMap.getId()+">>：用户名：<<"+base.getUserName()+">>操作异常<---||--->；异常码：<<"+errorAbs.getErrorCode()+">>(^_^)异常信息：<<"+errorAbs.getMsg()+">>", e);
        }else {
            error((short) AppErrorCode.SERVER_ERR);
            LoggerUtils.getLogicLog().debug("用户id：<<"+userMap.getId()+">>：用户名：<<"+base.getUserName()+">>操作异常",e);
        }
    }

    /**
     * 异常回发
     * @param errorCode
     * @param
     */
    private void error(short errorCode){
        jettyHandler.write(httpServletResponse,(short)404,new ErrorCodeDto(errorCode));
    }
}