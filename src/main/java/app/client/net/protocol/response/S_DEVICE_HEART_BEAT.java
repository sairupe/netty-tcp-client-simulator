//package app.client.net.protocol.response;
//
//import app.client.net.annotation.Protocol;
//import app.client.net.protocol.ProtocolType;
//import app.client.net.protocol.ResponseProtocol;
//import com.google.protobuf.InvalidProtocolBufferException;
//import com.gowild.sdk.protocol.SdkMsgType;
//import com.gowild.sdk.protocol.Tcp2DeviceProtocol;
//import com.gowild.sdk.metadata.pb.Tcp2SdkMsgProto;
//
//@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_DEVICE_HEART_BEAT_S, type = ProtocolType.RESPONSE)
//public class S_DEVICE_HEART_BEAT extends ResponseProtocol{
//
//    Tcp2SdkMsgProto.SdkDeviceHeartBeatMsg heartBeatMsg;
//
//    @Override
//    public boolean readBinaryData(){
//        try {
//            heartBeatMsg = Tcp2SdkMsgProto.SdkDeviceHeartBeatMsg.parseFrom(buffer.array());
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    public Tcp2SdkMsgProto.SdkDeviceHeartBeatMsg getHeartBeatMsg() {
//        return heartBeatMsg;
//    }
//}
