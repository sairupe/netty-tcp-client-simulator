package app.client.user.session;


import java.util.concurrent.Future;


import app.client.net.protocol.RequestProtocol;
import app.client.net.task.TaskManager;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月18日 上午9:57:04
 */
public class UserSession {
	
	private ChannelHandlerContext ctx;
	
	private long uid;
	
	private ConnectStatus connectStatus;
	
	private Future<?> tickTaskFuture;
	
	public UserSession(ChannelHandlerContext ctx){
		this.ctx = ctx;
	}
	
	public long getUid() {
		return uid;
	}

	
//	public void addResponseToQueue(ResponseProtocol response){
//		TaskManager.getInstance().addResponse2Queue(response);
//	}
	
    public void sendMsg(RequestProtocol request){
		TaskManager.getInstance().addRequest2Queue(request);
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public ConnectStatus getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(ConnectStatus connectStatus) {
		this.connectStatus = connectStatus;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Future<?> getTickTaskFuture() {
		return tickTaskFuture;
	}

	public void setTickTaskFuture(Future<?> tickTaskFuture) {
		this.tickTaskFuture = tickTaskFuture;
	}
	
    /**
    * 取消心跳包的定时任务
    */
	public void cancelTickTask(){
		if(tickTaskFuture != null){
			tickTaskFuture.cancel(true);
		}
	}
}
