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

	// 账号
	private String account;

	// 机器MAC
	private String mac;

	// APP TOKEN
	private String appToken;

	// ROBOT TOKEN
	private String robotToken;

	// 登陆时间
	private long loginTime;

	// 收到登陆相应时间
	private long receivLoginResultTime;

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

    /**
    * 取消心跳包的定时任务
    */
	public void cancelTickTask(){
		if(tickTaskFuture != null){
			tickTaskFuture.cancel(true);
		}
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getAccount() {
		return account;
	}

	public String getMac() {
		return mac;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getRobotToken() {
		return robotToken;
	}

	public void setRobotToken(String robotToken) {
		this.robotToken = robotToken;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public long getReceivLoginResultTime() {
		return receivLoginResultTime;
	}

	public void setReceivLoginResultTime(long receivLoginResultTime) {
		this.receivLoginResultTime = receivLoginResultTime;
	}
}
