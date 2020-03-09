package com.zixue.javaweb;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        ServletConfig servletConfig = this.getServletConfig();
        String encoding = servletConfig.getInitParameter("encoding");
        System.out.println("encoding="+encoding);


        String encoding1 = this.getServletContext().getInitParameter("encoding");
        System.out.println("encoding:"+encoding1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.printf("接收到 Get 请求");
        System.out.println("请求方式："+req.getMethod());
        System.out.println("URI："+req.getRequestURI());
        System.out.println("URL："+req.getRequestURL());
        System.out.println("发出请求客户端IP地址："+req.getRemoteAddr());
        System.out.println("服务点接收请求的IP地址："+req.getLocalAddr());
        System.out.println("访问客户端的端口号："+req.getRemotePort());
        System.out.println("WEB应用路径："+req.getContextPath());
        System.out.println("http协议和版本："+req.getProtocol());
        System.out.println("=====================================");
        System.out.println("=====================================");

        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String ele = headerNames.nextElement();
            System.out.println(ele+":"+req.getHeader(ele));
        }

        String name = req.getParameter("name");
        String pass = req.getParameter("password");

        Enumeration<String> paramNames =  req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String ele = paramNames.nextElement();
            System.out.println(ele+":"+req.getParameter(ele));
        }
        System.out.println(name+","+pass);

        String res = "请求响应OK";
//        ServletOutputStream servletOutputStream =  resp.getOutputStream();
//        servletOutputStream.write(res.getBytes());

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html; charset=utf-8");
        printWriter.write(res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post 请求");
    }
}
