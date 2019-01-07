package DiviTest.netty;


import DiviTest.netty.dncode.PrivateDncode;
import DiviTest.netty.encode.PrivateEncode;
import DiviTest.netty.handler.HttpHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;


public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
//        ch.pipeline().addLast("http-codec", new HttpServerCodec());
//        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
//        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        ch.pipeline().addLast(new PrivateDncode(1024 * 1024, 4, 4));
        ch.pipeline().addLast(new PrivateEncode());
        ch.pipeline().addLast(new HttpHandler());
    }
}
