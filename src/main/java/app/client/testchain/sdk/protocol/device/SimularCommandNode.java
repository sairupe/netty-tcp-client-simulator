package app.client.testchain.sdk.protocol.device;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.device.C_DEVICE_SIMULAR_COMMAND;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import com.gowild.sdk.basic.enums.DeviceTypeEnum;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by zh on 2017/11/21.
 */
public class SimularCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        if(true)
            return;

//        {
//            "bindMasterId":1,
//                "code":"deviceWithName",
//                "params":{
//            "deviceName":"苹果！"
//        },
//            "deviceGroupByType":[
//            {
//                "deviceType":1,
//                    "deviceInfo":[
//                {
//                    "deviceId":123,
//                        "deviceName":"设备名字"
//                }
//                  ],
//                "cmdConstruct":{
//                "a":"a"
//            }
//            }
//             ]
//        }

        String testStr = "{\"bindMasterId\":1,\"code\":\"deviceWithName\",\"params\":{\"deviceName\":\"苹果！\"},\"deviceGroupByType\":[{\"deviceType\":1,\"deviceInfo\":[{\"deviceId\":123,\"deviceName\":\"设备名字\"}],\"cmdConstruct\":{\"a\":\"a\"}}]}";


        // 发送模拟指令
        JSONObject cmdJson = new JSONObject(testStr);
        int bindMasterId = SdkTestConst.BIND_MASTER_ID;
        int firstDeviceId = SdkTestConst.FIRST_DEVICE_ID;
        int secondDeviceId = SdkTestConst.SECOND_DEVICE_ID;

        String firstDeviceName = SdkTestConst.FIRST_DEVICE_NAME;
        String secondDeviceName = SdkTestConst.SECOND_DEVICE_NAME;

        String operationType = "MODE";
        String mainType = "ATTRIBUTE_BRIGHTNESS";
        String subType = "ATTR_MAX";

        String act = "ACTION_TO";
        String mode = "";
        String attr = "ATTRIBUTE_BRIGHTNESS";
        String attrValueType = "SPECIEL";
        String attrValue = "VALUE_MAX";
        int execTime = 0;



        cmdJson.put("bindMasterId", bindMasterId);
        cmdJson.put("code","deviceWithName");
        JSONObject params = new JSONObject();
        params.put("deviceName", "苹果");
        cmdJson.put("params", params);

        JSONArray deviceGroupByType = new JSONArray();
        JSONObject deviceNode = new JSONObject();
        deviceNode.put("deviceType", DeviceTypeEnum.LIGHT.getDeviceType());
        JSONArray deviceInfo = new JSONArray();
        JSONObject deviceFirst = new JSONObject();
        deviceFirst.put("deviceId", firstDeviceId);
        deviceFirst.put("deviceName", firstDeviceName);
        JSONObject deviceSecond = new JSONObject();
        deviceSecond.put("deviceId", secondDeviceId);
        deviceSecond.put("deviceName", secondDeviceName);
        deviceInfo.put(deviceFirst);
        deviceInfo.put(deviceSecond);
        deviceNode.put("deviceInfo", deviceInfo);

        JSONObject cmdConstruct = new JSONObject();
        cmdConstruct.put("operationType", operationType);
        cmdConstruct.put("mainType", mainType);
        cmdConstruct.put("subType", subType);

        cmdConstruct.put("act", act);
        cmdConstruct.put("mode", mode);
        cmdConstruct.put("attr", attr);
        cmdConstruct.put("attrValueType", attrValueType);
        cmdConstruct.put("attrValue", attrValue);
        cmdConstruct.put("execTime", execTime);
        deviceNode.put("cmdConstruct", cmdConstruct);

        // 整体元素
        deviceGroupByType.put(deviceNode);
        cmdJson.put("deviceGroupByType", deviceGroupByType);

        C_DEVICE_SIMULAR_COMMAND command = ProtocolFactory.createRequestProtocol(C_DEVICE_SIMULAR_COMMAND.class, userSession.getCtx());
        String str = cmdJson.toString();
        command.setSemanticCommand(str);
        userSession.sendMsg(command);
    }

    @Override
    public boolean canExecuteImmediately() {
        return true;
    }
}
