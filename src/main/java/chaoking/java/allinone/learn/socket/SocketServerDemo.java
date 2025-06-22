package chaoking.java.allinone.learn.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost",8088));
        Socket sokcet = server.accept();

        InputStream inputStream = sokcet.getInputStream();
        byte[] bytes = new byte[5];
        int n = inputStream.read(bytes);
        System.out.println(n);

        System.in.read();
    }
}

