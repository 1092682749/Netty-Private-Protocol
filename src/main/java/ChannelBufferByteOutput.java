

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import org.jboss.marshalling.ByteOutput;

class ChannelBufferByteOutput implements ByteOutput {

    private final ByteBuf buffer;

    /**
     * Create a new instance which use the given {@link ByteBuf}
     */
    public ChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }



    /**
     * Return the {@link ByteBuf} which contains the written content
     */
    ByteBuf getBuffer() {
        return buffer;
    }

    public void write(int i) throws IOException {
        buffer.writeByte(i);
    }

    public void write(byte[] bytes) throws IOException {
        buffer.writeBytes(bytes);
    }

    public void write(byte[] bytes, int i, int i1) throws IOException {
        buffer.writeBytes(bytes, i, i1);
    }

    public void close() throws IOException {

    }

    public void flush() throws IOException {

    }
}

