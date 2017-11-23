package app.client.user.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:58
 */
public class UserSessionManager {
	
	private static UserSessionManager instance = new UserSessionManager();
	
	private UserSessionManager(){
		
	}
	
	private Map<Long, UserSession> channelId2SessionMap = new ConcurrentHashMap<Long, UserSession>();
	
	
	public void addUserSession(long channelId, UserSession userSession){
		if(!channelId2SessionMap.containsKey(channelId)){
			channelId2SessionMap.put(channelId, userSession);
		}
	}
	
	public void removeUserSessionByChannelId(long channelId){
		if(channelId2SessionMap.containsKey(channelId)){
			UserSession userSession = channelId2SessionMap.get(channelId);
			userSession.cancelTickTask();
			channelId2SessionMap.remove(channelId);
		}
	}
	
	public UserSession getUserSessionByChannelId(long channelId){
		return channelId2SessionMap.get(channelId);
	}
	
	public static UserSessionManager getInstance(){
		return instance;
	}
}
