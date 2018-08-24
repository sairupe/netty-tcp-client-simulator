package app.client.net.protocol;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;
import org.jboss.netty.buffer.ChannelBuffer;

import app.client.net.annotation.Protocol;
import app.client.user.session.UserSessionManager;
import app.client.utils.ClassUtil;
import app.client.utils.ClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author syriana.zh
 * 
 *         协议原型提供工厂
 * 
 *         2016年4月15日 下午2:40:06
 */
public class ProtocolFactory {

    private static Logger logger = LoggerFactory.getLogger(ProtocolFactory.class);

	private static Map<Integer, RequestProtocol> requestPrototypeMap = new ConcurrentHashMap<Integer, RequestProtocol>();

	private static Map<Integer, ResponseProtocol> reponsePrototypeMap = new ConcurrentHashMap<Integer, ResponseProtocol>();

    /**
     * 包的标识 32766
     */
    public static final short HEADER = 0x7ffe;

	private ProtocolFactory() {

	}

	static {
		try {
            Set<Class<?>> set = ClassUtil.getClasses(IProtocol.class
                    .getPackage().getName());
			for (Class<?> clz : set) {
				Protocol cmd = clz.getAnnotation(Protocol.class);
				if (cmd != null) {
                    int moduleId = cmd.moduleId();
                    int sequenceId = cmd.sequenceId();
                    int key = ClientUtil.buildProtocolKey(moduleId, sequenceId);
					ProtocolType type = cmd.type();
					if (type == ProtocolType.REQUSET) {
						RequestProtocol request = (RequestProtocol) clz.newInstance();
                        requestPrototypeMap.put(key, request);
					} else {
						ResponseProtocol response = (ResponseProtocol) clz.newInstance();
                        reponsePrototypeMap.put(key, response);
					}
				}
			}
            System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @SuppressWarnings("unchecked")
    @Deprecated
    public static <T extends RequestProtocol> T createRequestProtocol(
            int moduleId, int sequenceId){
//		RequestProtocol protype = null;
//        try{
//            int key = ClientUtil.buildProtocolKey(moduleId, sequenceId);
//            protype = requestPrototypeMap.get(key).getClass()
//                    .newInstance();
//            ChannelBuffer buffer = ClientUtil.getDynamicBuffer();
//            protype.setBuffer(buffer);
//            protype.setModuleIdAndSequenceId(moduleId, sequenceId,
//                    ProtocolType.REQUSET);
//            return (T) protype;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//		return (T) protype;
        return null;
	}

    public static <T extends RequestProtocol> T createRequestProtocol(
            Class<? extends RequestProtocol> clazz, ChannelHandlerContext ctx){
        RequestProtocol protype = null;
        try{
            Protocol protocolAnnotation = clazz.getAnnotation(Protocol.class);
            if (protocolAnnotation != null){
                int moduleId = protocolAnnotation.moduleId();
                int sequenceId = protocolAnnotation.sequenceId();
                int key = ClientUtil.buildProtocolKey(moduleId, sequenceId);
                protype = requestPrototypeMap.get(key).getClass().newInstance();
                ChannelBuffer buffer = ClientUtil.getDynamicBuffer();
                protype.setBuffer(buffer);
                /**
                 * 协议包结构
                 * +----------------+--------------+-----------+----------+-------------------------+
                 * | HEADER         | Total_Length | Code      | Type     | ProtoBuffer Bytes Array |
                 * | 0x7FFE(2 byte) | (2 byte)     | (2 byte)  | (1 byte) | ...                     |
                 * +----------------+--------------+-----------+----------+-------------------------+
                 *
                 * Total Length = sizeof(HEADER) + sizeof(Total_Length) + sizeof(Code) + sizeOf(Type) + sizeOf(ProtoBuffer Bytes Array)
                 */
                // 保留前面4个字节填写长度和OFFSET
                buffer.writeShort(HEADER);
                // 写入长度,暂时写入0,真正写入数据后再发送
                buffer.writeShort(0);
                // 写入code
                buffer.writeShort((short) sequenceId);
                // 写入type
                buffer.writeByte((byte) moduleId);
                protype.setModuleIdAndSequenceId(moduleId, sequenceId,
                        ProtocolType.REQUSET);
                protype.setCtx(ctx);
            }
            return (T) protype;
        } catch (Exception e){
            e.printStackTrace();
        }
        return (T) protype;
    }

	@SuppressWarnings("unchecked")
    public static <T extends ResponseProtocol> T getResponseProtocol(
            int moduleId, int sequenceId, ChannelBuffer buffer, long channelId){
		ResponseProtocol protype = null;
        try{
            int key = ClientUtil.buildProtocolKey(moduleId, sequenceId);
            if (reponsePrototypeMap.get(key) == null){
                System.out.println("===========protoclId 空: moduleId:{" + moduleId + "}, sequenceId:{" + sequenceId + "}");
                return null;
            }
            protype = reponsePrototypeMap.get(key).getClass()
                    .newInstance();
            protype.setModuleIdAndSequenceId(moduleId, sequenceId,
                    ProtocolType.RESPONSE);
            protype.setUserSession(UserSessionManager.getInstance()
                    .getUserSessionByChannelId(channelId));
            protype.setBuffer(buffer);
            protype.readBinaryData();
            return (T) protype;
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            // 暂时不知道这个是de(buffer);
        }
		return (T) protype;
	}
}
