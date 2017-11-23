//package app.client.net.protocol.response;
//
//import app.client.net.annotation.Protocol;
//import app.client.net.protocol.ProtocolType;
//import app.client.net.protocol.ResponseProtocol;
//import com.google.protobuf.InvalidProtocolBufferException;
//import com.gowild.protocol.SdkMsgType;
//import com.gowild.protocol.SdkTcp2DeviceProtocol;
//import com.gowild.sdktcp.metadata.pb.SdkDeviceBothMsg;
//
//@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_RECEIVED_DEVICE_COMMAND_S, type = ProtocolType.RESPONSE)
//public class S_DEVICE_CMD extends ResponseProtocol{
//
//    SdkDeviceBothMsg.SdkDeviceCommandMsg sdkDeviceCommandMsg;
//
//    @Override
//    public boolean readBinaryData(){
//        try {
//            sdkDeviceCommandMsg = SdkDeviceBothMsg.SdkDeviceCommandMsg.parseFrom(buffer.array());
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    public SdkDeviceBothMsg.SdkDeviceCommandMsg getSdkDeviceCommandMsg() {
//        return sdkDeviceCommandMsg;
//    }
//}
