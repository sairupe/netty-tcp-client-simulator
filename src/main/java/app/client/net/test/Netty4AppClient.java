package app.client.net.test;

import app.client.data.StatisticHolder;
import app.client.net.dispacher.DispacherManager;
import app.client.net.socket.EventLoopHolder;
import app.client.net.socket.GowildAppHandler;
import app.client.net.socket.GowildDecoder;
import app.client.net.socket.GowildEncoder;
import app.client.net.socket.GowildHandler;
import app.client.net.task.TaskManager;
import com.google.common.annotations.VisibleForTesting;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.net.InetSocketAddress;

/**
 * @author syriana.zh
 */
public class Netty4AppClient implements Closeable{

    private static final Logger logger = LoggerFactory
            .getLogger(Netty4AppClient.class);

    private String account;
    private String token;

    public static int PORT = 6030;
    public static final String HOST = "172.27.1.41";
    public static String TOKEN_URL = "http://172.27.1.41/oauth/mobile/token";

//    public static int PORT = 6030;
//    public static final String HOST = "172.27.1.49";
//    public static String TOKEN_URL = "http://172.27.1.49:6130/oauth/mobile/token";

//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.73";
//    public static final String TOKEN_URL = "http://172.27.1.73:6130/oauth/mobile/token";


    public void start() throws Exception{
        try {
            Bootstrap b = new Bootstrap();
            b.group(EventLoopHolder.getGroup());
            b.channel(NioSocketChannel.class);
            b.remoteAddress(new InetSocketAddress(HOST, PORT));
            b.handler(new ChannelInitializer<SocketChannel>() {

                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new GowildDecoder());
                    ch.pipeline().addLast(new GowildEncoder());
                    GowildAppHandler gowildAppHandler = new GowildAppHandler();
                    gowildAppHandler.setAccount(account);
                    gowildAppHandler.setToken(token);
                    ch.pipeline().addLast(gowildAppHandler);

                }
            });
            ChannelFuture f = b.connect().sync();
            StatisticHolder.incAppClient();
            logger.info("===================>>>>啓動APP BOOTSTRAP，目前CLIENT數量為：" + StatisticHolder.getAppCount());
//            f.channel().closeFuture().sync();
        } finally {
//            EventLoopHolder.getGroup().shutdownGracefully().sync();
        }
        StatisticHolder.decApp();
    }

    @Override
    public void close(){
    }

    public void init() throws Exception{
    }

    public static void main(String[] args) throws Exception {
        Netty4AppClient appClient = new Netty4AppClient();
        appClient.init();
        appClient.start();
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
