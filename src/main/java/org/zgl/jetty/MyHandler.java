package org.zgl.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.zgl.logic.hall.siginin.dto.FirstBuyAwardDto;
import org.zgl.utils.ProtostuffUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @作者： big
 * @创建时间： 2018/5/18
 * @文件描述：
 */
public class MyHandler extends AbstractHandler {
    private final JettyHandlerImpl handler = new JettyHandlerImpl();
    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        handler.handle(request,httpServletResponse);
//        httpServletResponse.getWriter().print(buf);
    }
}
