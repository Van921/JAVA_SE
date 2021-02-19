package com.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpResponse {
    private int statusCode = 200;
    private String StatusReason = "OK";

    private File entity;

    private Socket socket;

    public HttpResponse(Socket socket){
        this.socket = socket;
    }

    public void flush(){
        sendStatusLine();
        sendHeaders();
        sendContent();
    }

    private void sendStatusLine() {
        System.out.println("HttpResponse:开始发送状态行...");
        try {
            OutputStream out = socket.getOutputStream();
            String line = "HTTP/1.1" + " " + statusCode + " " + StatusReason;
            System.out.println("状态行："+line);
            println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("HttpResponse:状态行发送完毕!");
    }

    private void sendHeaders() {
        System.out.println("Http:Response:开始发送响应头");

        try {
            String line = "Content-Type: text/html";
            println(line);

            println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Httpresponse：响应头发送完毕!");
    }

    private void sendContent() {
        System.out.println("HttpResponse:开始发送响应正文...");
        try {
            OutputStream out = socket.getOutputStream();
            FileInputStream fis = new FileInputStream(entity);

            int len;
            byte[] buf = new byte[1024 * 10];
            while ((len = fis.read(buf))!=-1){
                out.write(buf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HttpResponse:响应正文发送完毕...");
    }



    public void println(String line) throws IOException {
        OutputStream out = socket.getOutputStream();
        byte[] data = line.getBytes("ISO8859-1");
        out.write(data);
        out.write(13);
        out.write(10);
    }

    public File getEntity() {
        return entity;
    }

    public void setEntity(File entity) {
        this.entity = entity;
    }



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return StatusReason;
    }

    public void setStatusReason(String statusReason) {
        StatusReason = statusReason;
    }
}
