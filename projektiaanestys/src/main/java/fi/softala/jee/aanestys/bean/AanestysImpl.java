package fi.softala.jee.aanestys.bean;

import java.util.ArrayList;

public class AanestysImpl implements Aanestys {
//git
	private int AanestysID;
	private String Tunnus;
	private String AanestysNimi;
	private String Kuvaus;
	private ArrayList<Vaihtoehto> AanVaihtoehdot;
	
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
	
	public ArrayList<Vaihtoehto> getAanVaihtoehdot() {
		return AanVaihtoehdot;
	}
	
	public void setAanVaihtoehdot(
			ArrayList<Vaihtoehto> AanVaihtoehdot) {
		  this.AanVaihtoehdot = AanVaihtoehdot;

	}
	public void addAanVaihtoehto(Vaihtoehto UusiVaihtoehto) {
		this.AanVaihtoehdot.add(UusiVaihtoehto);

	}
	
	@Override
	public String toString() {
		return "AanestysImpl [AanestysID=" + AanestysID + ", Tunnus=" + Tunnus
				+ ", AanestysNimi=" + AanestysNimi + ", Kuvaus=" + Kuvaus + "]";
	}
	
	
	
}
