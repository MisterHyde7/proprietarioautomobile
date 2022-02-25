package it.proprietarioautomobile.service;

import it.proprietarioautomobile.dao.MyDaoFactory;
import it.proprietarioautomobile.service.automobile.AutomobileService;
import it.proprietarioautomobile.service.automobile.AutomobileServiceImpl;
import it.proprietarioautomobile.service.proprietario.ProprietarioService;
import it.proprietarioautomobile.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {

	// rendiamo le istanze restituite SINGLETON
	private static ProprietarioService proprietarioServiceInstance = null;
	private static AutomobileService automobileServiceInstance = null;

	public static ProprietarioService getProprietarioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}

	public static AutomobileService getAutomobileServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

}
