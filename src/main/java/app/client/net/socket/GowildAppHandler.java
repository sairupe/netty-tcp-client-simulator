/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package app.client.net.socket;

import app.client.data.StatisticHolder;
import app.client.net.dispacher.DispacherManager;
import app.client.net.dispacher.ServiceManager;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.ResponseProtocol;
import app.client.net.task.TaskManager;
import app.client.net.task.app.AppChainNodeTask;
import app.client.testchain.sdk.SdkTestConst;
import app.client.user.session.ConnectStatus;
import app.client.user.session.UserSession;
import app.client.user.session.UserSessionManager;
import app.client.utils.ClientUtil;
import com.gowild.core.util.LogUtil;
import com.gowild.sdk.protocol.SdkMsgType;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 游戏Socket处理
 *
 * @author Dream.xie
 */
public final class GowildAppHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    private String account;

    private String token;

    private int accountId;

    private Future<?> loginFuture;

    public GowildAppHandler() {
        ServiceManager.injectionReceiver(this);
    }

    public ExecutorService chainNodeExecuteSinglePool = Executors.newSingleThreadExecutor();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StatisticHolder.incAppHandShakeCount();
        long uid = ClientUtil.buildAppClientSessionKey(accountId);
        UserSession userSession = new UserSession(ctx);
        userSession.setConnectStatus(ConnectStatus.CONNECTING);
        userSession.setUid(uid);
        userSession.setAccount(account);
        userSession.setAppToken(token);
        userSession.setLoginFuture(loginFuture);
        userSession.setClientType(SdkMsgType.APP_CLIENT_TYPE);
        NioSocketChannel nioSocketChannel = (NioSocketChannel) ctx.channel();
        nioSocketChannel.attr(SdkTestConst.USER_SESSION).set(userSession);
        UserSessionManager.getInstance().addUserSession(uid, userSession);

        AppChainNodeTask appChainNodeTask = new AppChainNodeTask();
        appChainNodeTask.setUserSession(userSession);
        chainNodeExecuteSinglePool.execute(appChainNodeTask);
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        NioSocketChannel nioSocketChannel = (NioSocketChannel) ctx.channel();
        nioSocketChannel.attr(SdkTestConst.DECRYPTION_KEY).set(SocketUtil.copyDefaultKey());
        nioSocketChannel.attr(SdkTestConst.ENCRYPTION_KEY).set(SocketUtil.copyDefaultKey());
        super.channelRegistered(ctx);
    }

    /**
     *
     */
    @Override
    public void channelUnregistered(final ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    /**
     *
     */
    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object message) throws Exception {
        if(message instanceof Message){
            Message msg = (Message) message;
            int moduleId = msg.getType();
            int sequenceId = msg.getCode();
            ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(msg.getBody().length);
            buffer.writeBytes(msg.getBody());
            NioSocketChannel nioSocketChannel = (NioSocketChannel) ctx.channel();
            UserSession userSession = nioSocketChannel.attr(SdkTestConst.USER_SESSION).get();
            ResponseProtocol response = ProtocolFactory
                    .getResponseProtocol(moduleId, sequenceId,
                            buffer, userSession.getUid());
            if(response != null){
                TaskManager.getInstance().addResponse2Queue(response);
            }
        }
    }

    /**
     *
     */
    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        logger.error("APP：【{}】调用NettySocketHandler的exceptionCaught方法。", account, cause);
//        for(StackTraceElement e : cause.getStackTrace()){
//            logger.error(e.toString());
//        }
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setLoginFuture(Future<?> loginFuture) {
        this.loginFuture = loginFuture;
    }
}
