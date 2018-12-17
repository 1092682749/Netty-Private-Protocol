import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartBeatRespandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getType() == (byte)2) {
            ctx.writeAndFlush(message);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
    public NettyMessage buildRespMessage() {
        NettyMessage message = new NettyMessage();
        message.setType((byte)3);
        return message;
    }

}
