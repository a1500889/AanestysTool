package fi.softala.jee.aanestys.bean;

public class AanestysImpl implements Aanestys {
//git
	private int AanestysID;
	private String Tunnus;
	private String AanestysNimi;
	private String Kuvaus;
	
	public int getAanestysID() {
		return AanestysID;
	}
	public void setAanestysID(int aanestysID) {
		AanestysID = aanestysID;
	}
	public String getTunnus() {
		return Tunnus;
	}
	public void setTunnus(String tunnus) {
		Tunnus = tunnus;
	}
	public String getAanestysNimi() {
		return AanestysNimi;
	}
	public void setAanestysNimi(String aanestysNimi) {
		AanestysNimi = aanestysNimi;
	}
	public String getKuvaus() {
		return Kuvaus;
	}
	public void setKuvaus(String kuvaus) {
		Kuvaus = kuvaus;
	}
	
	public AanestysImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "AanestysImpl [AanestysID=" + AanestysID + ", Tunnus=" + Tunnus
				+ ", AanestysNimi=" + AanestysNimi + ", Kuvaus=" + Kuvaus + "]";
	}
	
	
	
}
