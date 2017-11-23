package app.client.testchain.sdk.protocol;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_DEVICE_SIMULAR_COMMAND;
import app.client.testchain.AbstractChainNode;
import app.client.testchain.ProtocolListenNode;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by zh on 2017/11/21.
 */
public class SimularCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
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
}
