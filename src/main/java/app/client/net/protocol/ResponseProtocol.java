package app.client.net.protocol;

import app.client.user.session.UserSession;

/**
 * 
 * @author syriana.zh
 *
 * 应答消息抽象类,对应服务端下发的协议实体
 * 2016年4月15日 下午3:07:02
 */
public abstract class ResponseProtocol extends AbstractProtocol{
	
    private UserSession userSession;

	@Override
    public void writeBinaryData(){
	}

    public UserSession getUserSession(){
        return userSession;
    }

    public void setUserSession(UserSession userSession){
        this.userSession = userSession;
    }
}
