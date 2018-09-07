package app.client.net.test;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelHandlerContextStatusStask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ChannelHandlerContextStatusStask.class);

	ChannelHandlerContext ctx;
	
	public ChannelHandlerContextStatusStask(ChannelHandlerContext ctx){
		this.ctx = ctx;
	}
	
	public void run() {
        logger.info("=========现在的连接状态是 isConnected:"
                + ctx.getChannel().isConnected() + " | isOpen : "
                + ctx.getChannel().isOpen());
	}
}
