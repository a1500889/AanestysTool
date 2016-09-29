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
import org.springframework.web.servlet.ModelAndView;

import fi.softala.jee.aanestys.bean.Aanestys;
import fi.softala.jee.aanestys.bean.AanestysImpl;
import fi.softala.jee.aanestys.bean.Aani;
import fi.softala.jee.aanestys.bean.AaniImpl;
import fi.softala.jee.aanestys.bean.Vaihtoehto;
import fi.softala.jee.aanestys.bean.VaihtoehtoImpl;
import fi.softala.jee.aanestys.dao.AaniDAO;
import fi.softala.jee.aanestys.dao.AanestysDAO;
import fi.softala.jee.aanestys.dao.AanestysDAOImpl;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAO;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAOImpl;

@Controller
@RequestMapping(value = "/Main")
public class MainController {

	@Inject
	private AaniDAO adao;

	public AaniDAO getaDao() {
		return adao;
	}

	public void setDao(AaniDAO adao) {
		this.adao = adao;
	}

	@Inject
	private VaihtoehtoDAO vdao;

	public VaihtoehtoDAO getDao() {
		return vdao;
	}

	public void setDao(VaihtoehtoDAO vdao) {
		this.vdao = vdao;
	}

	@Inject
	private AanestysDAO edao;

	public void setDao(AanestysDAO edao) {
		this.edao = edao;
	}

	// Hakee annetut äänet kannasta ja ohjaa ne listaaAanet.jsp sivulle
	@RequestMapping(value = "listaa", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		List<Aani> Annetutlista = adao.lista();
		ArrayList<String> AnnetutTxt = new ArrayList<String>();

		for (Aani x : Annetutlista) {
			AnnetutTxt.add(vdao.get(x.getVaihtoehtoID()).getVaihtoehtoNimi());
		}

		List<Vaihtoehto> vaihtoehdot = vdao.lista();

		ArrayList<VaihtoehtoImpl> tulos = new ArrayList<VaihtoehtoImpl>();

		for (Vaihtoehto v : vaihtoehdot) {
			VaihtoehtoImpl temp = new VaihtoehtoImpl();
			temp.setVaihtoehtoNimi(v.getVaihtoehtoNimi());
			temp.setVaihtoehtoID(v.getVaihtoehtoID());
			temp.setAanlkm(Collections.frequency(AnnetutTxt,
					v.getVaihtoehtoNimi()));
			tulos.add(temp);
		}

		model.addAttribute("tuloslista", tulos);

		return "tulos/listaaAanet";
	}

	// // EI KÄYTÖSSÄ!!!!
	// @RequestMapping(value = "uusi", method = RequestMethod.POST)
	// public String create(@ModelAttribute(value = "Aani") AaniImpl Aani) {
	// dao.insert(Aani);
	// return "redirect:/Vaihtoehdot/" + Aani.getAaniID();
	// }

	// //EI KÄYTÖSSÄ
	// @RequestMapping(value = "{id}", method = RequestMethod.GET)
	// public String getView(@PathVariable Integer id, Model model) {
	// Aani Aani = dao.get(id);
	// model.addAttribute("Aani", Aani);
	// return "view";
	// }

	// vaihtoehto metodit

	// Hakee vaihtoehdot kannasta ja välittää ne listaavEhdot.jsp sivulle
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public String getView(Model model) {
		List<Vaihtoehto> listaaVaihtoehdot = vdao.lista();
		model.addAttribute("vaihtoehdot", listaaVaihtoehdot);
		return "vaihto/listaavEhdot";
	}

	// Hakee vaihtoehdot kannasta ja välittää ne listaavEhdot.jsp sivulle
	@RequestMapping(value = "aanestys", method = RequestMethod.GET)
	public String getAanestys(Model model) {
		List<Aanestys> listaaAanestys = edao.lista();
		model.addAttribute("aanestykset", listaaAanestys);
		return "aloitus";
	}
	// tallettaa tiedot tietokantaan
	@RequestMapping(value = "/saveAanestys", method = RequestMethod.POST)
	public ModelAndView saveAanestys(@ModelAttribute AanestysImpl aanestys) {
		edao.saveOrUpdate(aanestys);
		return new ModelAndView("redirect:/");
	}
	//Luo äänestysformin
	@RequestMapping(value = "/newAanestys", method = RequestMethod.GET)
	public ModelAndView newAanestys(ModelAndView model) {
	    Aanestys newAanestys = new AanestysImpl();
	    model.addObject("aanestys", newAanestys);
	    model.setViewName("tulos/aanestysForm");
	    return model;
	}

}
