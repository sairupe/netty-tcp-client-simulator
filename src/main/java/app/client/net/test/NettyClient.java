//package app.client.net.test;
//
//import java.io.Closeable;
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
//import app.client.net.socket.*;
//import org.jboss.netty.bootstrap.ClientBootstrap;
//import org.jboss.netty.channel.ChannelFactory;
//import org.jboss.netty.channel.group.ChannelGroup;
//import org.jboss.netty.channel.group.DefaultChannelGroup;
//import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import app.client.net.dispacher.DispacherManager;
//import app.client.net.task.TaskManager;
//
//import com.google.common.annotations.VisibleForTesting;
//
///**
// * @author syriana.zh
// */
//public class NettyClient implements Closeable{
//
//    private static final Logger logger = LoggerFactory
//            .getLogger(NettyClient.class);
//
//    private static final int DEFAULT_MSG_SIZE_LIMIT = 1200;
//
//    private static final ChannelFactory factory = new NioClientSocketChannelFactory(
//            Executors.newCachedThreadPool(), Executors.newCachedThreadPool());;
//    private static final ChannelGroup allChannels = new DefaultChannelGroup();
//    private final ClientBootstrap clientBootstrap;
//    private static int port = 9999;
//    private final String CONNECT_IP = "127.0.0.1";
//
//
//    public NettyClient(int port){
//        this(port, DEFAULT_MSG_SIZE_LIMIT);
//    }
//
//    public NettyClient(int port, int msgSizeLimit){
//        this(port, msgSizeLimit, allChannels);
//    }
//
//    @VisibleForTesting
//    NettyClient(int port, ChannelGroup group){
//        this(port, DEFAULT_MSG_SIZE_LIMIT, group);
//    }
//
//    @VisibleForTesting
//    NettyClient(int port, final int msgSizeLimit, ChannelGroup group){
//        this.port = port;
//        clientBootstrap = new ClientBootstrap(factory);
//        clientBootstrap.getPipeline().addLast("encoder", new WszzEncoder());
//        clientBootstrap.getPipeline().addLast("decoder",
//                new WszzDecoder(DEFAULT_MSG_SIZE_LIMIT));
//        clientBootstrap.getPipeline().addLast("handler", new WszzHandler());
//
//        clientBootstrap.setOption("tcpNoDelay", true);
//        clientBootstrap.setOption("keepAlive", true);
//
//        clientBootstrap.connect(new InetSocketAddress(CONNECT_IP, port));
//    }
//
//    @Override
//    public void close(){
//        allChannels.close().awaitUninterruptibly();
//        clientBootstrap.releaseExternalResources();
//    }
//
//    public static void main(String args[]) throws Exception{
//
//        // 初始化协议原型加载
//        Class.forName("app.client.net.protocol.ProtocolFactory");
//        // 初始化协议加载
//        Class.forName("app.client.net.dispacher.DispacherManager");
//        // 初始化分发器
//        DispacherManager.getInstance().init();
//        // 初始化线程池
//        TaskManager.getInstance().init();
//
//        for (int i = 0; i < 1; i++){
//            new NettyClient(port);
//        }
//    }
//}
