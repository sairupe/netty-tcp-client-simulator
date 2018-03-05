package app.client.service.area;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.area.S_ADD_AREA_RESULT;
import app.client.net.protocol.response.sdk.area.S_DELETE_AREA_RESULT;
import app.client.net.protocol.response.sdk.area.S_SYNC_AREA_RESULT;
import app.client.net.protocol.response.sdk.area.S_UPDATE_AREA_RESULT;
import app.client.net.protocol.response.sdk.floor.S_ADD_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_DELETE_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_SYNC_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_UPDATE_FLOOR_RESULT;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdktcp.metadata.pb.SdkBothMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class AreaServiceImpl extends AbstractServiceImpl implements IAreaService {

    @Override
    public void addAreaResult(S_ADD_AREA_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【添加】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteAreaResult(S_DELETE_AREA_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateAreaResult(S_UPDATE_AREA_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void syncAreaResult(S_SYNC_AREA_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
