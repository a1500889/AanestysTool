package fi.softala.jee.aanestys.bean;

public class VaihtoehtoImpl implements Vaihtoehto{
//git
	private int vaihtoehtoID;
	private int AanestysID;
	private String VaihtoehtoNimi;
	public int aanlkm;
	public double aaniosuus;
	private String RyhmaTunnus;
	
	public VaihtoehtoImpl() {
		super();
	}
	public int getVaihtoehtoID() {
		return vaihtoehtoID;
	}
	public void setVaihtoehtoID(int vaihtoehtoID) {
		this.vaihtoehtoID = vaihtoehtoID;
	}
	public int getAanestysID() {
		return AanestysID;
	}
	public void setAanestysID(int aanestysID) {
		AanestysID = aanestysID;
	}
	public String getVaihtoehtoNimi() {
		return VaihtoehtoNimi;
	}
	public void setVaihtoehtoNimi(String vaihtoehtoNimi) {
		VaihtoehtoNimi = vaihtoehtoNimi;
	}
	
	public int getAanlkm() {
		return aanlkm;
	}
	public void setAanlkm(int aanlkm) {
		this.aanlkm = aanlkm;
	}
	
	public void setRyhmaTunnus(String RyhmaTunnus) {
		this.RyhmaTunnus=RyhmaTunnus;
	}
	public String getRyhmaTunnus() {
		return this.RyhmaTunnus;
	}
	
	@Override
	public String toString() {
		return "VaihtoehtoImpl [vaihtoehtoID=" + vaihtoehtoID + ", AanestysID="
				+ AanestysID + ", VaihtoehtoNimi=" + VaihtoehtoNimi + "]";
	}
	public void setAaniosuus(double anos) {
		this.aaniosuus=anos;
		
	}
	public double getAaniosuus() {
		return this.aaniosuus;
	}
	
	
	
}
