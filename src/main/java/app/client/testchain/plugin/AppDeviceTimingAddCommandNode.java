package app.client.testchain.plugin;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.plugin.C_DEVICE_TIMING_ADD;
import app.client.net.protocol.request.smarthome.C_QUERY_ROBOT_COLLA;
import app.client.testchain.ProtocolListenNode;

/**
 * Created by zh on 2017/11/21.
 */
public class AppDeviceTimingAddCommandNode extends ProtocolListenNode {

    private String pluginMac;

    private long executeTime;

    private boolean isOpen;

    public AppDeviceTimingAddCommandNode(String pluginMac, long executeTime, boolean isOpen){
        this.pluginMac = pluginMac;
        this.executeTime = executeTime;
        this.isOpen = isOpen;
    }

    @Override
    public void doExecute() {
        C_DEVICE_TIMING_ADD cDeviceTimingAdd = ProtocolFactory.createRequestProtocol(C_DEVICE_TIMING_ADD.class, userSession.getCtx());
        cDeviceTimingAdd.setPluginMac(pluginMac);
        cDeviceTimingAdd.setExecuteTime(executeTime);
        cDeviceTimingAdd.setOpen(isOpen);
        userSession.sendMsg(cDeviceTimingAdd);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
