package fi.softala.jee.aanestys.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.softala.jee.aanestys.bean.Aani;
import fi.softala.jee.aanestys.bean.AaniImpl;
import fi.softala.jee.aanestys.bean.Vaihtoehto;
import fi.softala.jee.aanestys.bean.VaihtoehtoImpl;
import fi.softala.jee.aanestys.dao.AaniDAO;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAO;

@Controller
@RequestMapping (value="/Aanet")
public class AaniControl {

	@Inject
	private AaniDAO dao;
	
	public AaniDAO getDao() {
		return dao;
	}

	public void setDao(AaniDAO dao) {
		this.dao = dao;
	}
	
	@Inject
	private VaihtoehtoDAO Vdao;
	
	public VaihtoehtoDAO getVDao() {
		return Vdao;
	}

	public void setVDao(VaihtoehtoDAO vdao) {
		this.Vdao = vdao;
	}
	
	//FORMIN TEKEMINEN
	@RequestMapping(value="listaa", method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		List<Aani> Annetutlista = dao.lista();
		ArrayList<String> AnnetutTxt = new ArrayList<String>();
		
		for(Aani x : Annetutlista){
			AnnetutTxt.add(Vdao.get(x.getVaihtoehtoID()).getVaihtoehtoNimi());
		}
		
		List<Vaihtoehto> vaihtoehdot = Vdao.lista();
		
		
		ArrayList<VaihtoehtoImpl> tulos = new ArrayList<VaihtoehtoImpl>();
		
		for (Vaihtoehto v : vaihtoehdot) {
			VaihtoehtoImpl temp = new VaihtoehtoImpl();
			temp.setVaihtoehtoNimi(v.getVaihtoehtoNimi());
			temp.setVaihtoehtoID(v.getVaihtoehtoID());
			temp.setAanlkm(Collections.frequency(AnnetutTxt, v.getVaihtoehtoNimi()));
			tulos.add(temp);
		}
		
		model.addAttribute("tuloslista",tulos);
		
		return "tulos/listaaAanet";
	}
	
	//FORMIN TIETOJEN VASTAANOTTO
	@RequestMapping(value="uusi", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="Aani") AaniImpl Aani) {
		dao.insert(Aani);
		return "redirect:/Vaihtoehdot/" + Aani.getAaniID();
	}
	
	//HENKILÖN TIETOJEN NÄYTTÄMINEN
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Aani Aani = dao.get(id);
		model.addAttribute("Aani", Aani);
		return "view";
	}
	
}
