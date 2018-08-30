package app.client.net.task;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.client.net.protocol.RequestProtocol;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月18日 下午1:53:07
 */
public class RequestTaskImpl implements IRequestTask{

    private static final Logger logger = LoggerFactory
            .getLogger(RequestTaskImpl.class);

    public ChannelHandlerContext ctx;
	
	protected RequestProtocol request;

	public RequestTaskImpl(ChannelHandlerContext ctx, RequestProtocol request){
		this.ctx = ctx;
		this.request = request;
	}
	
	@Override
	public void run() {
		write();
	}

	@Override
	public void write() {
		try {
			if(request != null){
				request.writeBinaryData();
				if (ctx.channel().isActive()){
					System.out.println("发送=====>>>>moduleId:【" + request.getModuleId() + "】, sequenceId:【" + request.getSequenceId() + "】..");
					ctx.channel().writeAndFlush(request);
				}
				ctx = null;
				request = null;
			}
		} catch (Exception e) {
			//TODO LOG
			e.printStackTrace();
		}
	}
}
