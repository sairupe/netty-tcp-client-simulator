package app.client.net.protocol;


import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 
 * @author syriana.zh
 *
 * 请求协议抽象类,对应发送给服务端的协议实体
 * 2016年4月15日 下午3:05:33
 */
@Data
public abstract class RequestProtocol extends AbstractProtocol{
	
    private ChannelHandlerContext ctx;

    protected ChannelBuffer writeBuf;
    
    public abstract void writeBinaryData();

    public ChannelHandlerContext getCtx(){
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }

}
