package it.proprietarioautomobile.test;

import it.proprietarioautomobile.dao.EntityManagerUtil;
import it.proprietarioautomobile.model.Proprietario;
import it.proprietarioautomobile.service.MyServiceFactory;
import it.proprietarioautomobile.service.automobile.AutomobileService;
import it.proprietarioautomobile.service.proprietario.ProprietarioService;

public class Test {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			testInserisciProprietario(proprietarioService);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("----- Inizio test inserimento nuovo proprietario -----");
		Proprietario proprietarioDaInserire = new Proprietario("test inserimento", "test inserimento",
				"test inserimento", new java.util.Date(2022, 01, 01));
		if (proprietarioDaInserire.getId() != null)
			throw new RuntimeException("test di inserimento fallito, record gia presente");
		proprietarioService.inserisciNuovo(proprietarioDaInserire);
		if (proprietarioDaInserire.getId() == null)
			throw new RuntimeException("test di inserimento fallito, record non inserito");
		System.out.println("----- test eseguito con successo -----");
	}

}
