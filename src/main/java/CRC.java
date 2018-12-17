import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;

public class CRC {
    int register = 0xffff;
    void en() throws UnsupportedEncodingException {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes("asdadadad".getBytes());
        buf.writeBytes("123".getBytes("utf-8"));
        byte[] bytes = new byte[9];
        buf.readBytes(bytes);

        System.out.println(bytes);
        int id = buf.readerIndex();
        byte[] body = new byte[3];
        buf.readBytes(body);
        buf.resetReaderIndex();
        byte[] bytes2 = new byte[10];
        buf.readBytes(bytes2, 1,1);
        buf.readerIndex(10);
        System.out.println(new String(bytes2, "utf-8"));
        byte[] bytes1 = buf.array();

        System.out.println(new String(bytes1, "utf-8"));
        System.out.println(new String(body,"utf-8"));
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        new CRC().en();
    }
}
