//package app.client.testchain;
//
//import app.client.net.protocol.ProtocolFactory;
//import app.client.net.protocol.request.smarthome.C_QUERY_ROBOT_COLLA;
//
///**
// * Created by zh on 2017/11/21.
// */
//public class AppQueryRobotCollaCommandNode extends ProtocolListenNode {
//
//    private int robotId;
//
//    public AppQueryRobotCollaCommandNode(int robotId){
//        this.robotId = robotId;
//    }
//
//    @Override
//    public void doExecute() {
//        C_QUERY_ROBOT_COLLA queryRobotColla = ProtocolFactory.createRequestProtocol(C_QUERY_ROBOT_COLLA.class, userSession.getCtx());
//        queryRobotColla.setRobotId(robotId);
//        userSession.sendMsg(queryRobotColla);
//    }
//
//    @Override
//    public boolean canExecuteImmediately(){
//        return true;
//    }
//}
