package app.client.net.dispacher;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月22日 下午7:38:26
 */
public class ServiceManager {
	
	private ServiceManager() {
		
	}
	
	private static Map<Class<?>, IService> clazz2ServiceInstanceMap = new ConcurrentHashMap<Class<?>, IService>();
	
	protected static void addIServiceInstance(IService service) {
		clazz2ServiceInstanceMap.put(service.getClass(), service);
	}
	
	public static Object getService(Class<? extends IService> clazz){
		return clazz2ServiceInstanceMap.get(clazz);
	}
	
	public static void injectionReceiver(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		if (fields != null && fields.length != 0) {
			for (Field field : fields) {
				Resource annotion = field.getAnnotation(Resource.class);
				if (annotion != null) {
					Type type = field.getGenericType();
					if (type instanceof Class<?>) {
						Class<?> typeClass = (Class<?>) type;
						if (!IService.class.isAssignableFrom(typeClass)) {
                            System.err.println(obj.getClass().getName()
                                    + "对象中存在Resource注解标志Receiver类使用错误!!!!!");
							continue;
						}
						field.setAccessible(true);
						IService service = clazz2ServiceInstanceMap.get(typeClass);
						if (service == null) {
                            System.err.println("ServiceManager中不存在"
                                    + typeClass.getName() + "的类型");
							continue;
						}
						try {
							field.set(obj, service);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
