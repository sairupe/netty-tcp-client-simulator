package app.client.service.device;

import app.client.net.annotation.Handler;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.device.C_DEVICE_SIMULAR_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DELETE_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_DEVICE_ATTR_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_MODE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_STATE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_ADD_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_SCENE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_SYNC_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_UPDATE_DEVICE_BIND_AREA;
import app.client.net.protocol.response.sdk.device.S_UPDATE_DEVICE_BIND_SCENE;
import app.client.net.protocol.response.sdk.device.S_UPDATE_DEVICE_RESULT;
import app.client.service.AbstractServiceImpl;
import app.client.testchain.sdk.SdkTestConst;
import app.client.user.session.UserSession;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.Tcp2DeviceProtocol;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import com.gowild.sdk.metadata.pb.Tcp2SdkMsgProto;
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
    //@Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_PUSH_STATE_COMMAND_S)
    @Handler(moduleId = 1, sequenceId = 2752)
    public void receiveStateCommand(S_DEVICE_STATE_COMMAND response) {
        System.out.println("=============>> 收到状态控制指令");
        Tcp2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList =  pushCommonCommandMsg.getCmdWrappersList();
        for(SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList){

            SdkBothMsgProto.SdkNewCommandStructMsg new_control = sdkCommandWrapperMsg.getNewControl();

            SdkBothMsgProto.SdkNewControlDeviceListMsg type_device = new_control.getTypeDevice();
            List<SdkBothMsgProto.SdkNewControlDeviceInfoMsg> device_list = type_device.getDeviceListList();
            StringBuilder sb = new StringBuilder();
            int deviceType = type_device.getDeviceType();
            sb.append("=========================NEW CONTROL ==============================" + "\n");
            sb.append("deviceType:  " + deviceType + "\n");
            sb.append("deviceInfos:  " + "\n");
            for(SdkBothMsgProto.SdkNewControlDeviceInfoMsg device : device_list){
                sb.append(device.getDeviceId() + "|" + device.getDeviceName() + "\n");
            }

            // command
            SdkBothMsgProto.SdkNewControlMsg new_base_command = new_control.getBaseCommand();
            int main_type = new_base_command.getMainTypeId();
            int sub_type = new_base_command.getSubTypeId();

            String new_action = new_base_command.getAction();
            String new_attr = new_base_command.getAttr();
            String new_attr_value_type = new_base_command.getAttrValueType();
            String new_attr_value = new_base_command.getAttrValue();
            String new_mode = new_base_command.getMode();

            sb.append("\n");
            sb.append("main_type : " + main_type + " | sub_type: " + sub_type + "\n");
            sb.append("new_action :" + new_action + " | new_attr:" + new_attr + "| "
                    + " | new_attr_value_type:" + new_attr_value_type + " | new_attr_value:" + new_attr_value + " | new_mode:" + new_mode + "\n");

            // param
            SdkBothMsgProto.SdkNewControlParamMsg cmd_params = new_control.getCmdParams();
            int attr_value_type = cmd_params.getAttrValueType();
            String attr_value = cmd_params.getAttrValue();
            sb.append("attr_value_type : " + attr_value_type
                    + "(" + SdkTestConst.getAttrValueTypeStringById(attr_value_type) + ")"
                    + "| attr_value: " + attr_value + "\n");


            sb.append("=========================OLD CONTROL ==============================" + "\n");
            SdkBothMsgProto.SdkOldCommandStructMsg old_control = sdkCommandWrapperMsg.getOldControl();
            SdkBothMsgProto.SdkOldControlDeviceListMsg type_device_old = old_control.getTypeDevice();

            int old_deviceType = type_device_old.getDeviceType();
            List<SdkBothMsgProto.SdkOldControlDeviceInfoMsg> old_device_list = type_device_old.getDeviceListList();
            sb.append("deviceType:  " + old_deviceType + "|" + "\n");
            sb.append("deviceInfos:  " + "\n");
            for(SdkBothMsgProto.SdkOldControlDeviceInfoMsg device : old_device_list){
                sb.append(device.getDeviceId() + "|" + device.getDeviceName() + "\n");
            }

            SdkBothMsgProto.SdkOldControlMsg old_base_command = old_control.getBaseCommand();
            String old_action_code = old_base_command.getActionCode();
            String old_attribute_code = old_base_command.getAttributeCode();
            String old_attribute_value_code = old_base_command.getAttributeValueCode();
            String old_mode_code = old_base_command.getModeCode();
            String old_state_code = old_base_command.getStateCode();

            sb.append("old_action :" + old_action_code + " | old_attr:" + old_attribute_code + "| "
                    + " | old_attrValue:" + old_attribute_value_code + " | old_mode:" + old_mode_code + " | old_state:" + old_state_code + "\n");

            System.out.print(sb.toString());
            System.out.println("====================华丽的分割线======================" + "\n");
        }
    }

    @Override
    //@Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_PUSH_ATTR_COMMAND_S)
    @Handler(moduleId = 1, sequenceId = 2756)
    public void receiveAttrCommand(S_DEVICE_ATTR_COMMAND response) {
        System.out.println("=============>> 收到属性制指令");
        Tcp2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList =  pushCommonCommandMsg.getCmdWrappersList();
        for(SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList){

            SdkBothMsgProto.SdkNewCommandStructMsg new_control = sdkCommandWrapperMsg.getNewControl();

            SdkBothMsgProto.SdkNewControlDeviceListMsg type_device = new_control.getTypeDevice();
            List<SdkBothMsgProto.SdkNewControlDeviceInfoMsg> device_list = type_device.getDeviceListList();
            StringBuilder sb = new StringBuilder();
            int deviceType = type_device.getDeviceType();
            sb.append("=========================NEW CONTROL ==============================" + "\n");
            sb.append("deviceType:  " + deviceType + "\n");
            sb.append("deviceInfos:  " + "\n");
            for(SdkBothMsgProto.SdkNewControlDeviceInfoMsg device : device_list){
                sb.append(device.getDeviceId() + "|" + device.getDeviceName() + "\n");
            }

            // command
            SdkBothMsgProto.SdkNewControlMsg new_base_command = new_control.getBaseCommand();
            int main_type = new_base_command.getMainTypeId();
            int sub_type = new_base_command.getSubTypeId();

            String new_action = new_base_command.getAction();
            String new_attr = new_base_command.getAttr();
            String new_attr_value_type = new_base_command.getAttrValueType();
            String new_attr_value = new_base_command.getAttrValue();
            String new_mode = new_base_command.getMode();

            sb.append("\n");
            sb.append("main_type : " + main_type + " | sub_type: " + sub_type + "\n");
            sb.append("new_action :" + new_action + " | new_attr:" + new_attr + "| "
                    + " | new_attr_value_type:" + new_attr_value_type + " | new_attr_value:" + new_attr_value + " | new_mode:" + new_mode + "\n");

            // param
            SdkBothMsgProto.SdkNewControlParamMsg cmd_params = new_control.getCmdParams();
            int attr_value_type = cmd_params.getAttrValueType();
            String attr_value = cmd_params.getAttrValue();
            sb.append("attr_value_type : " + attr_value_type
                    + "(" + SdkTestConst.getAttrValueTypeStringById(attr_value_type) + ")"
                    + "| attr_value: " + attr_value + "\n");


            sb.append("=========================OLD CONTROL ==============================" + "\n");
            SdkBothMsgProto.SdkOldCommandStructMsg old_control = sdkCommandWrapperMsg.getOldControl();
            SdkBothMsgProto.SdkOldControlDeviceListMsg type_device_old = old_control.getTypeDevice();

            int old_deviceType = type_device_old.getDeviceType();
            List<SdkBothMsgProto.SdkOldControlDeviceInfoMsg> old_device_list = type_device_old.getDeviceListList();
            sb.append("deviceType:  " + old_deviceType + "|" + "\n");
            sb.append("deviceInfos:  " + "\n");
            for(SdkBothMsgProto.SdkOldControlDeviceInfoMsg device : old_device_list){
                sb.append(device.getDeviceId() + "|" + device.getDeviceName() + "\n");
            }

            SdkBothMsgProto.SdkOldControlMsg old_base_command = old_control.getBaseCommand();
            String old_action_code = old_base_command.getActionCode();
            String old_attribute_code = old_base_command.getAttributeCode();
            String old_attribute_value_code = old_base_command.getAttributeValueCode();
            String old_mode_code = old_base_command.getModeCode();
            String old_state_code = old_base_command.getStateCode();

            sb.append("old_action :" + old_action_code + " | old_attr:" + old_attribute_code + "| "
                    + " | old_attrValue:" + old_attribute_value_code + " | old_mode:" + old_mode_code + " | old_state:" + old_state_code + "\n");

            System.out.print(sb.toString());
            System.out.println("====================华丽的分割线======================" + "\n");
        }
    }

    @Override
    public void receiveModeCommand(S_DEVICE_MODE_COMMAND response) {
        System.out.println("=============>> 收到模式控制指令");
        Tcp2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList =  pushCommonCommandMsg.getCmdWrappersList();
        for(SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList){

            SdkBothMsgProto.SdkNewCommandStructMsg new_control = sdkCommandWrapperMsg.getNewControl();

            SdkBothMsgProto.SdkNewControlDeviceListMsg type_device = new_control.getTypeDevice();
            List<SdkBothMsgProto.SdkNewControlDeviceInfoMsg> device_list = type_device.getDeviceListList();
            StringBuilder sb = new StringBuilder();
            int deviceType = type_device.getDeviceType();
            sb.append("=========================NEW CONTROL ==============================" + "\n");
            sb.append("deviceType:  " + deviceType + "\n");
            sb.append("deviceInfos:  " + "\n");
            for(SdkBothMsgProto.SdkNewControlDeviceInfoMsg device : device_list){
                sb.append(device.getDeviceId() + "|" + device.getDeviceName() + "\n");
            }

            // command
            SdkBothMsgProto.SdkNewControlMsg new_base_command = new_control.getBaseCommand();
            int main_type = new_base_command.getMainTypeId();
            int sub_type = new_base_command.getSubTypeId();

            String new_action = new_base_command.getAction();
            String new_attr = new_base_command.getAttr();
            String new_attr_value_type = new_base_command.getAttrValueType();
            String new_attr_value = new_base_command.getAttrValue();
            String new_mode = new_base_command.getMode();

            sb.append("\n");
            sb.append("main_type : " + main_type + " | sub_type: " + sub_type + "\n");
            sb.append("new_action :" + new_action + " | new_attr:" + new_attr + "| "
                    + " | new_attr_value_type:" + new_attr_value_type + " | new_attr_value:" + new_attr_value + " | new_mode:" + new_mode + "\n");

            // param
            SdkBothMsgProto.SdkNewControlParamMsg cmd_params = new_control.getCmdParams();
            int attr_value_type = cmd_params.getAttrValueType();
            String attr_value = cmd_params.getAttrValue();
            sb.append("attr_value_type : " + attr_value_type
                    + "(" + SdkTestConst.getAttrValueTypeStringById(attr_value_type) + ")"
                    + "| attr_value: " + attr_value + "\n");


            sb.append("=========================OLD CONTROL ==============================" + "\n");
            SdkBothMsgProto.SdkOldCommandStructMsg old_control = sdkCommandWrapperMsg.getOldControl();
            SdkBothMsgProto.SdkOldControlDeviceListMsg type_device_old = old_control.getTypeDevice();

            int old_deviceType = type_device_old.getDeviceType();
            List<SdkBothMsgProto.SdkOldControlDeviceInfoMsg> old_device_list = type_device_old.getDeviceListList();
            sb.append("deviceType:  " + old_deviceType + "|" + "\n");
            sb.append("deviceInfos:  " + "\n");
            for(SdkBothMsgProto.SdkOldControlDeviceInfoMsg device : old_device_list){
                sb.append(device.getDeviceId() + "|" + device.getDeviceName() + "\n");
            }

            SdkBothMsgProto.SdkOldControlMsg old_base_command = old_control.getBaseCommand();
            String old_action_code = old_base_command.getActionCode();
            String old_attribute_code = old_base_command.getAttributeCode();
            String old_attribute_value_code = old_base_command.getAttributeValueCode();
            String old_mode_code = old_base_command.getModeCode();
            String old_state_code = old_base_command.getStateCode();

            sb.append("old_action :" + old_action_code + " | old_attr:" + old_attribute_code + "| "
                    + " | old_attrValue:" + old_attribute_value_code + " | old_mode:" + old_mode_code + " | old_state:" + old_state_code + "\n");

            System.out.print(sb.toString());
            System.out.println("====================华丽的分割线======================" + "\n");
        }

    }

    @Override
    public void receiveSceneCommand(S_SCENE_COMMAND response) {
        System.out.println("=============>> 收到场景控制指令");
        StringBuilder sb = new StringBuilder();

        Tcp2SdkMsgProto.SdkSceneCommandMsg sdkSceneCommandMsg = response.getSdkSceneCommandMsg();

        // scenelist
        List<Tcp2SdkMsgProto.SceneTidWithName> sceneListList = sdkSceneCommandMsg.getSceneListList();
        for(Tcp2SdkMsgProto.SceneTidWithName scene : sceneListList){
            sb.append("sceneTid:" + scene.getSceneTid() + " | sceneName" + scene.getSceneName() + "\n");
        }

        // command
        SdkBothMsgProto.SdkNewControlMsg new_base_command = sdkSceneCommandMsg.getBaseCommand();
        int main_type = new_base_command.getMainTypeId();
        int sub_type = new_base_command.getSubTypeId();

        String new_action = new_base_command.getAction();
        String new_attr = new_base_command.getAttr();
        String new_attr_value_type = new_base_command.getAttrValueType();
        String new_attr_value = new_base_command.getAttrValue();
        String new_mode = new_base_command.getMode();

        sb.append("\n");
        sb.append("main_type : " + main_type + " | sub_type: " + sub_type + "\n");
        sb.append("new_action :" + new_action + " | new_attr:" + new_attr + "| "
                + " | new_attr_value_type:" + new_attr_value_type + " | new_attr_value:" + new_attr_value + " | new_mode:" + new_mode + "\n");

        // speak
        sb.append("speak text : " + sdkSceneCommandMsg.getSpeakText() + "\n");

        System.out.print(sb.toString());
        System.out.println("====================华丽的分割线======================" + "\n");

    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_MASTER_BIND_DEVICES_RESULT_S)
    public void receiveAddMasterBindDeviceResult(S_ADD_DEVICE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【增加】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_DELETE_MASTER_BIND_DEVICE_RESULT_S)
    public void receiveDeleteMasterBindDeviceResult(S_DELETE_DEVICE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_SYNC_DEVICE_RESULT_S)
    public void receiveSyncMasterBindDeviceResult(S_SYNC_DEVICE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateMasterBindDeviceResult(S_UPDATE_DEVICE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateDeviceBindAreaResult(S_UPDATE_DEVICE_BIND_AREA response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】设备绑定区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateDeviceBindSceneResult(S_UPDATE_DEVICE_BIND_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】设备绑定场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

//    @Override
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_RECEIVED_DEVICE_COMMAND_S)
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
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_RECEIVED_DEVICE_TYPE_COMMAND_S)
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
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_RECEIVED_SCENE_COMMAND_S)
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
//    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_RECEIVED_AREA_COMMAND_S)
//    public void receiveAreaCmd(S_AREA_CMD response) {
//        String state = response.getSdkAreaCommandMsg().getCommand().getState();
//        String action = response.getSdkAreaCommandMsg().getCommand().getAction();
//        String type = response.getSdkAreaCommandMsg().getCommand().getType();
//        String value = response.getSdkAreaCommandMsg().getCommand().getValue();
//        LogUtil.debug("============>>>>>>>>>>>收到 S_AREA_CMD 协议，state :{} | action : {} | type : {} | " +
//                "value : {}", state, action, type, value);
//    }
}
