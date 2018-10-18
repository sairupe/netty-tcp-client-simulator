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
import app.client.net.task.xb.XbChainNodeTask;
import app.client.service.user.UserServiceImpl;
import app.client.user.session.ConnectStatus;
import app.client.user.session.UserSession;
import app.client.user.session.UserSessionManager;
import com.gowild.core.util.LogUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 游戏Socket处理
 *
 * @author Dream.xie
 */
public final class GowildHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private UserServiceImpl userServiceImpl;
    // =================   GOWILD基础组件 ===================================================
    public static final AttributeKey<UserSession> USER_SESSION = AttributeKey.valueOf("USER_SESSION");
    /**
     * 解密密钥netty上下文属性
     */
    public static final AttributeKey<int[]> DECRYPTION_KEY = AttributeKey.valueOf("DECRYPTION_KEY");
    /**
     * 加密密钥netty上下文属性
     */
    public static final AttributeKey<int[]> ENCRYPTION_KEY = AttributeKey.valueOf("ENCRYPTION_KEY");


    public GowildHandler() {
        ServiceManager.injectionReceiver(this);
    }

    public ExecutorService chainNodeExecuteSinglePool = Executors.newSingleThreadExecutor();


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LogUtil.error("============建立连接=============");
        long channelId = System.currentTimeMillis();
        UserSession userSession = new UserSession(ctx);
        userSession.setConnectStatus(ConnectStatus.CONNECTING);
        userSession.setUid(channelId);
        NioSocketChannel nioSocketChannel = (NioSocketChannel) ctx.channel();
        nioSocketChannel.attr(USER_SESSION).set(userSession);
        UserSessionManager.getInstance().addUserSession(channelId, userSession);
        //userServiceImpl.userLogin(userSession);
        XbChainNodeTask xbChainNodeTask = new XbChainNodeTask();
        xbChainNodeTask.setUserSession(userSession);
        chainNodeExecuteSinglePool.execute(xbChainNodeTask);
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        NioSocketChannel nioSocketChannel = (NioSocketChannel) ctx.channel();
        nioSocketChannel.attr(DECRYPTION_KEY).set(SocketUtil.copyDefaultKey());
        nioSocketChannel.attr(ENCRYPTION_KEY).set(SocketUtil.copyDefaultKey());
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
            UserSession userSession = nioSocketChannel.attr(USER_SESSION).get();
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
//        LogUtil.error("调用NettySocketHandler的exceptionCaught方法。\n{}", cause);
//        for(StackTraceElement e : cause.getStackTrace()){
//            LogUtil.error(e.toString());
//        }
        cause.printStackTrace();
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
