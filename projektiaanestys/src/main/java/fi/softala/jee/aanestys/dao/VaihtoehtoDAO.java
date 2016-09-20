package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Vaihtoehto;

public interface VaihtoehtoDAO {
	
	public void insert (Vaihtoehto vEhto);
	
	public void delete (int VaihtoehtoID);
	
	public Vaihtoehto get (int Vaihtoehto);
	
	public List<Vaihtoehto> lista();

}
