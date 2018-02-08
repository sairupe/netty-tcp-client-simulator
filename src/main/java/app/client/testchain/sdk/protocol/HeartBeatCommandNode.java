package app.client.testchain.sdk.protocol;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.C_DEVICE_HEART_BEAT;
import app.client.net.task.SdkDeviceHeartBeatTask;
import app.client.net.task.TaskManager;
import app.client.testchain.ProtocolListenNode;

import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017/11/21.
 */
public class HeartBeatCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        C_DEVICE_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_DEVICE_HEART_BEAT.class,
                userSession.getCtx());
        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 2, 30, TimeUnit.SECONDS);
    }
}
