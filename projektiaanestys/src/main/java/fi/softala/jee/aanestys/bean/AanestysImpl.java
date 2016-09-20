package fi.softala.jee.aanestys.bean;

public class AanestysImpl implements Aanestys {
//git
	private int AanestysID;
	private String Tunnus;
	private String AanestysNimi;
	
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
	@Override
	public String toString() {
		return "AanestysImpl [AanestysID=" + AanestysID + ", Tunnus=" + Tunnus
				+ ", AanestysNimi=" + AanestysNimi + "]";
	}
	
	
}
