package fi.softala.jee.aanestys.bean;

import java.util.ArrayList;

public interface Aanestys {
//git
	
	
	public abstract int getAanestysID();

	public abstract void setAanestysID(int AanestysID);
	
	public abstract String getTunnus();

	public abstract void setTunnus(String Tunnus);
	
	public abstract String getAanestysNimi();

	public abstract void setAanestysNimi(String AanestysNimi);
	
	public abstract String getKuvaus();

	public abstract void setKuvaus(String Kuvaus);
	
	public abstract ArrayList<Vaihtoehto> getAanVaihtoehdot();
	
	public abstract void setAanVaihtoehdot(ArrayList<Vaihtoehto> AanVaihtoehdot);
	
	public abstract void addAanVaihtoehto(Vaihtoehto UusiVaihtoehto);
}
