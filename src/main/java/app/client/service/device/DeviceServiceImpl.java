package app.client.service.device;

import app.client.net.annotation.Handler;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_DEVICE_SIMULAR_COMMAND;
import app.client.net.protocol.response.S_DEVICE_STATE_COMMAND;
import app.client.service.AbstractServiceImpl;
import app.client.user.session.UserSession;
import com.gowild.core.util.LogUtil;
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
        SdkDownloadMsgProto.PushCommonCommandMsg pushCommonCommandMsg = response.getPushCommonCommandMsg();
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
