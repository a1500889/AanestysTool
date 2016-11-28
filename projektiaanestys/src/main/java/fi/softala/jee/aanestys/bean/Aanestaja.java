package fi.softala.jee.aanestys.bean;

public interface Aanestaja {
	
	public abstract int getAanestajaID();
	
	public abstract void setAanestajaID(int AanestajaID);
	
	public abstract String getEtunimi();
	
	public abstract void setEtunimi(String Etunimi);
	
	public abstract String getSukunimi();
	
	public abstract void  setSukunimi(String Sukunimi);
	
	public abstract Ryhma getRyhma();
	
	public abstract void setRyhma(Ryhma ryhma);

}
