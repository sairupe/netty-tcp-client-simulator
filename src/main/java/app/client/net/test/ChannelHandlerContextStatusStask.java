package app.client.net.test;

import org.jboss.netty.channel.ChannelHandlerContext;

public class ChannelHandlerContextStatusStask implements Runnable {
	
	ChannelHandlerContext ctx;
	
	public ChannelHandlerContextStatusStask(ChannelHandlerContext ctx){
		this.ctx = ctx;
	}
	
	public void run() {
        System.out.println("=========现在的连接状态是 isConnected:"
                + ctx.getChannel().isConnected() + " | isOpen : "
                + ctx.getChannel().isOpen());
	}
}
