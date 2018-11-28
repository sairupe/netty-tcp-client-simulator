package app.client.user.session;


import app.client.common.TimeRecord;
import app.client.common.TimeRecordKey;
import app.client.net.protocol.RequestProtocol;
import app.client.net.task.TaskManager;
import app.client.testchain.IChainNode;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author syriana.zh
 *         <p>
 *         2016年4月18日 上午9:57:04
 */
public class UserSession {

    private byte clientType;

    private ChannelHandlerContext ctx;

    private long uid;

    private ConnectStatus connectStatus;

    // 登陆的future
    private Future<?> loginFuture;
    // 账号
    private String account;
    // 机器MAC
    private String mac;
    // APP TOKEN
    private String appToken;
    // ROBOT TOKEN
    private String robotToken;
    // 协议链，收到登录协议返回后再发送
    private IChainNode chainNode;
    // 时间点记录MAP
    private Map<String, TimeRecord> timeReordMap = new HashMap<>();

    public UserSession(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void sendMsg(RequestProtocol request) {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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

    public Future<?> getLoginFuture() {
        return loginFuture;
    }

    public void setLoginFuture(Future<?> loginFuture) {
        this.loginFuture = loginFuture;
    }

    public byte getClientType() {
        return clientType;
    }

    public void setClientType(byte clientType) {
        this.clientType = clientType;
    }

    public int getRobotId() {
        return (int) (uid >> 2);
    }

    public int getAccountId() {
        return (int) (uid >> 2);
    }

    public IChainNode getChainNode() {
        return chainNode;
    }

    public void setChainNode(IChainNode chainNode) {
        this.chainNode = chainNode;
    }


    public void markTimeStart(TimeRecordKey key) {
        TimeRecord timeRecord = timeReordMap.get(key.name());
        if (timeRecord == null) {
            timeRecord = new TimeRecord();
        }
        timeRecord.setStartTime(System.currentTimeMillis());
        this.timeReordMap.put(key.name(), timeRecord);
    }

    public void markTimeEnd(TimeRecordKey key) {
        TimeRecord timeRecord = timeReordMap.get(key.name());
        if (timeRecord == null) {
            timeRecord = new TimeRecord();
        }
        timeRecord.setEndTime(System.currentTimeMillis());
        this.timeReordMap.put(key.name(), timeRecord);
    }

    public long getTimeStart(TimeRecordKey key) {
        TimeRecord timeRecord = timeReordMap.get(key.name());
        if (timeRecord == null) {
            return -1;
        }
        return timeRecord.getStartTime();
    }

    public long getTimeEnd(TimeRecordKey key) {
        TimeRecord timeRecord = timeReordMap.get(key.name());
        if (timeRecord == null) {
            return -1;
        }
        return timeRecord.getEndTime();
    }
}
