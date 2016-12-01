package fi.softala.jee.aanestys.bean;

public interface Vaihtoehto {
//git
	public abstract int getVaihtoehtoID();

	public abstract void setVaihtoehtoID(int VaihtoehtoID);

	public abstract int getAanestysID();

	public abstract void setAanestysID(int AanestysID);

	public abstract String getVaihtoehtoNimi();

	public abstract void setVaihtoehtoNimi(String vaihtoehtoNimi);
	
	public abstract void setAanlkm(int lkm);
	
	public abstract int getAanlkm();
	
	public abstract void setRyhmaTunnus(String rhID);
	
	public abstract String getRyhmaTunnus();
	
	public abstract void setAaniosuus(double anos);
	
	public abstract double getAaniosuus();
	

}
