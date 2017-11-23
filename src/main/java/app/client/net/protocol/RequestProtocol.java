package app.client.net.protocol;


import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author syriana.zh
 *
 * 请求协议抽象类,对应发送给服务端的协议实体
 * 2016年4月15日 下午3:05:33
 */
public abstract class RequestProtocol extends AbstractProtocol{
	
    private ChannelHandlerContext ctx;
    
    public abstract void writeBinaryData();
	
	
	@Override
    public boolean readBinaryData(){
		return false;
	}

    public ChannelHandlerContext getCtx(){
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }

}
