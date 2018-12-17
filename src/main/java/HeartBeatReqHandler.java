import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {
    private volatile ScheduledFuture<?> heartBeat;
    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getType() == (byte)1) {
            heartBeat = ctx.executor().scheduleAtFixedRate(new Runnable() {
                public void run() {
                    ctx.writeAndFlush(buildHeartBeat());
                }
            }, 0, 5000, TimeUnit.MICROSECONDS);
            System.out.println(message);
        } else if (message.getType() == 3) {
            System.out.println("response============" + message);
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        message.setType((byte) 2);
        return message;
    }
}
