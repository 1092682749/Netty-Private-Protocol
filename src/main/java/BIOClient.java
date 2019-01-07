import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println(socket.isConnected());
        SocketChannel channel = socket.getChannel();
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        String message = "hello";
        Integer a = 1;
        bos.write(a.byteValue());
        bos.flush();
    }
}
