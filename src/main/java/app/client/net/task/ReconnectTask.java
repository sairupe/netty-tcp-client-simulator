package app.client.net.task;

import java.util.concurrent.Future;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:20:37
 */
public class ReconnectTask implements Runnable{

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
//            System.out.println("正在尝试重连，目前失败次数" + reconnectTimes);
//			NettySocketManager.getInstance().reconnect(ip, port);
//		}
//		else{
//			reconnectFuture.cancel(true);
//            System.out.println("超过最大重连尝试次数，目前失败次数" + reconnectTimes);
//		}
	}

	public void setReconnectFuture(Future<?> reconnectFuture) {
		this.reconnectFuture = reconnectFuture;
	}
}
