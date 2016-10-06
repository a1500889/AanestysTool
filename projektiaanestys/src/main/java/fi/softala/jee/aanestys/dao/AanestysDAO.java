package fi.softala.jee.aanestys.dao;

import java.util.List;

import fi.softala.jee.aanestys.bean.Aanestys;
import fi.softala.jee.aanestys.bean.Aani;

public interface AanestysDAO {
	
	public List<Aanestys> lista();
	
	public  void saveOrUpdate (Aanestys aanestys);
		
	public void delete(int AanestysID);
	
	public void poistaLuvatAanestys (int AanestysID);
	
	
}
