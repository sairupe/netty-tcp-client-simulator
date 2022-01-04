package app.client.net.protocol;

import app.client.user.session.UserSession;
import io.netty.buffer.ByteBuf;
import lombok.Data;

/**
 * 
 * @author syriana.zh
 *
 * 应答消息抽象类,对应服务端下发的协议实体
 * 2016年4月15日 下午3:07:02
 */
@Data
public abstract class ResponseProtocol extends AbstractProtocol{
	
    private UserSession userSession;

    protected ByteBuf bodyBuf;

    /**
     * 读取服务端的二进制流
     */
    public abstract boolean readBinaryData();

}
