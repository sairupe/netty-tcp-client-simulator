package app.client.net.task;

import app.client.net.test.Netty4AppClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:20:37
 */
public class ReconnectTask implements Runnable{

	private static final Logger logger = LoggerFactory
			.getLogger(ReconnectTask.class);

    /**
    * 重连IP
    */
	private String ip;
    /**
    * 重连端口
    */
	private int port;
    /**
    * 提交线程池的FUTURE
    */
	private Future<?> reconnectFuture;
	
	public ReconnectTask(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	@Override
	public void run() {
//		byte reconnectTimes = NettySocketManager.getInstance().getReconnectTimes();
//		if(reconnectTimes <= Const.RECONNECT_TIMES){
//            logger.info("正在尝试重连，目前失败次数" + reconnectTimes);
//			NettySocketManager.getInstance().reconnect(ip, port);
//		}
//		else{
//			reconnectFuture.cancel(true);
//            logger.info("超过最大重连尝试次数，目前失败次数" + reconnectTimes);
//		}
	}

	public void setReconnectFuture(Future<?> reconnectFuture) {
		this.reconnectFuture = reconnectFuture;
	}
}
