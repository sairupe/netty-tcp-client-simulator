package app.client.testchain.plugin;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.plugin.C_DEVICE_TIMING_ADD;
import app.client.net.protocol.request.plugin.C_DEVICE_TIMING_EXECUTED;
import app.client.testchain.ProtocolListenNode;

/**
 * Created by zh on 2017/11/21.
 */
public class RobotNotifyPluginTimeExecuteCommandNode extends ProtocolListenNode {

    private int timingId;

    private String xbMac;

    public RobotNotifyPluginTimeExecuteCommandNode(int timingId, String xbMac){
        this.timingId = timingId;
        this.xbMac = xbMac;
    }

    @Override
    public void doExecute() {
        C_DEVICE_TIMING_EXECUTED cDeviceTimingExecuted = ProtocolFactory.createRequestProtocol(C_DEVICE_TIMING_EXECUTED.class, userSession.getCtx());
        cDeviceTimingExecuted.setTimingId(timingId);
        cDeviceTimingExecuted.setXbMac(xbMac);
        userSession.sendMsg(cDeviceTimingExecuted);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
