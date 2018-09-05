package app.client.net.protocol.request.sdk.batch.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.vo.AddDeviceInfoVo;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_DEVICE_BATCH_C, type = ProtocolType.REQUSET)
public class C_UPDATE_DEVICE_BATCH extends RequestProtocol{

    private List<AddDeviceInfoVo> updateDeviceInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkUpdateDeviceBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateDeviceBatchMsg.newBuilder();
        for(AddDeviceInfoVo addDeviceInfoVo : updateDeviceInfoVoList){
            Sdk2TcpMsgProto.SdkUpdateDevice.Builder second = Sdk2TcpMsgProto.SdkUpdateDevice.newBuilder();
            second.setDeviceId(addDeviceInfoVo.getDeviceId());
            second.setDeviceName(addDeviceInfoVo.getDeviceName());
            second.setOpenStatus(addDeviceInfoVo.getOpenStatus());
            second.setOnlineStatus(addDeviceInfoVo.getOnlineStatus());
            second.setDeviceType(addDeviceInfoVo.getDeviceType());
            build.addDeviceList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddDeviceInfoVo> getUpdateDeviceInfoVoList() {
        return updateDeviceInfoVoList;
    }

    public void setUpdateDeviceInfoVoList(List<AddDeviceInfoVo> updateDeviceInfoVoList) {
        this.updateDeviceInfoVoList = updateDeviceInfoVoList;
    }
}
