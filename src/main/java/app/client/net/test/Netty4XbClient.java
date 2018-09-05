package app.client.net.test;

import app.client.net.dispacher.DispacherManager;
import app.client.net.socket.GowildDecoder;
import app.client.net.socket.GowildEncoder;
import app.client.net.socket.GowildHandler;
import app.client.net.socket.GowildXbHandler;
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
public class Netty4XbClient implements Closeable{

    private static final Logger logger = LoggerFactory
            .getLogger(Netty4XbClient.class);

    private static final int DEFAULT_MSG_SIZE_LIMIT = 1200;

//    private final int PORT = 6100;
//    private final String HOST = "tcp01.xb.test01.gowild.top";

//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.41";
//    public static final String TOKEN_URL = "http://172.27.1.41:6130/oauth/robot/token";

    public static final int PORT = 6030;
    public static final String HOST = "172.27.1.49";
    public static final String TOKEN_URL = "http://172.27.1.49:6130/oauth/robot/token";

//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.73";
//    public static final String TOKEN_URL = "http://172.27.1.73:6130/oauth/robot/token";


    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.remoteAddress(new InetSocketAddress(HOST, PORT));
            b.handler(new ChannelInitializer<SocketChannel>() {

                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new GowildXbHandler());
//                    ch.pipeline().addLast(new GowildEncoder());
//                    ch.pipeline().addLast(new GowildDecoder());

                    ch.pipeline().addLast(new GowildDecoder());
                    ch.pipeline().addLast(new GowildEncoder());
                    ch.pipeline().addLast(new GowildXbHandler());

                }
            });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    @Override
    public void close(){
    }

    public void init() throws Exception{
    }

    public static void main(String[] args) throws Exception {
        Netty4XbClient xbClient = new Netty4XbClient();
        xbClient.init();
        xbClient.start();
    }
}
