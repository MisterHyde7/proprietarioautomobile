package it.proprietarioautomobile.test;

import it.proprietarioautomobile.dao.EntityManagerUtil;
import it.proprietarioautomobile.service.MyServiceFactory;
import it.proprietarioautomobile.service.automobile.AutomobileService;
import it.proprietarioautomobile.service.proprietario.ProprietarioService;

public class Test {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

}
