package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.MachineBothMsgProto;
import com.gowild.xbtcp.metadata.pb.MiscXC2SMsgProto;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 101, type = ProtocolType.REQUSET)
public class C_XB_LOGIN extends RequestProtocol{
//    message LoginForXBCMsg {
//        required string mac = 1; //mac地址
//        required string sn = 2; //sn地址
//        optional MachineInfoMsg machineInfo = 3; //机器信息
//        optional string city = 4; //城市
//    }
    private String mac;
    private String sn;

    private String version;
    private String versionV2;

    private String bVersion;
    private String gifV1;
    private String loginIp;



    @Override
    public void writeBinaryData(){
        MiscXC2SMsgProto.LoginForXBCMsg.Builder loginForXBCMsg = MiscXC2SMsgProto.LoginForXBCMsg.newBuilder();
        loginForXBCMsg.setMac(mac);
        loginForXBCMsg.setSn(sn);
        loginForXBCMsg.setIp(loginIp);

        MachineBothMsgProto.MachineInfoMsg.Builder builder = MachineBothMsgProto.MachineInfoMsg.newBuilder();
        builder.setVersionV2(versionV2);
        builder.setVersion(version);

        builder.setBVersion(bVersion);
        builder.setGifV1(gifV1);
        builder.setRobotPower(55);


        loginForXBCMsg.setMachineInfo(builder.build());
        byte[] array = loginForXBCMsg.build().toByteArray();
        for(byte b : array){
            writeByte(b);
        }
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setVersionV2(String versionV2) {
        this.versionV2 = versionV2;
    }

    public void setbVersion(String bVersion) {
        this.bVersion = bVersion;
    }

    public void setGifV1(String gifV1) {
        this.gifV1 = gifV1;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
