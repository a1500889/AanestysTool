package fi.softala.jee.aanestys.bean;

public class AanestajaImpl implements Aanestaja {
	
	private int AanestajaID;
	private String Etunimi;
	private String Sukunimi;
	private Ryhma Ryhma;

	public int getAanestajaID() {
		return AanestajaID;
	}

	public void setAanestajaID(int AanestajaID) {
		this.AanestajaID = AanestajaID;
		
	}

	public String getEtunimi() {
		return Etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.Etunimi = etunimi;
		
	}

	public String getSukunimi() {
		return Sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.Sukunimi = sukunimi;
		
	}
	
	public Ryhma getRyhma() {
		return this.Ryhma;
	}
	
	public void setRyhma(Ryhma ryhma) {
		this.Ryhma=ryhma;
		
	}

	@Override
	public String toString() {
		return "AanestajaImpl [AanestajaID=" + AanestajaID + ", Etunimi="
				+ Etunimi + ", Sukunimi=" + Sukunimi + "]";
	}



}
