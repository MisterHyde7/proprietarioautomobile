package it.proprietarioautomobile.test;

import java.util.ArrayList;
import java.util.List;

import it.proprietarioautomobile.dao.EntityManagerUtil;
import it.proprietarioautomobile.model.Automobile;
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

			testFindById(proprietarioService);

			testRemove(proprietarioService);

			// ==========================//

			testInserisciAutomobile(automobileService);

			testListAllAutomobile(automobileService);

			testUpdateAutomobile(automobileService);

			testFindByIdAuto(automobileService);

			testRemoveAuto(automobileService);

			// ==========================//

			testQuanteAutoConProprietariConCodiceFiscale(automobileService, proprietarioService);

			testQuantiHannoAutoImmatricolateNel(automobileService, proprietarioService);

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

		proprietarioService.inserisciNuovo(proprietarioDaInserire);

		if (proprietarioDaInserire.getId() == null)
			throw new RuntimeException("test di update fallito, record non inserito");

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

	private static void testFindById(ProprietarioService proprietarioService) throws Exception {
		System.out.println("----- Inizio test find by id proprietario -----");

		Proprietario proprietarioDaTrovare = new Proprietario("luca", "martucci", "mrt",
				new java.util.Date(2022, 01, 01));

		if (proprietarioDaTrovare.getId() != null)
			throw new RuntimeException("test di inserimento fallito, record gia presente");

		proprietarioService.inserisciNuovo(proprietarioDaTrovare);

		if (proprietarioDaTrovare.getId() == null)
			throw new RuntimeException("test di inserimento fallito, record non inserito");

		if (proprietarioService.caricaSingoloProprietario(proprietarioDaTrovare.getId()) == null)
			throw new RuntimeException("errore find by id");

		System.out.println("----- test eseguito con successo -----");
	}

	private static void testRemove(ProprietarioService proprietarioService) throws Exception {
		System.out.println("----- Inizio test remove proprietario -----");

		List<Proprietario> listaDiProprietari = new ArrayList<Proprietario>();

		listaDiProprietari = proprietarioService.listAllProprietari();

		if (listaDiProprietari == null || listaDiProprietari.isEmpty())
			throw new RuntimeException("lista di proprietari vuota");

		Proprietario proprietarioRemove = new Proprietario("remove", "remove", "remove",
				new java.util.Date(2022, 01, 01));

		if (proprietarioRemove.getId() != null)
			throw new RuntimeException("test di remove fallito, record gia presente");

		proprietarioService.inserisciNuovo(proprietarioRemove);

		if (proprietarioRemove.getId() == null)
			throw new RuntimeException("test di remove fallito, record non inserito");

		proprietarioService.rimuovi(proprietarioRemove);

		if (proprietarioService.caricaSingoloProprietario(proprietarioRemove.getId()) != null)
			throw new RuntimeException("test di remove fallito");

		System.out.println("----- test eseguito con successo -----");
	}

	private static void testInserisciAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println("----- Inizio test inserimento nuovo automobile -----");

		Automobile autoDaInserire = new Automobile("test", "test", "test", 2000);

		if (autoDaInserire.getId() != null)
			throw new RuntimeException("test di inserimento fallito, record gia presente");

		automobileService.inserisciNuovo(autoDaInserire);

		if (autoDaInserire.getId() == null)
			throw new RuntimeException("test di inserimento fallito, record non inserito");

		System.out.println("----- test eseguito con successo -----");
	}

	private static void testListAllAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println("----- Inizio test list proprietario -----");

		Automobile automobileDaInserire = new Automobile("test list", "test list", "list", 2000);

		if (automobileDaInserire.getId() != null)
			throw new RuntimeException("test di inserimento fallito, record gia presente");

		automobileService.inserisciNuovo(automobileDaInserire);

		if (automobileDaInserire.getId() == null)
			throw new RuntimeException("test di inserimento fallito, record non inserito");

		List<Automobile> listaDiAuto = new ArrayList<Automobile>();

		listaDiAuto = automobileService.listAllAutomobili();

		if (listaDiAuto == null || listaDiAuto.isEmpty())
			throw new RuntimeException("lista di proprietari vuota");

		System.out.println("----- test eseguito con successo -----");
	}

	private static void testUpdateAutomobile(AutomobileService automobileService) throws Exception {
		System.out.println("----- Inizio test update automobile -----");

		List<Automobile> listaDiAuto = new ArrayList<Automobile>();

		listaDiAuto = automobileService.listAllAutomobili();

		if (listaDiAuto == null || listaDiAuto.isEmpty())
			throw new RuntimeException("lista di automobili vuota");

		Automobile automobileUpdate = new Automobile("update", "update", "update", 2000);

		if (automobileUpdate.getId() != null)
			throw new RuntimeException("test di update fallito, record gia presente");

		automobileService.inserisciNuovo(automobileUpdate);

		if (automobileUpdate.getId() == null)
			throw new RuntimeException("test di update fallito, record non inserito");

		automobileUpdate.setAnnoImmatricolazione(1990);

		automobileService.aggiorna(automobileUpdate);

		if (automobileUpdate.getAnnoImmatricolazione() != 1990)
			throw new RuntimeException("update fallito");

		System.out.println("----- test eseguito con successo -----");
	}

	private static void testFindByIdAuto(AutomobileService automobileService) throws Exception {
		System.out.println("----- Inizio test find by id automobile -----");

		Automobile autoDaTrovare = new Automobile("find", "find", "find", 2000);

		if (autoDaTrovare.getId() != null)
			throw new RuntimeException("test di inserimento fallito, record gia presente");

		automobileService.inserisciNuovo(autoDaTrovare);

		if (autoDaTrovare.getId() == null)
			throw new RuntimeException("test di inserimento fallito, record non inserito");

		if (automobileService.caricaSingolaAutomobile(autoDaTrovare.getId()) == null)
			throw new RuntimeException("errore find by id");

		System.out.println("----- test eseguito con successo -----");
	}

	private static void testRemoveAuto(AutomobileService automobileService) throws Exception {
		System.out.println("----- Inizio test remove automobile -----");

		List<Automobile> listaDiAuto = new ArrayList<Automobile>();

		listaDiAuto = automobileService.listAllAutomobili();

		if (listaDiAuto == null || listaDiAuto.isEmpty())
			throw new RuntimeException("lista di proprietari vuota");

		Automobile autoRemove = new Automobile("remove", "remove", "remove", 2000);

		if (autoRemove.getId() != null)
			throw new RuntimeException("test di remove fallito, record gia presente");

		automobileService.inserisciNuovo(autoRemove);

		if (autoRemove.getId() == null)
			throw new RuntimeException("test di remove fallito, record non inserito");

		automobileService.rimuovi(autoRemove);

		if (automobileService.caricaSingolaAutomobile(autoRemove.getId()) != null)
			throw new RuntimeException("test di remove fallito");

		System.out.println("----- test eseguito con successo -----");
	}

	private static void testQuanteAutoConProprietariConCodiceFiscale(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {
		System.out.println("----- Inizio test -----");

		Proprietario nuovoPropietario = new Proprietario("test", "test", "test", new java.util.Date(2000, 01, 01));

		if (nuovoPropietario.getId() != null)
			throw new RuntimeException("errore");

		proprietarioService.inserisciNuovo(nuovoPropietario);

		if (nuovoPropietario.getId() == null)
			throw new RuntimeException("errore");

		Automobile nuovaAutomobile1 = new Automobile("prova", "prova", "prova", 2000);
		Automobile nuovaAutomobile2 = new Automobile("test", "test", "test", 2020);

		if (nuovaAutomobile1.getId() != null || nuovaAutomobile2.getId() != null)
			throw new RuntimeException("errore");

		automobileService.inserisciNuovo(nuovaAutomobile1);

		if (nuovaAutomobile1.getId() == null)
			throw new RuntimeException("errore");

		automobileService.inserisciNuovo(nuovaAutomobile2);

		if (nuovaAutomobile2.getId() == null)
			throw new RuntimeException("errore");

		nuovoPropietario.getAutomobili().add(nuovaAutomobile1);
		nuovoPropietario.getAutomobili().add(nuovaAutomobile2);
		nuovaAutomobile1.setProprietario(nuovoPropietario);
		nuovaAutomobile2.setProprietario(nuovoPropietario);

		automobileService.aggiorna(nuovaAutomobile1);
		automobileService.aggiorna(nuovaAutomobile2);

		proprietarioService.aggiorna(nuovoPropietario);

		List<Automobile> listaAuto = automobileService.quanteAutoConProprietarioConCodFis("te");

		if (listaAuto.size() < 1)
			throw new RuntimeException("errore");

		System.out.println("----- test eseguito con successo -----");
	}

	public static void testQuantiHannoAutoImmatricolateNel(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {

		System.out.println("----- Inizio test -----");

		Proprietario nuovoProprietario = new Proprietario("Guggo", "Gruoogo", "MRPEIEJF",
				new java.util.Date(2000, 01, 01));
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("errore");
		proprietarioService.inserisciNuovo(nuovoProprietario);
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("errore");
		Automobile nuovaAutomobile = new Automobile("test", "test", "test", 2000);
		if (nuovaAutomobile.getId() != null)
			throw new RuntimeException("errore");
		automobileService.inserisciNuovo(nuovaAutomobile);

		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("errore");

		nuovoProprietario.getAutomobili().add(nuovaAutomobile);
		nuovaAutomobile.setProprietario(nuovoProprietario);
		automobileService.aggiorna(nuovaAutomobile);

		proprietarioService.aggiorna(nuovoProprietario);

		System.out.println(proprietarioService.contaQuantiConAutoImmatricolataDopo(2000));

		System.out.println("----- test eseguito con successo -----");
	}

}
