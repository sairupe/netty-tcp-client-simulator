package app.client.testchain;

import app.client.net.dispacher.DispacherManager;
import app.client.net.protocol.response.S_DEVICE_LOGIN_RESULT;
import app.client.testchain.sdk.db.BaseDbInfoInsertNode;
import app.client.testchain.sdk.protocol.LoginCommandNode;
import app.client.testchain.sdk.protocol.SimularCommandNode;
import app.client.user.session.UserSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zh on 2017/11/21.
 */
public class ChainNodeManager {

    private static ChainNodeManager chainNodeManager = new ChainNodeManager();
    private static Connection con;
    private ChainNodeManager() {

    }

    static {
        String urlstr = "jdbc:mysql://localhost:3306/gwsdk";
        String sql = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("classnotfoundexception :");
            System.err.print(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(urlstr, "root", "123456");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("sqlexception :" + ex.getMessage());
            System.err.println("sql :" + sql);
        }
    }

    /**
     * 开始执行的任务节点
     */
    public IChainNode startingChainNode;

    public static ChainNodeManager getInstance(){
        return chainNodeManager;
    }

    public void start(UserSession userSession){
        startingChainNode = new BaseDbInfoInsertNode();
        startingChainNode.setVar(userSession, con);
        startingChainNode.addLastNext(new LoginCommandNode());
        startingChainNode.addLastNext(new SimularCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));

        startingChainNode.start(userSession, con);
        DispacherManager.getInstance().setChainNode(startingChainNode);
    }
}
