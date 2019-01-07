import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8989);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] bytes = new byte[1024];
        bis.read(bytes);
        System.out.println(new String(bytes, "utf-8").trim());
    }
}
