import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
    MyDecoder myDecoder;
    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.myDecoder = new MyDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        byte[] bytes = new byte[1024];

        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        NettyMessage message = new NettyMessage();
        message.setType(in.readByte());
        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attch = new HashMap<String, Object>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key;
            for (int i = 0; i < size; i++) {
                keySize = in.readInt();
                keyArray = new byte[keySize];
                in.readBytes(keyArray);
                key = new String(keyArray, "utf-8");
                attch.put(key, myDecoder.decode(in));
            }
            key = null;
            keyArray = null;
            message.setAttachment(attch);
        }
        return message;
    }
}
