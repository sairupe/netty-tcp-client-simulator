package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkDeviceBothMsg;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_DEVICE_LOGIN_C, type = ProtocolType.REQUSET)
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
     * 设备MAC
     */
    private String deviceMac;
    /**
     * 品牌信息
     */
    private String brand;
    /**
     * 登录时间
     */
    private long loginTime;
    /**
     * 加密码
     */
    private String encrypCode;


    @Override
    public void writeBinaryData(){
        SdkDeviceBothMsg.SdkDeviceLoginMsg.Builder build = SdkDeviceBothMsg.SdkDeviceLoginMsg.newBuilder();
        build.setUniqueCode(uniqueCode);
        build.setDeviceType(deviceType);
        build.setDeviceMac(deviceMac);
        build.setBrand(brand);
        build.setLoginTime(loginTime);
        build.setEncrypCode(encrypCode);
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
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

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getEncrypCode() {
        return encrypCode;
    }

    public void setEncrypCode(String encrypCode) {
        this.encrypCode = encrypCode;
    }
}
