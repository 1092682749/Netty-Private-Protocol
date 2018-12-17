//import javax.xml.bind.Unmarshaller;
import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

public class MyDecoder {
    private final Unmarshaller unmarshaller;
    protected MyDecoder() throws IOException {
        this.unmarshaller = MarshallingCodeCFactory.buildUnMarshalling();
    }
    protected Object decode(ByteBuf in) throws IOException, ClassNotFoundException {
        int size = in.readInt();
        ByteBuf buf = in.slice(in.readerIndex(), size);
        ByteInput input = new ChannelBufferByteInput(buf);
        try{
            unmarshaller.start(input);
            Object o = unmarshaller.readObject();
            unmarshaller.finish();
            in.readerIndex(in.readerIndex() + size);
            return o;
        }finally {

        }
    }
}
