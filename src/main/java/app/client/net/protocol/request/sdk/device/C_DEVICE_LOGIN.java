package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_DEVICE_LOGIN_C, type = ProtocolType.REQUSET)
public class C_DEVICE_LOGIN extends RequestProtocol{

    /**
     * 账号名
     */
    private String uniqueCode;
    /**
     * 设备类型
     */
    private int deviceType;
    /**
     * 设备第三方ID
     */
    private String deviceId;
    /**
     * 登录时间
     */
    private long loginTime;
    /**
     * 设备唯一识别码
     */
    private String deviceSn;
    /**
     * 加密码
     */
    private String encrypCode;


    @Override
    public void writeBinaryData(){
//        Sdk2TcpMsgProto.SdkDeviceLoginMsg.Builder build = Sdk2TcpMsgProto.SdkDeviceLoginMsg.newBuilder();
//        build.setUniqueCode(uniqueCode);
//        build.setDeviceType(deviceType);
//        build.setDeviceId(deviceId);
//        build.setLoginTime(loginTime);
//        build.setDeviceSn(deviceSn);
//        build.setEncryptCode(encrypCode);
//        byte[] bytes = build.build().toByteArray();
//        for(byte b : bytes){
//            writeByte(b);
//        }
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getEncrypCode() {
        return encrypCode;
    }

    public void setEncrypCode(String encrypCode) {
        this.encrypCode = encrypCode;
    }
}
