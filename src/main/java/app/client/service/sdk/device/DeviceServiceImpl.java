package app.client.service.sdk.device;

import app.client.common.TimeRecordKey;
import app.client.data.RobotDataHolder;
import app.client.net.annotation.Handler;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.device.C_DEVICE_SIMULAR_COMMAND;
import app.client.net.protocol.response.sdk.S_DOCKER_SPEAK;
import app.client.net.protocol.response.sdk.batch.device.S_ADD_DEVICE_BATCH;
import app.client.net.protocol.response.sdk.batch.device.S_DELETE_DEVICE;
import app.client.net.protocol.response.sdk.batch.device.S_DEVICE_ATTR_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_DEVICE_MODE_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_DEVICE_STATE_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_SCENE_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_SYNC_DEVICE;
import app.client.net.protocol.response.sdk.batch.device.S_UPDATE_DEVICE_BATCH;
import app.client.net.protocol.response.sdk.batch.device.S_UPDATE_DEVICE_BIND_AREA_BATCH;
import app.client.net.protocol.response.sdk.batch.device.S_UPDATE_DEVICE_BIND_SCENE_BATCH;
import app.client.net.protocol.response.sdk.single.device.S_ADD_DEVICE;
import app.client.net.protocol.response.sdk.single.device.S_UPDATE_DEVICE;
import app.client.net.protocol.response.sdk.single.device.S_UPDATE_DEVICE_BIND_AREA;
import app.client.net.protocol.response.sdk.single.device.S_UPDATE_DEVICE_BIND_SCENE;
import app.client.service.AbstractServiceImpl;
import app.client.service.sdk.area.AreaServiceImpl;
import app.client.testchain.sdk.SdkTestConst;
import app.client.user.session.UserSession;
import com.gowild.sdk.metadata.pb.BaseBothMsgProto;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.Tcp2DeviceProtocol;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import com.gowild.sdk.metadata.pb.Tcp2SdkMsgProto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zh on 2017/11/8.
 */
@Receiver
public class DeviceServiceImpl extends AbstractServiceImpl implements IDeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

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
//    @Handler(moduleId = 1, sequenceId = 2752)
    public void receiveStateCommand(S_DEVICE_STATE_COMMAND response) {
        logger.info("=============>> 收到状态控制指令");
        Tcp2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList = pushCommonCommandMsg.getCmdWrappersList();
        for (SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList) {

            SdkBothMsgProto.SdkNewCommandStructMsg new_control = sdkCommandWrapperMsg.getNewControl();

            SdkBothMsgProto.SdkNewControlDeviceListMsg type_device = new_control.getTypeDevice();
            List<SdkBothMsgProto.SdkNewControlDeviceInfoMsg> device_list = type_device.getDeviceListList();
            StringBuilder sb = new StringBuilder();
            int deviceType = type_device.getDeviceType();
            sb.append("=========================NEW CONTROL ==============================" + "\n");
            sb.append("deviceType:  " + deviceType + "\n");
            sb.append("deviceInfos:  " + "\n");
            for (SdkBothMsgProto.SdkNewControlDeviceInfoMsg device : device_list) {
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
            for (SdkBothMsgProto.SdkOldControlDeviceInfoMsg device : old_device_list) {
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

            logger.info(sb.toString());
            logger.info("====================华丽的分割线======================" + "\n");
        }
    }

    @Override
    //@Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_PUSH_ATTR_COMMAND_S)
//    @Handler(moduleId = 1, sequenceId = 2756)
    public void receiveAttrCommand(S_DEVICE_ATTR_COMMAND response) {
        logger.info("=============>> 收到属性制指令");
        Tcp2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList = pushCommonCommandMsg.getCmdWrappersList();
        for (SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList) {

            SdkBothMsgProto.SdkNewCommandStructMsg new_control = sdkCommandWrapperMsg.getNewControl();

            SdkBothMsgProto.SdkNewControlDeviceListMsg type_device = new_control.getTypeDevice();
            List<SdkBothMsgProto.SdkNewControlDeviceInfoMsg> device_list = type_device.getDeviceListList();
            StringBuilder sb = new StringBuilder();
            int deviceType = type_device.getDeviceType();
            sb.append("=========================NEW CONTROL ==============================" + "\n");
            sb.append("deviceType:  " + deviceType + "\n");
            sb.append("deviceInfos:  " + "\n");
            for (SdkBothMsgProto.SdkNewControlDeviceInfoMsg device : device_list) {
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
            for (SdkBothMsgProto.SdkOldControlDeviceInfoMsg device : old_device_list) {
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

            logger.info(sb.toString());
            logger.info("====================华丽的分割线======================" + "\n");
        }
    }

    @Override
    public void receiveModeCommand(S_DEVICE_MODE_COMMAND response) {
        logger.info("=============>> 收到模式控制指令");
        Tcp2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
        List<SdkBothMsgProto.SdkCommandWrapperMsg> sdkCommandWrapperMsgList = pushCommonCommandMsg.getCmdWrappersList();
        for (SdkBothMsgProto.SdkCommandWrapperMsg sdkCommandWrapperMsg : sdkCommandWrapperMsgList) {

            SdkBothMsgProto.SdkNewCommandStructMsg new_control = sdkCommandWrapperMsg.getNewControl();

            SdkBothMsgProto.SdkNewControlDeviceListMsg type_device = new_control.getTypeDevice();
            List<SdkBothMsgProto.SdkNewControlDeviceInfoMsg> device_list = type_device.getDeviceListList();
            StringBuilder sb = new StringBuilder();
            int deviceType = type_device.getDeviceType();
            sb.append("=========================NEW CONTROL ==============================" + "\n");
            sb.append("deviceType:  " + deviceType + "\n");
            sb.append("deviceInfos:  " + "\n");
            for (SdkBothMsgProto.SdkNewControlDeviceInfoMsg device : device_list) {
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
            for (SdkBothMsgProto.SdkOldControlDeviceInfoMsg device : old_device_list) {
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

            logger.info(sb.toString());
            logger.info("====================华丽的分割线======================" + "\n");
        }

    }

    @Override
    public void receiveSceneCommand(S_SCENE_COMMAND response) {
        logger.info("=============>> 收到场景控制指令");
        StringBuilder sb = new StringBuilder();

        Tcp2SdkMsgProto.SdkSceneCommandMsg sdkSceneCommandMsg = response.getSdkSceneCommandMsg();

        // scenelist
        List<Tcp2SdkMsgProto.SceneTidWithName> sceneListList = sdkSceneCommandMsg.getSceneListList();
        for (Tcp2SdkMsgProto.SceneTidWithName scene : sceneListList) {
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

        logger.info(sb.toString());
        logger.info("====================华丽的分割线======================" + "\n");

    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_MASTER_BIND_DEVICES_RESULT_S)
    public void receiveAddDeviceResult(S_ADD_DEVICE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【增加】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_DELETE_MASTER_BIND_DEVICE_RESULT_S)
    public void receiveDeleteDeviceResult(S_DELETE_DEVICE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【删除】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    @Handler(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SDK_SYNC_DEVICE_RESULT_S)
    public void receiveSyncDeviceResult(S_SYNC_DEVICE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【同步】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
        response.getUserSession().markTimeEnd(TimeRecordKey.SDK_SYNC_DEVICE_TIME);
    }

    @Override
    public void receiveUpdateDeviceResult(S_UPDATE_DEVICE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【更新】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateDeviceBindAreaResult(S_UPDATE_DEVICE_BIND_AREA response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【更新】设备绑定区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateDeviceBindSceneResult(S_UPDATE_DEVICE_BIND_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【更新】设备绑定场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveAddDeviceBatchResult(S_ADD_DEVICE_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量增加】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateDeviceBatchResult(S_UPDATE_DEVICE_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量更新】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateDeviceBindAreaBatchResult(S_UPDATE_DEVICE_BIND_AREA_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量更新】设备绑定区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveUpdateDeviceBindSceneBatchResult(S_UPDATE_DEVICE_BIND_SCENE_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量更新】设备绑定场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void receiveDockerSpeak(S_DOCKER_SPEAK reponse) {
        BaseBothMsgProto.StringMsg stringMsg = reponse.getStringMsg();
        logger.info("====== >>> 设备间通信【DOCKER】讲话是 : " + stringMsg.getValue());
    }
}
