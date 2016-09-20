package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Aani;

public interface AaniDAO {

	public void insert (Aani Aani);
	
	public void delete (int AaniID);
	
	public Aani get (int Aani);
	
	public List<Aani> lista();
	
}
