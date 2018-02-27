package app.client.service.device;

import app.client.net.annotation.Handler;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.device.C_DEVICE_SIMULAR_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DELETE_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_DEVICE_ATTR_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_MODE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_STATE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_ADD_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_SYNC_DEVICE_RESULT;
import app.client.service.AbstractServiceImpl;
import app.client.user.session.UserSession;
import com.google.protobuf.ProtocolStringList;
import com.gowild.protocol.SdkMsgType;
import com.gowild.protocol.SdkTcp2DeviceProtocol;
import com.gowild.sdktcp.metadata.pb.SdkBothMsgProto;
import com.gowild.sdktcp.metadata.pb.SdkDownloadMsgProto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zh on 2017/11/8.
 */
@Receiver
public class DeviceServiceImpl extends AbstractServiceImpl implements IDeviceService{

    @Override
    public void sendSimularCmd(UserSession userSession) {
        // 发送模拟指令
        JSONObject object = new JSONObject();
        String uid = "";
        String text = "这是一个模拟测试指令";
        String expression = "";
        String mood = "";

        JSONObject funcObj = new JSONObject();
        JSONArray details = new JSONArray();
        JSONObject detailObj = new JSONObject();

        String state = "OFF";
        String action = "";
        String op_type = "STATE";
        String value = "";
        int execTime = 0;

        object.put("uid", uid);
        object.put("text", text);
        object.put("expression", expression);
        object.put("mood", mood);
        object.put("func", funcObj);

        funcObj.put("type", op_type);
        funcObj.put("details", details);

        detailObj.put("code", "device");
        detailObj.put("name", "雪梨");
        detailObj.put("state", state);
        detailObj.put("action", action);
        detailObj.put("type", op_type);
        detailObj.put("value", value);
        detailObj.put("execTime", execTime);

        details.put(detailObj);

        C_DEVICE_SIMULAR_COMMAND command = ProtocolFactory.createRequestProtocol(C_DEVICE_SIMULAR_COMMAND.class, userSession.getCtx());
        command.setSemanticCommand(object.toString());
        userSession.sendMsg(command);
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_PUSH_STATE_COMMAND_S)
    public void receiveStateCommand(S_DEVICE_STATE_COMMAND response) {
        System.out.println("=============>> 收到状态控制指令");
        SdkDownloadMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList =  pushCommonCommandMsg.getCmdWrappersList();
        for(SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList){
            SdkBothMsgProto.SdkCommandDeviceMsg typeDevice = sdkCommandWrapperMsg.getTypeDevice();
            StringBuilder sb = new StringBuilder();
            int deviceType = typeDevice.getDeviceType();
            sb.append("deviceType:" + deviceType + "|");
            ProtocolStringList ids = typeDevice.getDeviceIdListList();
            sb.append("deviceIds:" + deviceType + "|");
            for(String id : ids){
                sb.append(id + "|");
            }
            SdkBothMsgProto.SdkCommandParamMsg cmdParams = sdkCommandWrapperMsg.getCmdParams();
            int attrValueType = cmdParams.getAttrValueType();
            String attrValue = cmdParams.getAttrValue();
            sb.append("attrValueType:" + attrValueType + " | attrValue:" + attrValue);
            SdkBothMsgProto.SdkBaseCommandMsg baseCommand = sdkCommandWrapperMsg.getBaseCommand();
            int mainType = baseCommand.getMainTypeId();
            int subType = baseCommand.getSubTypeId();

            String action = baseCommand.getAction();
            String attr = baseCommand.getAttr();
            String attrValueTypeStr = baseCommand.getAttrValueType();
            attrValue = baseCommand.getAttrValue();
            String mode = baseCommand.getMode();

            long execTime = baseCommand.getExecTime();
            sb.append("mainType :" + mainType + " | subType:" + subType + "|");
            sb.append("action :" + action + " | attr:" + attr + "| attrValueTypeStr:" + attrValueTypeStr
                    + " | attrValue:" + attrValue + " | mode:" + mode);
            System.out.println(sb.toString());
            System.out.println("====================华丽的分割线======================");
        }
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_PUSH_ATTR_COMMAND_S)
    public void receiveAttrCommand(S_DEVICE_ATTR_COMMAND response) {
        System.out.println("=============>> 收到属性制指令");
        SdkDownloadMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList =  pushCommonCommandMsg.getCmdWrappersList();
        for(SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList){
            SdkBothMsgProto.SdkCommandDeviceMsg typeDevice = sdkCommandWrapperMsg.getTypeDevice();
            StringBuilder sb = new StringBuilder();
            int deviceType = typeDevice.getDeviceType();
            sb.append("deviceType:" + deviceType + "|");
            ProtocolStringList ids = typeDevice.getDeviceIdListList();
            sb.append("deviceIds:" + deviceType + "|");
            for(String id : ids){
                sb.append(id + "|");
            }
            SdkBothMsgProto.SdkCommandParamMsg cmdParams = sdkCommandWrapperMsg.getCmdParams();
            int attrValueType = cmdParams.getAttrValueType();
            String attrValue = cmdParams.getAttrValue();
            sb.append("attrValueType:" + attrValueType + " | attrValue:" + attrValue);
            SdkBothMsgProto.SdkBaseCommandMsg baseCommand = sdkCommandWrapperMsg.getBaseCommand();
            int mainType = baseCommand.getMainTypeId();
            int subType = baseCommand.getSubTypeId();

            String action = baseCommand.getAction();
            String attr = baseCommand.getAttr();
            String attrValueTypeStr = baseCommand.getAttrValueType();
            attrValue = baseCommand.getAttrValue();
            String mode = baseCommand.getMode();

            long execTime = baseCommand.getExecTime();
            sb.append("mainType :" + mainType + " | subType:" + subType + "|");
            sb.append("action :" + action + " | attr:" + attr + "| attrValueTypeStr:" + attrValueTypeStr
                    + " | attrValue:" + attrValue + " | mode:" + mode);
            System.out.println(sb.toString());
            System.out.println("====================华丽的分割线======================");
        }
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_PUSH_MODE_COMMAND_S)
    public void receiveModeCommand(S_DEVICE_MODE_COMMAND response) {
        System.out.println("=============>> 收到模式控制指令");
        SdkDownloadMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList =  pushCommonCommandMsg.getCmdWrappersList();
        for(SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList){
            SdkBothMsgProto.SdkCommandDeviceMsg typeDevice = sdkCommandWrapperMsg.getTypeDevice();
            StringBuilder sb = new StringBuilder();
            int deviceType = typeDevice.getDeviceType();
            sb.append("deviceType:" + deviceType + "|");
            ProtocolStringList ids = typeDevice.getDeviceIdListList();
            sb.append("deviceIds:" + deviceType + "|");
            for(String id : ids){
                sb.append(id + "|");
            }
            SdkBothMsgProto.SdkCommandParamMsg cmdParams = sdkCommandWrapperMsg.getCmdParams();
            int attrValueType = cmdParams.getAttrValueType();
            String attrValue = cmdParams.getAttrValue();
            sb.append("attrValueType:" + attrValueType + " | attrValue:" + attrValue);
            SdkBothMsgProto.SdkBaseCommandMsg baseCommand = sdkCommandWrapperMsg.getBaseCommand();
            int mainType = baseCommand.getMainTypeId();
            int subType = baseCommand.getSubTypeId();

            String action = baseCommand.getAction();
            String attr = baseCommand.getAttr();
            String attrValueTypeStr = baseCommand.getAttrValueType();
            attrValue = baseCommand.getAttrValue();
            String mode = baseCommand.getMode();

            long execTime = baseCommand.getExecTime();
            sb.append("mainType :" + mainType + " | subType:" + subType + "|");
            sb.append("action :" + action + " | attr:" + attr + "| attrValueTypeStr:" + attrValueTypeStr
                    + " | attrValue:" + attrValue + " | mode:" + mode);
            System.out.println(sb.toString());
            System.out.println("====================华丽的分割线======================");
        }

    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_MASTER_BIND_DEVICES_RESULT_S)
    public void receiveAddMasterBindDeviceResult(S_ADD_DEVICE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【增加】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_DELETE_MASTER_BIND_DEVICE_RESULT_S)
    public void receiveDeleteMasterBindDeviceResult(S_DELETE_DEVICE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_SYNC_DEVICE_RESULT_S)
    public void receiveSyncMasterBindDeviceResult(S_SYNC_DEVICE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

//    @Override
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_RECEIVED_DEVICE_COMMAND_S)
//    public void receiveDeviceCmd(S_DEVICE_CMD response) {
//        String state = response.getSdkDeviceCommandMsg().getCommand().getState();
//        String action = response.getSdkDeviceCommandMsg().getCommand().getAction();
//        String type = response.getSdkDeviceCommandMsg().getCommand().getType();
//        String value = response.getSdkDeviceCommandMsg().getCommand().getValue();
//        LogUtil.debug("============>>>>>>>>>>>收到 S_DEVICE_CMD 协议，state :{} | action : {} | type : {} | " +
//                "value : {}", state, action, type, value);
//    }
//
//    @Override
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_RECEIVED_DEVICE_TYPE_COMMAND_S)
//    public void receiveDeviceTypeCmd(S_DEVICE_TYPE_CMD response) {
//        String state = response.getSdkDeviceTypeCommandMsg().getCommand().getState();
//        String action = response.getSdkDeviceTypeCommandMsg().getCommand().getAction();
//        String type = response.getSdkDeviceTypeCommandMsg().getCommand().getType();
//        String value = response.getSdkDeviceTypeCommandMsg().getCommand().getValue();
//        LogUtil.debug("============>>>>>>>>>>>收到 S_DEVICE_TYPE_CMD 协议，state :{} | action : {} | type : {} | " +
//                "value : {}", state, action, type, value);
//    }
//
//    @Override
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_RECEIVED_SCENE_COMMAND_S)
//    public void receiveSceneCmd(S_SCENE_CMD response) {
//        String state = response.getSdkSceneCommandMsg().getCommand().getState();
//        String action = response.getSdkSceneCommandMsg().getCommand().getAction();
//        String type = response.getSdkSceneCommandMsg().getCommand().getType();
//        String value = response.getSdkSceneCommandMsg().getCommand().getValue();
//        LogUtil.debug("============>>>>>>>>>>>收到 S_SCENE_CMD 协议，state :{} | action : {} | type : {} | " +
//                "value : {}", state, action, type, value);
//    }
//
//    @Override
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_RECEIVED_AREA_COMMAND_S)
//    public void receiveAreaCmd(S_AREA_CMD response) {
//        String state = response.getSdkAreaCommandMsg().getCommand().getState();
//        String action = response.getSdkAreaCommandMsg().getCommand().getAction();
//        String type = response.getSdkAreaCommandMsg().getCommand().getType();
//        String value = response.getSdkAreaCommandMsg().getCommand().getValue();
//        LogUtil.debug("============>>>>>>>>>>>收到 S_AREA_CMD 协议，state :{} | action : {} | type : {} | " +
//                "value : {}", state, action, type, value);
//    }
}
