package app.client.testchain.sdk.protocol.home;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.home.C_UPDATE_HOME_BATCH;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import com.gowild.sdk.vo.db.AddHomeInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class UpdateHomeCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        List<AddHomeInfoVo> updateHomeInfoVoList = new ArrayList<>();

        String firstHomeTid = SdkTestConst.FIRST_HOME_TID;
        String secondHomeTid = SdkTestConst.SECOND_HOME_TID;
        String firstHomeName = SdkTestConst.FIRST_HOME_NAME;
        String secondHomeName = SdkTestConst.SECOND_HOME_NAME;

        AddHomeInfoVo addHomeInfoVo1 = new AddHomeInfoVo();
        addHomeInfoVo1.setHomeTid(firstHomeTid);
        addHomeInfoVo1.setHomeName(firstHomeName);

        AddHomeInfoVo addHomeInfoVo2 = new AddHomeInfoVo();
        addHomeInfoVo2.setHomeTid(secondHomeTid);
        addHomeInfoVo2.setHomeName(secondHomeName);

        updateHomeInfoVoList.add(addHomeInfoVo1);
        updateHomeInfoVoList.add(addHomeInfoVo2);


        C_UPDATE_HOME_BATCH protocol = ProtocolFactory.createRequestProtocol(C_UPDATE_HOME_BATCH.class,
                userSession.getCtx());
        protocol.setUpdateHomeInfoVoList(updateHomeInfoVoList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
