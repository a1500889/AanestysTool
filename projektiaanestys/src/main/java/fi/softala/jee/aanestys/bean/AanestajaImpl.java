package fi.softala.jee.aanestys.bean;

public class AanestajaImpl implements Aanestaja {
	
	private int AanestajaID;
	private String Etunimi;
	private String Sukunimi;

	public int getAanestajaID() {
		return AanestajaID;
	}

	public void setAanestajaID(int AanestajaID) {
		this.AanestajaID = AanestajaID;
		
	}

	public String getAanestajaEtunimi() {
		return Etunimi;
	}

	public void setAanestajaEtunimi(String etunimi) {
		this.Etunimi = etunimi;
		
	}

	public String getAanestajaSukunimi() {
		return Sukunimi;
	}

	public void setAanestajaSukunimi(String sukunimi) {
		this.Sukunimi = sukunimi;
		
	}

	@Override
	public String toString() {
		return "AanestajaImpl [AanestajaID=" + AanestajaID + ", Etunimi="
				+ Etunimi + ", Sukunimi=" + Sukunimi + "]";
	}

}
