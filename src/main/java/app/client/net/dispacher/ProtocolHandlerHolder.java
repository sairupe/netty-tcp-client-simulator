package app.client.net.dispacher;

import java.lang.reflect.Method;

import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:18:18
 */
public class ProtocolHandlerHolder {
	
	    /**
     * 具体的service实体类
     */
	private IService serviceImpl;
	    /**
     * 需要反射调用的方法
     */
	private Method method;

	public IService getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(IService serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
}
