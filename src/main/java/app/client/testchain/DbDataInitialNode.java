package app.client.testchain;

/**
 * Created by zh on 2017/11/21.
 */
public abstract class DbDataInitialNode extends AbstractChainNode{

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
