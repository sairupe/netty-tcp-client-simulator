package app.client.service;

import app.client.net.dispacher.ServiceManager;

public class AbstractServiceImpl implements IService{

	@Override
	public void initRelyService() {
		ServiceManager.injectionReceiver(this);
	}
}
