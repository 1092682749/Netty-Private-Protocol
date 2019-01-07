package DiviTest.netty.encode;

import DiviTest.netty.MessageFormat;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.jboss.marshalling.*;


import java.io.IOException;

public class PrivateEncode extends MessageToByteEncoder<MessageFormat> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageFormat messageFormat, final ByteBuf byteBuf) throws Exception {
        Marshaller marshaller = buildMarshalling();
        int pos = byteBuf.writerIndex();
        byteBuf.writeBytes(new byte[4]);
        ByteOutput output = new ByteOutput() {

            public void write(int i) throws IOException {
//                byteBuf.writeInt(i);
                byteBuf.writeInt(i);
            }


            public void write(byte[] bytes) throws IOException {
//                    byteBuf.writeBytes(bytes);
            }


            public void write(byte[] bytes, int i, int i1) throws IOException {
//                    byteBuf.writeBytes(bytes, i, i1);
            }


            public void close() throws IOException {

            }


            public void flush() throws IOException {

            }
        };
        marshaller.start(output);
        marshaller.writeObject(messageFormat.getTime());
        marshaller.finish();
        byteBuf.setInt(pos, byteBuf.writerIndex() - pos - 4);
        byteBuf.writeBytes(messageFormat.getContent().getBytes());
    }
    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Marshaller marshaller = marshallerFactory.createMarshaller(configuration);
        return marshaller;
    }
}
