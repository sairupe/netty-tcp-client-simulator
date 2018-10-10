package app.client.net.test;

import app.client.data.StatisticHolder;
import app.client.net.dispacher.DispacherManager;
import app.client.net.socket.EventLoopHolder;
import app.client.net.socket.GowildDecoder;
import app.client.net.socket.GowildEncoder;
import app.client.net.socket.GowildHandler;
import app.client.net.socket.GowildXbHandler;
import app.client.net.task.TaskManager;
import com.google.common.annotations.VisibleForTesting;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
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

    private String mac;
    private String token;
    private int robotId;

//    private final int PORT = 6100;
//    private final String HOST = "tcp01.xb.test01.gowild.top";

//    测试服
//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.41";
//    public static final String TOKEN_URL = "http://172.27.1.41/oauth/robot/token";

//    自己服
//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.49";
//    public static final String TOKEN_URL = "http://172.27.1.49:6130/web/oauth/robot/token";

//    开发服
    public static final int PORT = 6030;
    public static final String HOST = "172.27.1.73";
    public static final String TOKEN_URL = "http://172.27.1.73:6130/oauth/robot/token";

//    内网机子
//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.151";
//    public static final String TOKEN_URL = "http://172.27.1.151/oauth/robot/token";

//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.166";
//    public static final String TOKEN_URL = "http://172.27.1.166/oauth/robot/token";
//
//    public static final int PORT = 6030;
//    public static final String HOST = "172.27.1.180";
//    public static final String TOKEN_URL = "http://172.27.1.180/oauth/robot/token";

//    外网4.0
//    public static final int PORT = 6030;
//    public static final String HOST = "jarvis-tcp.gowild.info";
//    public static final String TOKEN_URL = "http://jarvis-tcp.gowild.info/oauth/robot/token";



    public void start() throws Exception{
        try {
            Bootstrap b = new Bootstrap();
            b.group(EventLoopHolder.getGroup());
            b.channel(NioSocketChannel.class);
            b.remoteAddress(new InetSocketAddress(HOST, PORT));
            b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000);
            b.handler(new ChannelInitializer<SocketChannel>() {

                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new GowildXbHandler());
//                    ch.pipeline().addLast(new GowildEncoder());
//                    ch.pipeline().addLast(new GowildDecoder());

                    ch.pipeline().addLast(new GowildDecoder());
                    ch.pipeline().addLast(new GowildEncoder());
                    GowildXbHandler gowildXbHandler = new GowildXbHandler();
                    gowildXbHandler.setMac(mac);
                    gowildXbHandler.setToken(token);
                    gowildXbHandler.setRobotId(robotId);
                    ch.pipeline().addLast(gowildXbHandler);
                }
            });
            ChannelFuture f = b.connect().sync();
            StatisticHolder.incRobotClient();
//            logger.info("===================>>>>啓動XB BOOTSTRAP，目前CLIENT數量為：" + StatisticHolder.getRobotCount());
//            f.channel().closeFuture().sync();
        } catch (Exception e){
            logger.error("启动error", e);
        }
        finally {
//            EventLoopHolder.getGroup().shutdownGracefully().sync();
        }
        StatisticHolder.decRobot();
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

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }
}
