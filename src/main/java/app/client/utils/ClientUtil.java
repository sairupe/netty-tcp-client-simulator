package app.client.utils;

import app.client.common.CommonConsts;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class ClientUtil{

    public static ChannelBuffer getDynamicBuffer(){
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
        return buf;
    }

    public static int buildProtocolKey(int moduleId, int sequenceId){
        return moduleId << 16 | sequenceId;
    }

    public static long buildRobotClientSessionKey(int robotId){
        return robotId << 2 | CommonConsts.CLIENT_TYPE_PC;
    }

    public static long buildAppClientSessionKey(int accountId){
        return accountId << 2 | CommonConsts.CLIENT_TYPE_APP;
    }
}
