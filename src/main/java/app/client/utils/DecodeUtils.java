package app.client.utils;
import java.io.UnsupportedEncodingException;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月15日 下午3:36:28
 */
public class DecodeUtils {

    public static byte getByte(ChannelBuffer buffer){
		return buffer.readByte();
	}
	
    public static short getUnsignByte(ChannelBuffer buffer){
		return buffer.readUnsignedByte();
	}
	
    public static short getShort(ChannelBuffer buffer){
		return buffer.readShort();
	}
	
    public static int getUnsignShort(ChannelBuffer buffer){
		return buffer.readUnsignedShort();
	}
	
    public static int getInt(ChannelBuffer buffer){
		return buffer.readInt();
	}
	
    public static long getUnsignInt(ChannelBuffer buffer){
		return buffer.readUnsignedInt();
	}
	
    public static long getLong(ChannelBuffer buffer){
		return buffer.readLong();
	}
	
    public static String getString(ChannelBuffer buffer){
		short length = buffer.readShort();
		byte[] strData = new byte[length];
		buffer.readBytes(strData);
		try {
			return new String(strData, "uft-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
