package app.client.net.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.client.net.dispacher.DispacherManager;
import app.client.net.protocol.ResponseProtocol;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:20:42
 */
public class ResponseTaskImpl implements IResponseTask{
	
    private static final Logger logger = LoggerFactory
            .getLogger(ResponseTaskImpl.class);

	private ResponseProtocol response;

	public ResponseTaskImpl(ResponseProtocol response){
		this.response = response;
	}
	
	@Override
	public void run() {
		dispach(response);
	}
	
	@Override
	public void dispach(ResponseProtocol response) {
		try {
            int moduleId = response.getModuleId();
            int seqenceId = response.getSequenceId();
            DispacherManager.getInstance().dispatch(moduleId, seqenceId,
                    response);
            logger.info("收到#####>>>>moduleId:【{}】, sequenceId:【{}】", moduleId,
                    seqenceId);
			response = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
