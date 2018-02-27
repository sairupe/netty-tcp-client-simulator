package app.client.net.dispacher;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import app.client.net.annotation.Handler;
import app.client.net.annotation.Protocol;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.ResponseProtocol;
import app.client.service.IService;
import app.client.testchain.IChainNode;
import app.client.utils.ClassUtil;
import app.client.utils.ClientUtil;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:18:41
 */
public class DispacherManager {

	private static DispacherManager instance = new DispacherManager();

	private static Map<Integer, ProtocolHandlerHolder> protocolId2Method = new ConcurrentHashMap<Integer, ProtocolHandlerHolder>();

	private IChainNode chainNode;
	/**
    * 初始化分发MAP。在GlobalServiceManager static静态块初始化完成后再初始化这个
    */
	public void init() {
		try {
            // 加载所有的类型
			Set<Class<?>> set = ClassUtil.getClasses(IService.class.getPackage().getName());
			for (Class<?> clz : set) {
				Receiver receiver = clz.getAnnotation(Receiver.class);
				if (receiver != null) {
					IService receiverImpl = (IService) clz.newInstance();
					ServiceManager.addIServiceInstance(receiverImpl);
					Method[] methods = clz.getDeclaredMethods();
					for (Method method : methods) {
//						Handler handler = method.getAnnotation(Handler.class);
//						if (handler != null) {
//							ProtocolHandlerHolder holder = new ProtocolHandlerHolder();
//                            int moduleId = handler.moduleId();
//                            int sequenceId = handler.sequenceId();
//                            int key = ClientUtil.buildProtocolKey(moduleId,
//                                    sequenceId);
//							holder.setMethod(method);
//							holder.setServiceImpl(receiverImpl);
//                            protocolId2Method.put(key, holder);
//						}
						// 直接拿第一个参数协议判断
						Class<?>[] params = method.getParameterTypes();
						if(params != null && params.length > 0){
							Class<?> firstParam = params[0];
							if(ResponseProtocol.class.isAssignableFrom(firstParam)){
								Protocol protocol = firstParam.getAnnotation(Protocol.class);
								if(protocol != null){
									int moduleId = protocol.moduleId();
									int sequenceId = protocol.sequenceId();
									int key = ClientUtil.buildProtocolKey(moduleId,sequenceId);
									ProtocolHandlerHolder holder = new ProtocolHandlerHolder();
									holder.setMethod(method);
									holder.setServiceImpl(receiverImpl);
									protocolId2Method.put(key, holder);
								}
							}
						}
					}
				}
			}
            // 回调。初始化ServiceImpl的相互引用依赖
			Collection<ProtocolHandlerHolder> holderSet = protocolId2Method.values();
			for(ProtocolHandlerHolder holder : holderSet){
				holder.getServiceImpl().initRelyService();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
    public void dispatch(int moduleId, int sequenceId,
            ResponseProtocol response){
		try {
            int key = ClientUtil.buildProtocolKey(moduleId, sequenceId);
            ProtocolHandlerHolder holder = protocolId2Method.get(key);
			if(holder != null){
				Method method = holder.getMethod();
				method.invoke(holder.getServiceImpl(), response);
			}
			// 分发到链式调用
			if(chainNode != null){
				chainNode.sniff(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private DispacherManager() {

	}

	public static DispacherManager getInstance() {
		return instance;
	}

	public void setChainNode(IChainNode chainNode){
    	this.chainNode = chainNode;
	}
}
