package com.zixue.javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = "xiaoming";
        String userPwd = "123321";

        String name = request.getParameter("name");
        String pwd = request.getParameter("password");

        if (!name.equals(userName)) {
            //不存在
            request.setAttribute("ErrorMsg", "用户不存在");
            //请求转发
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }

        if (!pwd.equals(userPwd)) {
            //密码错误
            request.setAttribute("ErrorMsg", "密码错误");
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }

        //重定向到特定地址
//        response.setStatus(302);
//        response.setHeader("Location", "https://www.baidu.com");
        response.sendRedirect("https://www.baidu.com");
    }
}
