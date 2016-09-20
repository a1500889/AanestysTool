package fi.softala.jee.aanestys.control;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.softala.jee.aanestys.bean.Vaihtoehto;
import fi.softala.jee.aanestys.bean.VaihtoehtoImpl;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAO;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAOImpl;

@Controller
@RequestMapping (value="/Vaihtoehdot")
public class VaihtoehtoControl {

	@Inject
	private VaihtoehtoDAO dao;
	
	public VaihtoehtoDAO getDao() {
		return dao;
	}

	public void setDao(VaihtoehtoDAO dao) {
		this.dao = dao;
	}
	
	//FORMIN TEKEMINEN
	@RequestMapping(value="uusi", method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		Vaihtoehto tyhjaVaihtoehto = new VaihtoehtoImpl();
		tyhjaVaihtoehto.setVaihtoehtoNimi("vaihtoehtoNimi");
		System.out.println(dao.get(1).getVaihtoehtoNimi());
		
		model.addAttribute("Vaihtoehto", tyhjaVaihtoehto);
		return "createForm";
	}
	
	//FORMIN TIETOJEN VASTAANOTTO
	@RequestMapping(value="uusi", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="Vaihtoehto") VaihtoehtoImpl Vaihtoehto) {
		dao.insert(Vaihtoehto);
		return "redirect:/Vaihtoehdot/" + Vaihtoehto.getVaihtoehtoID();
	}
	
	//HENKILÖN TIETOJEN NÄYTTÄMINEN
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String getView(@PathVariable Integer id, Model model) {
		Vaihtoehto Vaihtoehto = dao.get(id);
		model.addAttribute("Vaihtoehto", Vaihtoehto);
		return "view";
	}
	
}
