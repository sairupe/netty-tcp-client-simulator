package app.client.testchain;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ResponseProtocol;
import app.client.user.session.UserSession;
import com.gowild.core.util.LogUtil;

import java.sql.Connection;

/**
 * Created by zh on 2017/11/21.
 */
public abstract class AbstractChainNode implements IChainNode {

    /**
     * 链式调用
     */
    protected IChainNode nextNode;
    /**
     * 用户汇话
     */
    protected UserSession userSession;
    /**
     * 数据库链接对象
     */
    protected Connection connection;
    /**
     * 监听模块ID
     */
    protected int moduleId;
    /**
     * 监听sequenceId
     */
    protected int sequenceId;

    @Override
    public void start(UserSession userSession, Connection connection) {
        execute();
    }

    @Override
    public void end() {
        LogUtil.debug("==================>>>>>>>>>>>>> NODE CHAIN END");
    }

    @Override
    public void execute(){
        if(nextNode == null){
            end();
        }
        doExecute();
        nextNode = next();
        if(nextNode != null && nextNode.canExecuteImmediately()){
            nextNode.execute();
        }
    }

    @Override
    public void sniff(ResponseProtocol responseProtocol) {
        // CHILD IMPLEMENT
    }

    @Override
    public void addLastNext(AbstractChainNode chainNode) {
        chainNode.userSession = userSession;
        chainNode.connection = connection;
        // 加到链尾
        if(nextNode == null){
            nextNode = chainNode;
        }else{
            IChainNode node = nextNode;
            while(node.next() != null){
                node = node.next();
            }
            node.next(chainNode);
        }
    }

    @Override
    public void next(IChainNode nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public IChainNode next() {
        return nextNode;
    }

    @Override
    public AbstractChainNode registListenProtocol(Class<? extends ResponseProtocol> listenningPotocol) {
       if(listenningPotocol != null){
           Protocol protocol = listenningPotocol.getAnnotation(Protocol.class);
           if(protocol != null){
               this.moduleId = protocol.moduleId();
               this.sequenceId = protocol.sequenceId();
           }
       }
       return this;
    }

    @Override
    public void setVar(UserSession userSession, Connection connection){
        this.userSession = userSession;
        this.connection = connection;
    }
}
