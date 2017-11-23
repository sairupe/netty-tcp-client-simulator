package app.client.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Inflater;

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
}
