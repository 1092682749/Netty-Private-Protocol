import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import trim.utils.CRC16;
//import sun.misc.CRC16;

import java.io.UnsupportedEncodingException;
@SuppressWarnings("Duplicates")
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
//        CRC16 crc16 = new CRC16();
        int a = CRC16.crc16(body);
        System.out.println(Integer.toHexString(a));
        System.out.println(Integer.toBinaryString(a));
        System.out.println(new String(bytes1, "utf-8"));
        System.out.println(new String(body,"utf-8"));
        System.out.println(Integer.toBinaryString(getCRC(body)));
    }
    /**
     * 计算CRC16校验码
     *
     * @param bytes
     * @return
     */
    public static int getCRC(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return CRC;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        new CRC().en();
    }
}
