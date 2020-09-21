package chaoking.java.learn.socket;



import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8081);
        System.out.println("发送数据");
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("12312312312312312313123".getBytes());
    }
}
