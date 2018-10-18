package app.client.net.protocol;

import java.math.BigInteger;

import org.jboss.netty.buffer.ChannelBuffer;

import app.client.common.ProtocolUtils;


/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:19:31
 */
public abstract class AbstractProtocol{
	
    private int moduleId;
	
    private int sequenceId;
	
    protected ChannelBuffer buffer;
	
    protected void setModuleIdAndSequenceId(int moduleId, int sequenceId,
            ProtocolType protocolType){
        this.moduleId = moduleId;
        this.sequenceId = sequenceId;
	}

    public int getModuleId(){
        return moduleId;
	}

    public int getSequenceId(){
        return sequenceId;
    }
	
	// =================================================
	protected byte getByte(){
        return ProtocolUtils.getByte(buffer);
	}

	protected short getUnsignByte(){
        return ProtocolUtils.getUnsignByte(buffer);
	}

	protected short getShort(){
        return ProtocolUtils.getShort(buffer);
	}

	protected int getUnsignShort(){
        return ProtocolUtils.getUnsignShort(buffer);
	}

	protected int getInt(){
        return ProtocolUtils.getInt(buffer);
	}

	protected long getUnsignInt(){
        return ProtocolUtils.getUnsignInt(buffer);
	}

	protected long getLong(){
        return ProtocolUtils.getLong(buffer);
	}

	protected BigInteger getUnsignLong(){
        return ProtocolUtils.getUnsignLong(buffer);
	}

	protected String getString(){
        return ProtocolUtils.getString(buffer);
	}

    protected byte[] getLastReableBytes(){
        return ProtocolUtils.getLastReableBytes(buffer);
    }

	//============================================

	protected void writeBytes(ChannelBuffer byteBuf){
        ProtocolUtils.writeBytes(buffer, byteBuf);
	}

	protected void writeBytes(byte[] bytes){
		ProtocolUtils.writeBytes(buffer, bytes);
	}


	protected void writeByte(byte byteValue){
        ProtocolUtils.writeByte(buffer, byteValue);
	}

	protected void writeShort(short shortValue){
        ProtocolUtils.writeShort(buffer, shortValue);
	}

	protected void writeInt(int intValue){
        ProtocolUtils.writeInt(buffer, intValue);
	}

	protected void writeLong(long longValue){
        ProtocolUtils.writeLong(buffer, longValue);
	}

	/*                                                                                                                                                                                                                                                                                                                                        /**
    * 不要SB的取扩展byteValue的范围，能用最短就用short,不要用int和long
    */
	protected void writeUnsignByte(short shortValue){
        ProtocolUtils.writeUnsignByte(buffer, shortValue);
	}

    /**
    * 不要SB的取扩展intValue的范围，能用最短就用int,不要用long
    */
	protected void writeUnsignShort(int intValue){
        ProtocolUtils.writeUnsignShort(buffer, intValue);
	}

	protected void writeUnsignInt(long longValue){
        ProtocolUtils.writeUnsignInt(buffer, longValue);
	}

	protected void writeUnsignLong(BigInteger longValue){
        ProtocolUtils.writeUnsignLong(buffer, longValue);
	}

	protected void writeString(String string){
        ProtocolUtils.writeString(buffer, string);
	}

	// =================================================================
    public void setBuffer(ChannelBuffer buffer){
		this.buffer = buffer;
	}
	
    public ChannelBuffer getBuffer(){
        return buffer;
    }

	                                                                                                                                                                                                                                                                                                                                    /**
    * 读协议二进制流
    */
    public abstract void writeBinaryData();
	
	                                                                                                                                                                                                                                                                                                                                        /**
    * 读取服务端的二进制流
    */
    public abstract boolean readBinaryData();
}
