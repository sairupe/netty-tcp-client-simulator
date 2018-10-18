package app.client.net.protocol;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月15日 下午3:02:18
 */
public interface IProtocol {
	    /**
     * 设置协议ID
     */
	public void setProtocolId(int protocolId);
	
    /**
     * 获取协议ID
     */
	public int getProtocolId();
}
