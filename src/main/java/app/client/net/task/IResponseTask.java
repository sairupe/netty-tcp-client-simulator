package app.client.net.task;

import app.client.net.protocol.ResponseProtocol;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:20:33
 */
public interface IResponseTask extends Runnable{
	
	public void dispach(ResponseProtocol response);
	
}
