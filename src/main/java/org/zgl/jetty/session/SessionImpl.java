package org.zgl.jetty.session;

import org.zgl.jetty.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public class SessionImpl implements ISession {
//    private static AttributeKey<Object> ATTACHMENT_KEY = AttributeKey.valueOf("ATTACHMENT_KEY");
//
    private final HttpServletResponse httpServletResponse;
    public SessionImpl(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public Object getAttachment() {
        return null;
    }

    @Override
    public void setAttachment(Object attachment) {
//        channel.attr(ATTACHMENT_KEY).set(attachment);
    }

    @Override
    public void removeAttachment() {
//        channel.attr(ATTACHMENT_KEY).remove();
    }

    @Override
    public void write(Response response) {
//        channel.writeAndFlush(response);
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void close() {
//        channel.close();
    }
}
