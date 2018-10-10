package app.client.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Inflater;

import com.gowild.sdk.protocol.SdkMsgType;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.util.internal.jzlib.JZlib;
import org.jboss.netty.util.internal.jzlib.ZStream;

public class ClientUtil{

    public static ChannelBuffer getDynamicBuffer(){
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
        return buf;
    }

    public static int buildProtocolKey(int moduleId, int sequenceId){
        return moduleId << 16 | sequenceId;
    }

    public static long buildRobotClientSessionKey(int robotId){
        return robotId << 2 | SdkMsgType.XB_CLIENT_TYPE;
    }

    public static long buildAppClientSessionKey(int accountId){
        return accountId << 2 | SdkMsgType.APP_CLIENT_TYPE;
    }
}
