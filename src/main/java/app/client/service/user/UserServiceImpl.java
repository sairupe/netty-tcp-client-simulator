package app.client.service.user;

import app.client.data.StatisticHolder;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.S_APP_LOGIN;
import app.client.service.AbstractServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author syriana.zh
 * <p>
 * 2016年4月21日 下午3:21:43
 */
@Slf4j
@Receiver
public class UserServiceImpl extends AbstractServiceImpl implements IUserService {

    @Override
    public void receivedAppLoginRes(S_APP_LOGIN response) {
        Short code = response.getCode();
        Long userId = response.getUserId();
        log.info("====== >>> APP【{}】登录结果，code:{} | userId:{} ", code, code, userId);
    }
}
