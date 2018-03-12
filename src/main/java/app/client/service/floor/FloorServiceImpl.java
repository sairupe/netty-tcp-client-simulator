package app.client.service.floor;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.floor.S_ADD_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_DELETE_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_SYNC_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_UPDATE_FLOOR_BIND_HOME;
import app.client.net.protocol.response.sdk.floor.S_UPDATE_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.home.S_ADD_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_DELETE_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_SYNC_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_UPDATE_HOME_RESULT;
import app.client.service.AbstractServiceImpl;
import app.client.service.home.IHomeService;
import com.gowild.sdktcp.metadata.pb.SdkBothMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class FloorServiceImpl extends AbstractServiceImpl implements IFloorService {

    @Override
    public void addFloorResult(S_ADD_FLOOR_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【添加】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteFloorResult(S_DELETE_FLOOR_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateFloorResult(S_UPDATE_FLOOR_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void syncFloorResult(S_SYNC_FLOOR_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateFloorBindHomeResult(S_UPDATE_FLOOR_BIND_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】楼层绑定家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
