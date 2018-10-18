package app.client.net.protocol;/*package app.client.net.protocol;


import org.jboss.netty.channel.ChannelHandlerContext;

import app.client.user.session.UserSession;
import app.client.user.session.UserSessionManager;

*//**
* 
* @author syriana.zh
*
* 2016年4月15日 下午3:05:40
*/
/*
public final class ProtocolRecornizer extends SimpleChannelInboundHandler<ByteBuf>{

@Override
protected void channelRead0(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
	int protocolId = buffer.readUnsignedShort();
	ResponseProtocol response = ProtocolFactory.getResponseProtocol(protocolId, buffer);
	if(response == null){
		return ;
	}
	long channelId = Long.parseLong(ctx.channel().id().asShortText(), 16);
	UserSession session = UserSessionManager.getInstance().getUserSessionByChannelId(channelId);
	session.addResponseToQueue(response);
}
}
*/