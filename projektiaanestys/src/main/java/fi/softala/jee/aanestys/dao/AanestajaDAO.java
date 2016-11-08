package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.Aani;

public interface AanestajaDAO {
	
	public void insert (Aanestaja aanestaja);
	
	public void delete (int AanestajaID);
	
	public void deletet (int AanestysID);
	
	public void poistaLuvatAanestaja (int AanestajaID);
	
	public List<Aanestaja> lista();

	public List<String> listaaLuvalliset(int AanestysID);
	
	public int haeVapaaAanestajaID(Aani Aani, String etunimi, String sukunimi);
	
	public boolean tarkistaAanestysoikeus(int kayttajaID, int aanestysID);
}
