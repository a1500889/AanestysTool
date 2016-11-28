package fi.softala.jee.aanestys.bean;

public class RyhmaImpl implements Ryhma {
	private int RyhmaID;
	private String RyhmaNimi;
	private String RyhmaTunnus;
	
	public void Ryhma(int id, String nimi, String tunnus){
		this.RyhmaID=id;
		this.RyhmaNimi=nimi;
		this.RyhmaTunnus=tunnus;
	}

	public int getRyhmaID() {
		return this.RyhmaID;
	}

	public void setRyhmaID(int ryhmaid) {
		this.RyhmaID=ryhmaid;
		
	}

	public String getRyhmaNimi() {
		return RyhmaNimi;
	}

	public void setRyhmaNimi(String ryhmanimi) {
		this.RyhmaNimi=ryhmanimi;
		
	}

	public String getRyhmaTunnus() {
		return this.RyhmaTunnus;
	}

	public void setRyhmaTunnus(String ryhmatunnus) {
		this.RyhmaTunnus=ryhmatunnus;
		
	}

}
