package it.proprietarioautomobile.test;

import java.util.ArrayList;
import java.util.List;

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

			testListAllProprietario(proprietarioService);

			testUpdateProprietario(proprietarioService);

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

	private static void testListAllProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("----- Inizio test list proprietario -----");
		Proprietario proprietarioDaInserire = new Proprietario("test list", "test list", "test list",
				new java.util.Date(2022, 01, 01));
		if (proprietarioDaInserire.getId() != null)
			throw new RuntimeException("test di inserimento fallito, record gia presente");
		List<Proprietario> listaDiProprietari = new ArrayList<Proprietario>();
		listaDiProprietari = proprietarioService.listAllProprietari();
		if (listaDiProprietari == null || listaDiProprietari.isEmpty())
			throw new RuntimeException("lista di proprietari vuota");
		System.out.println("----- test eseguito con successo -----");
	}

	private static void testUpdateProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println("----- Inizio test update proprietario -----");
		List<Proprietario> listaDiProprietari = new ArrayList<Proprietario>();
		listaDiProprietari = proprietarioService.listAllProprietari();
		if (listaDiProprietari == null || listaDiProprietari.isEmpty())
			throw new RuntimeException("lista di proprietari vuota");
		Proprietario proprietarioUpdate = new Proprietario("update", "update", "update",
				new java.util.Date(2022, 01, 01));
		if (proprietarioUpdate.getId() != null)
			throw new RuntimeException("test di update fallito, record gia presente");
		proprietarioService.inserisciNuovo(proprietarioUpdate);
		if (proprietarioUpdate.getId() == null)
			throw new RuntimeException("test di update fallito, record non inserito");
		proprietarioUpdate.setCognome("nuovo update");
		proprietarioService.aggiorna(proprietarioUpdate);
		if (!(proprietarioUpdate.getCognome().equals("nuovo update")))
			throw new RuntimeException("update fallito");
		System.out.println("----- test eseguito con successo -----");
	}

}
