package app.client.net.socket;

/**
 * Created by zh on 2017/10/23.
 */
/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:21:57
 */

import com.gowild.tcp.core.manager.socket.Connection;
import com.gowild.tcp.core.manager.socket.ConnectionEventType;
import com.gowild.tcp.core.manager.socket.Message;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import com.gowild.tcp.core.manager.socket.Connection;
import com.gowild.tcp.core.manager.socket.ConnectionEventType;
import com.gowild.tcp.core.manager.socket.Message;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Connection的netty实现
 *
 * @author Dream.xie
 */
public final class GowildConnection extends Connection {
    /**
     * nio抽象连接
     */
    private NioSocketChannel session;

    /**
     */
    GowildConnection(final NioSocketChannel session) {
        this.session = session;
        InetSocketAddress socketAddr = session.remoteAddress();
        InetSocketAddress localSocketAddr = session.localAddress();
        this.setHostAddress(socketAddr.getAddress().getHostAddress());
        this.setPort(socketAddr.getPort());
        this.setLocalHostaddress(localSocketAddr.getAddress().getHostAddress());
        this.setLocalPort(localSocketAddr.getPort());
    }

    @Override
    public void sendMessage(final Message message) {
        if (isConnected()) {
            if (this.notifyVoteListeners(ConnectionEventType.BEFORE_SEND_MSG_CMD, message)) {
                session.writeAndFlush(message);
            }
        }
    }

    /**
     * 关闭连接
     */
    @Override
    public void closeConnection() {
        if (isConnected()) {
            session.close();
        }
    }

    @Override
    public Boolean isConnected() {
        return session.isOpen();
    }

    @Override
    public Boolean isClosed() {
        return !session.isOpen();
    }

    /**
     * @return the session
     */
    public NioSocketChannel getSession() {
        return session;
    }

}
