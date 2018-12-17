import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NettyMessageEncord extends MessageToMessageEncoder<NettyMessage> {
    MyEncoder marshallingEncoder;
    NettyMessageEncord() throws IOException {
        this.marshallingEncoder = new MyEncoder();
    }
    protected void encode(ChannelHandlerContext ctx, NettyMessage nettyMessage, List<Object> list) throws Exception {
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(nettyMessage.getType());
        String key = null; Object value = null;
        byte[] keyArray = null;
        for (Map.Entry<String, Object> entry : nettyMessage.getAttachment().entrySet()) {
            key = entry.getKey();
            keyArray = key.getBytes("utf-8");
            buf.writeInt(keyArray.length);
            buf.writeBytes(keyArray);
            value = entry.getValue();
            marshallingEncoder.encode(value, buf);
        }
    }
}
