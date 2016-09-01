package model;

public class Kirja {
	public String nimi;
	public String tekijatiedot;
	public String ISBN;
	public Kirja() {
		super();
		// TODO Auto-generated constructor stub
	}
	
public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

public String getTekijatiedot() {
		return tekijatiedot;
	}

public void setTekijatiedot(String tekijatiedot) {
		this.tekijatiedot = tekijatiedot;
	}

public String getISBN() {
		return ISBN;
	}

public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


public Kirja(String nimi, String tekijatiedot, String ISBN) {
	super();
	this.nimi= nimi;
	this.tekijatiedot=tekijatiedot;
	this.ISBN=ISBN;
	
}

}


