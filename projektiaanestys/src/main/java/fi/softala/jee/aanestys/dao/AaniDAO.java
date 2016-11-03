package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Aani;

public interface AaniDAO {

	public void insert (Aani Aani);
	
	public void insert (Aani Aani, String Etunimi, String Sukunimi);
	
	public void delete (int AanestysID);
	
	public void deletet (int AaniID);
	
	public Aani get (int Aani);
	
	public List<Aani> lista(int AanestID);
	
	public void aanestanyt(int AanestysID, int AanestajaID);
	
}
