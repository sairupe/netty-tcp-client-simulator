package app.client.net.socket;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * Created by zh on 2018/9/5.
 */
public class EventLoopHolder {

    private final static EventLoopGroup group = new NioEventLoopGroup();

    public static EventLoopGroup getGroup() {
        return group;
    }
}
