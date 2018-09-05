package app.client.net.test;

import app.client.net.dispacher.DispacherManager;
import app.client.net.socket.*;
import app.client.net.task.TaskManager;
import com.google.common.annotations.VisibleForTesting;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.swagger.annotations.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * @author syriana.zh
 */
public class Netty4SdkClient implements Closeable{

    private static final Logger logger = LoggerFactory
            .getLogger(Netty4SdkClient.class);

    private static final int DEFAULT_MSG_SIZE_LIMIT = 1200;

//    private final int port = 6130;
//    private final String host = "localhost";

    private final int port = 7030;
    private final String host = "172.27.1.49";


    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.remoteAddress(new InetSocketAddress(host, port));
            b.handler(new ChannelInitializer<SocketChannel>() {

                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(GowildEncoder.getGowildEncoder());
                    ch.pipeline().addLast(GowildDecoder.getGowildDecoder());
                    ch.pipeline().addLast(new GowildHandler());

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
        Netty4SdkClient client = new Netty4SdkClient();
        client.init();
        client.start();
    }
}
