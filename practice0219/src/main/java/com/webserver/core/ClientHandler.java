package com.webserver.core;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try {
            HttpRequest request = new HttpRequest(socket);
            HttpResponse response = new HttpResponse(socket);

            String path = request.getUri();
            File file = new File("./webapps"+path);

            if (file.exists()&&file.isFile()){
                System.out.println("该资源已找到");
                response.setEntity(file);
            }else {
                System.out.println("资源不存在！");
                File notFoundFile = new File("./webapps/root/404.html");
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                response.setEntity(notFoundFile);
            }

            System.out.println("响应发送完毕！");

            response.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}