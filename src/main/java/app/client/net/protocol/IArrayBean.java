package app.client.net.protocol;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 
 * @author syriana.zh
 * 
 * 所有Proto对象中的数组成员变量(Array)抽象出的实体都继承这个接口。
 * 简单调用接口即可实现读写，减少readBinaryData和writeBinaryData的复杂性
 *
 * 2016年5月6日 下午5:56:16
 */
public interface IArrayBean {
	
    public void readDataFromBuffer(ChannelBuffer buffer);
	
    public void writeDataToBuffer(ChannelBuffer buffer);
}
