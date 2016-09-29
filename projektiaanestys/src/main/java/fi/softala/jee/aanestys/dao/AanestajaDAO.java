package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Aanestaja;

public interface AanestajaDAO {
	
	public void insert (Aanestaja aanestaja);
	
	public void delete (int AanestajaID);
	
	public List<Aanestaja> lista();

}
