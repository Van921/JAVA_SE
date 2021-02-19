package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求对象
 * 该类的每一个实例用于表示客户端发送过来的一个HTTP请求内容
 * 每个请求由三部分构成：
 * 请求行，消息头，消息正文
 */
public class HttpRequest {
    private String method;
    private String uri;
    private String protocol;
    private Map<String,String> headers = new HashMap<>();


    private Socket socket;
    public HttpRequest(Socket socket){
        this.socket = socket;
        parseRequestLine();
        parseHeaders();
        parseContenet();

    }

    private void parseRequestLine() {
        System.out.println("HttpRequest:开始解析请求行...");
        try{
            String line = readLine();
            System.out.println("请求行："+line);
            String[] data = line.split("\\s");
            method = data[0];

            uri = data[1];
            protocol = data[2];
            System.out.println("method:"+method);
            System.out.println("uri:"+uri);
            System.out.println("protocol:"+protocol);
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("HttpRequest:请求行解析完毕！！");
    }

    private void parseHeaders() {
        System.out.println("HttpRequest:开始解析消息头...");
        try{
            while(true){
                String line = readLine();
                if ((line.isEmpty())){
                    break;
                }
                System.out.println("消息头："+line);
                String[] data = line.split("\\s");
                headers.put((data[0]),data[1]);
            }
            System.out.println("headers:"+headers);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("HttpRequest:消息头解析完毕！！");
    }

    private void parseContenet() {
        System.out.println("HttpRequest:开始解析正文...");
        System.out.println("HttpRequest:正文解析完毕！！");
    }

    private String readLine() throws IOException {
    InputStream in = socket.getInputStream();
    int d;
    char cur = ' ';
    char pre = ' ';
    StringBuilder builder = new StringBuilder();
    while ((d=in.read())!=-1){
        cur = (char)d;
        if(pre==13 && cur==10){
            break;
        }
        builder.append(cur);
        pre = cur;
    }
    return builder.toString().trim();
    }
}
