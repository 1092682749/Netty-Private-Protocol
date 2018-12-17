package trim.utils;
@SuppressWarnings("Duplicates")
public class CRC16 {
    /**
     * 计算CRC16校验码
     *
     * @param bytes
     * @return
     */
    public static int crc16(byte[] bytes) {
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
    public static int crc32(byte[]... bytesArr) {
        int b = 0;
        int crc = 0xffff;

        for (byte[] d : bytesArr) {
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < 8; j++) {
                    b = ((d[i] << j) & 0x80) ^ ((crc & 0x8000) >> 8);
                    crc <<= 1;
                    if (b != 0)
                        crc ^= 0x1021;
                }
            }
        }
        crc = ~crc;
        return crc;
    }
}
