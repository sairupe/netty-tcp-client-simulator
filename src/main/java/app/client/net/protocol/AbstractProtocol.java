package app.client.net.protocol;

/**
 * @author syriana.zh
 * <p>
 * 2016年4月21日 下午3:19:31
 */
public abstract class AbstractProtocol {

    private int moduleId;

    private int sequenceId;

    protected void setModuleIdAndSequenceId(int moduleId, int sequenceId,
                                            ProtocolType protocolType) {
        this.moduleId = moduleId;
        this.sequenceId = sequenceId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public int getSequenceId() {
        return sequenceId;
    }


}
