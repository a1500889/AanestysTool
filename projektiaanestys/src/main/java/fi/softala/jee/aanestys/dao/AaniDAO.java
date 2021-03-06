package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.Aani;

public interface AaniDAO {
	
	public void insert (Aani Aani, int kayttajaID);
	
	public boolean tarkistaFusku(int KayttajaID, int VaihtoehtoID);
	
	public void delete (int AanestysID);
	
	public Aani get (int Aani);
	
	public List<Aani> lista(int AanestID);
	
	public void aanestanyt(int AanestysID, int AanestajaID);
	
	public List<Aanestaja> listaaKusimutterit();
	
	public int haeLupaSumma(int AanestysID);
	
	public int haeAnnettujenAanienMaara(int AanestysID);
	
	public int hylattyjenAanienMaara(int AanID);
	
}
