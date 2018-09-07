package app.client.net.task.xb;

import app.client.net.protocol.RequestProtocol;
import app.client.net.task.RequestTaskImpl;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:20:48
 */
public class XbHeartBeatTask extends RequestTaskImpl {

    private static final Logger logger = LoggerFactory
            .getLogger(XbHeartBeatTask.class);

	public XbHeartBeatTask(ChannelHandlerContext ctx, RequestProtocol request) {
		super(ctx, request);
	}
	
	@Override
	public void run() {
		try {
            // 重新分配新协议
			super.run();
		} catch (Exception e) {
			// LOG
			e.printStackTrace();
		}
	}

	@Override
	public void write() {
		try {
			request.writeBinaryData();
			if (ctx.channel().isActive()){
				logger.info(
						"=================>>>> 发送协议,  moduleId:【{}】, sequenceId:【{}】",
						request.getModuleId(), request.getSequenceId());
				ctx.channel().writeAndFlush(request);
				request.getBuffer().readerIndex(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
