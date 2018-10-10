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
	
	private Map<Long, UserSession> uid2SessionMap = new ConcurrentHashMap<Long, UserSession>();
	
	
	public void addUserSession(long uid, UserSession userSession){
		if(!uid2SessionMap.containsKey(uid)){
			uid2SessionMap.put(uid, userSession);
		}
	}
	
	public void removeUserSessionByUid(long uid){
		if(uid2SessionMap.containsKey(uid)){
			UserSession userSession = uid2SessionMap.get(uid);
			uid2SessionMap.remove(uid);
			userSession.getCtx().close();
		}
	}
	
	public UserSession getUserSessionByUid(long uid){
		return uid2SessionMap.get(uid);
	}
	
	public static UserSessionManager getInstance(){
		return instance;
	}

	public Map<Long, UserSession> getUid2SessionMap() {
		return uid2SessionMap;
	}
}
