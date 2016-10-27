package fi.softala.jee.aanestys.bean;

import java.util.ArrayList;

public class VaihtoehtoListaPOJO {
	
	private ArrayList<Vaihtoehto> Vehdot;
	
	public ArrayList<Vaihtoehto> getVaihtoehdot(){
		return Vehdot;
	}
	
	public void setVaihtoehdot(ArrayList<Vaihtoehto> v){
		this.Vehdot = v;
	}

}
