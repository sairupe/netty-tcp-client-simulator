package app.client.testchain;

import app.client.net.protocol.ResponseProtocol;
import app.client.user.session.UserSession;

import java.sql.Connection;

/**
 * Created by zh on 2017/11/21.
 */
public interface IChainNode extends Runnable{

    void start();

    void execute();

    void doExecute();

    void end();

    AbstractChainNode registListenProtocol(Class<? extends ResponseProtocol> listenningPotocol);

    void addLastNext(AbstractChainNode chainNode);

    IChainNode next();

    void next(IChainNode nextNode);

    boolean canExecuteImmediately();

    void setVar(UserSession userSession, Connection connection);

}
