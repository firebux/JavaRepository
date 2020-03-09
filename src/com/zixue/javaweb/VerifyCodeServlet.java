package com.zixue.javaweb;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "VerifyCodeServlet", urlPatterns = "/code")
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int with = 100;
        int high = 30;
        BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_3BYTE_BGR);

        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0,0,with,high);

        String data = "ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        graphics.setColor(Color.black);
        for (int i = 0; i < 4; i++) {
            int randpos = random.nextInt();

            graphics.drawString(data.substring(randpos, randpos+1), with/5 * (i+1), 15);
        }


        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
