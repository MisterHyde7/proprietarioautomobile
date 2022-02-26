package it.proprietarioautomobile.dao.automobile;

import java.util.List;

import it.proprietarioautomobile.dao.IBaseDAO;
import it.proprietarioautomobile.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile> {
	
	public List<Automobile> quanteAutoConProprietariConCodFiscaleUgualeA(String inputCodiceFiscale) throws Exception ;

}
