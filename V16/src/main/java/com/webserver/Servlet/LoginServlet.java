package com.webserver.Servlet;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

import javax.crypto.spec.PSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class LoginServlet {
    public void service(HttpRequest request, HttpResponse response){
        System.out.println("LoginServlet:登录信息开始处理....");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username==null||password==null){
            File file = new File("./webapps/myweb/login_fail.html");
            response.setEntity(file);
            return;
        }

            System.out.println(username+","+password);


        try {
            RandomAccessFile raf = new RandomAccessFile("user.dat","rw");

            for (int i = 0; i < raf.length(); i++) {
                raf.seek(i*100);
                byte[] data = new byte[32];
                raf.read(data);
                String name = new String(data,"UTF-8").trim();
                if (username.equals(name)){
                    File file = new File("./webapps/myweb/have_user.html");
                    response.setEntity(file);

                }
            }

            raf.seek(raf.length());

            byte[] data = username.getBytes("UTF-8");
            data = Arrays.copyOf(data,32);
            raf.write(data);

            data = password.getBytes("UTF-8");
            data = Arrays.copyOf(data,32);
            raf.write(data);

            System.out.println("注册完毕！！！");

            File file = new File("./webapps/myweb/reg.success.html");
            response.setEntity(file);


        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("LoginServlet:登录信息已完毕！");


    }

}
