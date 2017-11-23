package app.client.common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.jboss.netty.buffer.ChannelBuffer;

public class ProtocolUtils {
		// =================================================
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
		
    public static BigInteger getUnsignLong(ChannelBuffer buffer){
			long num = buffer.readLong();
			byte[] unsignLongByteArray = new byte[9];
			unsignLongByteArray[0] = 0;
			unsignLongByteArray[1] = (byte) (num >> 56);
			unsignLongByteArray[2] = (byte) (num >> 48);
			unsignLongByteArray[3] = (byte) (num >> 40);
			unsignLongByteArray[4] = (byte) (num >> 32);
			unsignLongByteArray[5] = (byte) (num >> 24);
			unsignLongByteArray[6] = (byte) (num >> 16);
			unsignLongByteArray[7] = (byte) (num >> 8);
			unsignLongByteArray[8] = (byte) num;
			return new BigInteger(unsignLongByteArray);
		}
		
    public static String getString(ChannelBuffer buffer){
			int length = buffer.readUnsignedShort();
			byte[] strData = new byte[length];
			buffer.readBytes(strData);
			try {
				return new String(strData, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
    }

    public static byte[] getLastReableBytes(ChannelBuffer buffer){
        byte[] reableBytes = new byte[buffer.readableBytes()];
        buffer.readBytes(reableBytes);
        return reableBytes;
    }
		
		//============================================
		
    public static void writeBytes(ChannelBuffer base, ChannelBuffer dataBuffer){
			base.writeBytes(dataBuffer);
    }
		
    public static void writeByte(ChannelBuffer buffer, byte byteValue){
		buffer.writeByte(byteValue);
    }
		
    public static void writeShort(ChannelBuffer buffer, short shortValue){
		buffer.writeShort(shortValue);
    }
		
    public static void writeInt(ChannelBuffer buffer, int intValue){
        buffer.writeInt(intValue);
    }
		
    public static void writeLong(ChannelBuffer buffer, long longValue){
        buffer.writeLong(longValue);
    }
		
	/**
    * 不要SB的取扩展byteValue的范围，能用最短就用short,不要用int和long
    */
    public static void writeUnsignByte(ChannelBuffer buffer, short shortValue){
			byte value = (byte) shortValue;
			buffer.writeByte(value);
		}
		
		
	/**
    * 不要SB的取扩展intValue的范围，能用最短就用int,不要用long
    */
    public static void writeUnsignShort(ChannelBuffer buffer, int intValue){
			byte value1 = (byte) (intValue >> 8);
			byte value2 = (byte) (intValue);
			buffer.writeByte(value1);
			buffer.writeByte(value2);
		}
		
    public static void writeUnsignInt(ChannelBuffer buffer, long longValue){
			byte value1 = (byte) (longValue >> 24);
			byte value2 = (byte) (longValue >> 16);
			byte value3 = (byte) (longValue >> 8);
			byte value4 = (byte) longValue;
			buffer.writeByte(value1);
			buffer.writeByte(value2);
			buffer.writeByte(value3);
			buffer.writeByte(value4);
		}
		
    public static void writeUnsignLong(ChannelBuffer buffer,
            BigInteger unsignLong){
        // 这里BYTE[]的长度一般不固定，从最末尾开始写入
			byte[] bytes = unsignLong.toByteArray();
			byte[] sendBytes = new byte[8];
			if(bytes.length <= 8){
				System.arraycopy(bytes, 0, sendBytes, sendBytes.length - bytes.length, bytes.length);
			}
			else{
				System.arraycopy(bytes, bytes.length - 8, sendBytes, 0, 8);
			}
			buffer.writeBytes(sendBytes);
		}

	// 字符串解析需要前后端对应
    public static void writeString(ChannelBuffer buffer, String string){
			try {
				byte[] bytes = string.getBytes("utf-8");
				int length = bytes.length;
				writeUnsignShort(buffer, length);
				buffer.writeBytes(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	}
}
