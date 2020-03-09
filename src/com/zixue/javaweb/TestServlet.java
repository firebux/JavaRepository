package com.zixue.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Servlet实例是单例的,
//无论请求多少次Servlet,最多只有一个Servlet实例,
//如果多个并发,同时访问Servlet的时候,服务器会启动多个线程分别执行Servlet的service方法
//这么做是为了防止浪费资源

/**
 * Servlet
 *
 * ServletConfig：ServletConfig对象是它所对应的Servlet对象的相关配置信息 （局部的，仅仅是当前Servlet程序的配置）
 *                每一个servlet对象都有一个ServletConfig对象和它相对应，不可共享;
 *
 * ServletContext: ServletContext即Servlet上下文对象，该对象表示当前的web应用环境信息(全局的)
 *                它又称为 域对象（域对象就是在不同资源之间共享数据，保存数据，获取数据用的）
 *                通过ServletConfig对象的getServletConfig()方法得到;
 *                也可通过this.getServletContext()获取
 */

@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("执行init");
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("执行service");
        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    int global_i = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行doGet");

        //取应用整体配置文件
        String encoding = this.getServletContext().getInitParameter("encoding");

        //加载特定配置文件
        InputStream resourceAsStream = this.getServletContext().getResourceAsStream("/db.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        String url1 = properties.getProperty("URL");

        System.out.println("Encoding:"+encoding);
        System.out.println(name+":"+password+":"+url1);

        synchronized (this) {
            global_i++;
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(global_i + "");
        }


        response.setHeader("refresh", "3;url='/login.html'");
        response.getWriter().write("3秒后自动刷新跳转到登录页面。。。");
    }

    @Override
    public void destroy() {
        System.out.println("执行destroy销毁");
        super.destroy();
    }
}
