package app.client.user.session;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:49
 */
public enum ConnectStatus {
    CONNECTING,
	// 正常连接中
    CONNECT_FNISHED,	// 连接完成
    CONNECT_LOST,	// 连接断开
    CONNECT_RE,	// 重连中
    USER_EXIT;// 用户主动退出
}
