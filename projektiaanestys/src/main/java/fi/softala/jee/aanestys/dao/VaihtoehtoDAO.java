package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Vaihtoehto;

public interface VaihtoehtoDAO {
	
	public void insert (Vaihtoehto vEhto);
	
	public void delete (int VaihtoehtoID);
	
	public void deletet(int AanestysID);
	
	public Vaihtoehto get (int Vaihtoehto);
	
	public List<Vaihtoehto> haeVaihtoehdot (int AanestysID);
	
	public List<Vaihtoehto> lista();

}
