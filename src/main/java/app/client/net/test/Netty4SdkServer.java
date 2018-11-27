//package app.client.net.test;
//
//import app.client.net.socket.GowildDecoder;
//import app.client.net.socket.GowildEncoder;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//
///**
// * Created by zh on 2017/10/23.
// */
//public class Netty4SdkServer {
//
//    /**
//     * 接收器
//     */
//    private ServerBootstrap serverBootstrap;
//
//    private static int port = 6130;
//
//    public void start() throws Exception {
//        serverBootstrap = new ServerBootstrap();
//        //NioEventLoopGroup就是存放EventLoop是一个比线程更牛逼的东西，他可以有调度功能(schedule)，EventLoopGroup
//        //是封装NioEventLoop的容器。
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2 + 1);
//        //前者用来处理accept事件，后者用于处理已经建立的连接的socket的读，写事件
//        serverBootstrap.group(bossGroup, workerGroup);
//        serverBootstrap.channel(NioServerSocketChannel.class);
//        //自己实现ChannelHandler
//        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
//            @Override
//            protected void initChannel(final SocketChannel ch)
//                    throws Exception {
//                //ChannelPipeline和ChannelHandler的：ChannelPipeline是ChannelHandler的容器。
//                //添加ChannelHandler到ChannelPipeline里面。
//                ChannelPipeline pipeline = ch.pipeline();
//                pipeline.addLast(new GowildDecoder());
//                pipeline.addLast(new GowildEncoder());
//                pipeline.addLast(new GowildHandler());
//            }
//        });
//        //设置ServerSocket的参数
//        // 默认3072 或者读取 /proc/sys/net/core/somaxconn这个里面的值 这里设置的是已连接的队列数量。要设置未连接
//        //的队列数量。在/proc/sys/net/ipv4/tcp_max_syn_backlog
//        //server socket只支持: SO_RCVBUF  SO_REUSEADDR  SO_BACKLOG
//        serverBootstrap.option(ChannelOption.SO_BACKLOG, 3072); //队列数量
//        //是否重用端口
//        serverBootstrap.option(ChannelOption.SO_REUSEADDR, false);
//        ///设置Socket的参数
//        serverBootstrap.childOption(ChannelOption.SO_RCVBUF, 1024 * 8);  //默认是8K
//        serverBootstrap.childOption(ChannelOption.SO_SNDBUF, 1024 * 8); //8K
//        //等待N秒再关闭底层socket连接，0为立即关闭底层socket连接
//        serverBootstrap.childOption(ChannelOption.SO_LINGER, 0);
//        //和TCP_CORK作用相反，关闭传输缓存，默认为false  含义是关闭采用Nagle算法把较小的包组装为更大的帧。
//        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
//        //是否开启保持活动状态的套接字。
//        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
//        //是否重用端口
//        serverBootstrap.childOption(ChannelOption.SO_REUSEADDR, false);
//        serverBootstrap.bind(port).sync();
//    }
//
//    public static void main(String[] args){
//
//    }
//}
