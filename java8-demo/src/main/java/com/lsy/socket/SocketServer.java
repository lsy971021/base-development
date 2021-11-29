package com.lsy.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9900);
        Socket socket = serverSocket.accept();
        handler(socket);
    }

    private static void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);
        if(read != -1){
            System.out.println("请求的内容是：" + new String(bytes, 0, read));
        }
    }
}
