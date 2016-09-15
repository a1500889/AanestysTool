package bean;

public class AaniImpl implements Aani {

	
	private int AaniID;
	private int AanestysID;
	private int VaihtoehtoID;
	
	public int getAaniID() {
		return AaniID;
	}
	public void setAaniID(int aaniID) {
		AaniID = aaniID;
	}
	public int getAanestysID() {
		return AanestysID;
	}
	public void setAanestysID(int aanestysID) {
		AanestysID = aanestysID;
	}
	public int getVaihtoehtoID() {
		return VaihtoehtoID;
	}
	public void setVaihtoehtoID(int vaihtoehtoID) {
		VaihtoehtoID = vaihtoehtoID;
	}
	public AaniImpl(int aaniID, int aanestysID, int vaihtoehtoID) {
		super();
		AaniID = aaniID;
		AanestysID = aanestysID;
		VaihtoehtoID = vaihtoehtoID;
	}
	
	
}
