/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package app.client.net.socket;

import app.client.net.dispacher.DispacherManager;
import app.client.net.dispacher.ServiceManager;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.ResponseProtocol;
import app.client.net.task.app.AppChainNodeTask;
import app.client.user.session.ConnectStatus;
import app.client.user.session.UserSession;
import app.client.user.session.UserSessionManager;
import com.gowild.core.util.LogUtil;
import com.gowild.tcp.core.manager.socket.Message;
import com.gowild.tcp.core.manager.socket.SocketUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 游戏Socket处理
 *
 * @author Dream.xie
 */
public final class GowildAppHandler extends ChannelInboundHandlerAdapter {

    private String account;

    public GowildAppHandler() {
        ServiceManager.injectionReceiver(this);
    }

    public ExecutorService chainNodeExecuteSinglePool = Executors.newSingleThreadExecutor();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        long channelId = System.currentTimeMillis();
        UserSession userSession = new UserSession(ctx);
        userSession.setConnectStatus(ConnectStatus.CONNECTING);
        userSession.setUid(channelId);
        NioSocketChannel nioSocketChannel = (NioSocketChannel) ctx.channel();
        nioSocketChannel.attr(GowildHandler.USER_SESSION).set(userSession);
        UserSessionManager.getInstance().addUserSession(channelId, userSession);
//        userServiceImpl.appLogin(userSession);

        AppChainNodeTask appChainNodeTask = new AppChainNodeTask();
        appChainNodeTask.setUserSession(userSession);
        chainNodeExecuteSinglePool.execute(appChainNodeTask);
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        NioSocketChannel nioSocketChannel = (NioSocketChannel) ctx.channel();
        nioSocketChannel.attr(GowildHandler.DECRYPTION_KEY).set(SocketUtil.copyDefaultKey());
        nioSocketChannel.attr(GowildHandler.ENCRYPTION_KEY).set(SocketUtil.copyDefaultKey());
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
            UserSession userSession = nioSocketChannel.attr(GowildHandler.USER_SESSION).get();
            ResponseProtocol response = ProtocolFactory
                    .getResponseProtocol(moduleId, sequenceId,
                            buffer, userSession.getUid());
            DispacherManager.getInstance().dispatch(moduleId, sequenceId,
                    response);
        }
    }

    /**
     *
     */
    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        LogUtil.error("{}调用NettySocketHandler的exceptionCaught方法。");
        for(StackTraceElement e : cause.getStackTrace()){
            LogUtil.error(e.toString());
        }
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
