package bean;

public class VaihtoehtoImpl implements Vaihtoehto{

	private int vaihtoehtoID;
	private int AanestysID;
	private String VaihtoehtoNimi;
	
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
	@Override
	public String toString() {
		return "VaihtoehtoImpl [vaihtoehtoID=" + vaihtoehtoID + ", AanestysID="
				+ AanestysID + ", VaihtoehtoNimi=" + VaihtoehtoNimi + "]";
	}
	
	
	
}
