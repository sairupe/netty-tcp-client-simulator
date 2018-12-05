package app.client.net.dispacher;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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

	/**
	 * 单独监听某个协议返回而执行的MAP，需要主动执行注册
	 */
	private static Map<Integer, Queue<IChainNode>> snipperMap = new ConcurrentHashMap<>();

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
			// 清空监听队列，这里又监听又发送的，可能有并发问题
			Queue<IChainNode> snipperNodeQueue = snipperMap.get(key);
			if(snipperNodeQueue != null && !snipperNodeQueue.isEmpty()){
				while(!snipperNodeQueue.isEmpty()){
					IChainNode node = snipperNodeQueue.poll();
					if(node != null){
						node.execute();
					}
				}
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


	public void addRegistryNode(int protolId, IChainNode chainNode){
		Queue<IChainNode> snipperNodeQueue = snipperMap.get(protolId);
		if(snipperNodeQueue == null){
			snipperNodeQueue = new ConcurrentLinkedQueue<>();
			snipperMap.put(protolId, snipperNodeQueue);
		}
		snipperNodeQueue.offer(chainNode);
	}
}
