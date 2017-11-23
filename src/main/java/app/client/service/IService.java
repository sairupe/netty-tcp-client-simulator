package app.client.service;

/**
 * 
 * @author syriana.zh
 *
 * 空的接口，为了在ProtocolHandlerHolder中使用多态
 * 每一个Service的接口都应该extends这个接口
 * 2016年4月21日 下午3:01:14
 */
public interface IService {
	    /**
     * 因为设计是所有Service是单例存储在ServiceManager中管理的。
     * 如果Service有相互依赖（既相互是成员变量的问题），
     * 需要加入这个接口进行初始化完成的回调赋值,而并不是在构造函数中赋值
     */
	public void initRelyService();
}
