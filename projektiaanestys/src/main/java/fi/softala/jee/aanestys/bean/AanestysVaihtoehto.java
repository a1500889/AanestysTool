package fi.softala.jee.aanestys.bean;

public class AanestysVaihtoehto {
	private String valittuVaihtoehto;

	public AanestysVaihtoehto() {
		super();
	}

	public AanestysVaihtoehto(String valittuVaihtoehto) {
		super();
		this.valittuVaihtoehto = valittuVaihtoehto;
	}

	public String getValittuVaihtoehto() {
		return valittuVaihtoehto;
	}

	public void setValittuVaihtoehto(String valittuVaihtoehto) {
		this.valittuVaihtoehto = valittuVaihtoehto;
	}

	@Override
	public String toString() {
		return "AanestysVaihtoehto [valittuVaihtoehto=" + valittuVaihtoehto
				+ "]";
	}
	
	
	
}
