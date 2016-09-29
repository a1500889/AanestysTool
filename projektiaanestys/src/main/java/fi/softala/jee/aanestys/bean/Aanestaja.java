package fi.softala.jee.aanestys.bean;

public interface Aanestaja {
	
	public abstract int getAanestajaID();
	
	public abstract void setAanestajaID(int AanestajaID);
	
	public abstract String getAanestajaEtunimi();
	
	public abstract void setAanestajaEtunimi(String etunimi);
	
	public abstract String getAanestajaSukunimi();
	
	public abstract void  setAanestajaSukunimi(String sukunimi);

}
