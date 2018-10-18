package app.client.net.protocol;//package app.client.net.protocol;
//
//import java.util.List;
//
//import org.jboss.netty.channel.ChannelHandlerContext;
//
///**
// * @author syriana.zh
// *
// * 基于消息定长解析的解码器
// * 2016年4月15日 下午2:17:54
// */
//public final class BaseDecoder extends ByteToMessageDecoder{
//	
////	 * +------------------------+-------------+---------------+
////	 * | Protocol Toltal Length | Protobuf ID | Protocol Data |
////	 * | 消息总长度                        | 协议号           |    协议数据流   |
////	 * | (4 bytes)              | (2 bytes)   |   (? bytes)   |
////	 * +------------------------+-------------+---------------+
//	
//    // 消息头4个字节
//	public static final int PROTOCOL_LENGTH = 4;
//    // 协议号2个字节
//	public static final int PROTOCOL_ID_LENGTH = 2;
//    // 基础消息头长度6个字节
//	public static final int PROTOCOL_BASE_LENGTH = PROTOCOL_LENGTH + PROTOCOL_ID_LENGTH;
//    // 消息数据缓冲BUFFER长度
//	public static final int PROTOCOL_CACHE_BUFFER_SIZE = 2048;
//	
//    // 现在接受协议目前的状态
//	private int nowProtocolStatus = 0;
//	private int nowProtocolTotalSize = 0;
//	
//    // 现在协议是需要消息总长度和协议号ID的状态
//	private final static int PROTOCOL_STATUS_NEED_HEADER = 0;
//    // 现在协议是需要完整的协议二进制流的状态
//	private final static int PROTOCOL_STATUS_NEED_BODY = 1;
//	
//	@Override
//	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
//		switch (nowProtocolStatus) {
//			case PROTOCOL_STATUS_NEED_HEADER:{
//				if (buffer.readableBytes() < PROTOCOL_LENGTH + PROTOCOL_ID_LENGTH) {
//					return;
//				}
//				nowProtocolTotalSize = buffer.readInt();
//				nowProtocolStatus = PROTOCOL_STATUS_NEED_BODY;
//			}
//			case PROTOCOL_STATUS_NEED_BODY:{
//				if (buffer.readableBytes() < nowProtocolTotalSize - PROTOCOL_LENGTH) {
//					return;
//				}
//				int dataSize = nowProtocolTotalSize - PROTOCOL_LENGTH;
//				ByteBuf dataInputStream = Unpooled.buffer(dataSize, PROTOCOL_CACHE_BUFFER_SIZE);
//				dataInputStream.writeBytes(buffer, dataSize);
//				nowProtocolStatus = PROTOCOL_STATUS_NEED_HEADER;
//				out.add(dataInputStream);
//				return;
//			}
//			default:{
//				throw new Error("Decode Protocol Status Error");
//			}
//		}
//	}
//}
