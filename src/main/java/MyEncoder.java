

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;

import java.io.IOException;

public class MyEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;
    public MyEncoder() throws IOException {
        this.marshaller = MarshallingCodeCFactory.buildMarshalling();
    }
    protected void encode(Object msg, ByteBuf out) throws IOException {
        try{
            int lengthPos = out.readerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);
            ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
            out.setInt(lengthPos, out.readerIndex() - lengthPos - 4);
        }finally {
            marshaller.close();
        }
    }
}
