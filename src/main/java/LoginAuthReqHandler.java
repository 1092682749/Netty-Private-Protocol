

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        byte type = 1;
        if (message.getType() == type) {
            System.out.println("login success!");
            ctx.fireChannelRead(msg);
        } else {
            System.out.println("login failed");
            ctx.channel().close();
        }
    }
    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        byte type = 1;
        message.setType(type);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}

