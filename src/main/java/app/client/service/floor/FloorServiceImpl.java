package app.client.service.floor;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.batch.floor.S_ADD_FLOOR_BATCH;
import app.client.net.protocol.response.sdk.batch.floor.S_DELETE_FLOOR;
import app.client.net.protocol.response.sdk.batch.floor.S_SYNC_FLOOR;
import app.client.net.protocol.response.sdk.batch.floor.S_UPDATE_FLOOR_BATCH;
import app.client.net.protocol.response.sdk.batch.floor.S_UPDATE_FLOOR_BIND_HOME_BATCH;
import app.client.net.protocol.response.sdk.single.floor.S_ADD_FLOOR;
import app.client.net.protocol.response.sdk.single.floor.S_UPDATE_FLOOR;
import app.client.net.protocol.response.sdk.single.floor.S_UPDATE_FLOOR_BIND_HOME;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class FloorServiceImpl extends AbstractServiceImpl implements IFloorService {

    @Override
    public void syncFloorResult(S_SYNC_FLOOR response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteFloorResult(S_DELETE_FLOOR response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addFloorResult(S_ADD_FLOOR response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【添加】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateFloorResult(S_UPDATE_FLOOR response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateFloorBindHomeResult(S_UPDATE_FLOOR_BIND_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】楼层绑定家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addFloorBatchResult(S_ADD_FLOOR_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【批量添加】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());

    }

    @Override
    public void updateFloorBatchResult(S_UPDATE_FLOOR_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【批量更新】楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());

    }

    @Override
    public void updateFloorBindHomeBatchResult(S_UPDATE_FLOOR_BIND_HOME_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【批量更新】楼层绑定家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());

    }
}
