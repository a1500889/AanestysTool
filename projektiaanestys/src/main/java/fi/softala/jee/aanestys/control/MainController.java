package fi.softala.jee.aanestys.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.AanestajaImpl;
import fi.softala.jee.aanestys.bean.Aanestys;
import fi.softala.jee.aanestys.bean.AanestysImpl;
import fi.softala.jee.aanestys.bean.AanestysVaihtoehto;
import fi.softala.jee.aanestys.bean.Aani;
import fi.softala.jee.aanestys.bean.AaniImpl;
import fi.softala.jee.aanestys.bean.EnvBean;
import fi.softala.jee.aanestys.bean.Vaihtoehto;
import fi.softala.jee.aanestys.bean.VaihtoehtoImpl;
import fi.softala.jee.aanestys.bean.VaihtoehtoListaPOJO;
import fi.softala.jee.aanestys.dao.AanestajaDAO;
import fi.softala.jee.aanestys.dao.AaniDAO;
import fi.softala.jee.aanestys.dao.AanestysDAO;
import fi.softala.jee.aanestys.dao.AanestysDAOImpl;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAO;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAOImpl;

@Controller
@SessionAttributes("AanestysID")
@RequestMapping(value = "/Main")
public class MainController {
	// http://stackoverflow.com/questions/21028954/radio-button-selection-and-its-value-in-spring-mvc
	// löytyy esimerkki EnvBean:ista.
	
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
	
	@Inject
	private AanestajaDAO aadao;
	
	public void setDAO (AanestajaDAO aadao){
		this.aadao = aadao;
	}

	// HAKEE ANNETUT ÄÄNET KANNASTA JA OHJAA NE .jsp SIVULLE.
	@RequestMapping(value = "listaa", method = RequestMethod.GET)
	public String getCreateForm(@ModelAttribute("envBean") EnvBean envBean, @ModelAttribute(value="env") String iidee, Model model) {
		
		
		List<Aani> Annetutlista;
		Annetutlista=adao.lista(Integer.parseInt(iidee));
		ArrayList<String> AnnetutTxt = new ArrayList<String>();
		
		for (Aani x : Annetutlista) {
			AnnetutTxt.add(vdao.get(x.getVaihtoehtoID()).getVaihtoehtoNimi());
		}
		
		List<Vaihtoehto> vaihtoehdot;
		vaihtoehdot = vdao.lista(Integer.parseInt(iidee));

		
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

	// OTTAA ÄÄNESTETTÄVÄN VAIHTOEHDON VASTAAN JA OHJAA ETEENPÄIN
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public String env(@ModelAttribute("envBean") EnvBean envBean, Model model) {
		Aani a = new AaniImpl();
		int vaihtoehtoID = Integer.parseInt(envBean.getEnv());
		a.setVaihtoehtoID(vaihtoehtoID);
		a.setAanestysID(vdao.get(vaihtoehtoID).getAanestysID());
		adao.insert(a);
		int AanestID = vdao.get(vaihtoehtoID).getAanestysID();
		model.addAttribute("AanestysID", AanestID);
		
		return "redirect:listaa";

	}

	
	//HAKEE KANNASTA VAIHTOEHDOT JA LISTAA NE KÄYTTÄJÄLLE
			//EnvBean toimii backup beanina, ei tarvitse kiinnittää huomiota.
			@RequestMapping(value = "lista", method = RequestMethod.GET)
			public String getView(@ModelAttribute("envBean") EnvBean envBean, Model model) {
				List<Vaihtoehto> listaaVaihtoehdot = vdao.lista(Integer.parseInt(envBean.getEnv()));
				model.addAttribute("vaihtoehdot", listaaVaihtoehdot);
				//model.addAttribute(envBean);
				return "vaihto/listaavEhdot";
		}

	// tallettaa tiedot tietokantaan
	@RequestMapping(value = "/saveAanestys", method = RequestMethod.POST)
	public ModelAndView saveAanestys(@ModelAttribute AanestysImpl aanestys) {
		edao.saveOrUpdate(aanestys);
		return new ModelAndView("redirect:/");
	}

	// Luo äänestysformin
	@RequestMapping(value = "/newAanestys", method = RequestMethod.GET)
	public ModelAndView newAanestys(ModelAndView model) {
		Aanestys newAanestys = new AanestysImpl();
		model.addObject("aanestys", newAanestys);
		model.setViewName("tulos/aanestysForm");
		return model;
	}

	

	// Luo äänestäjälisäysformin
	
	@RequestMapping(value = "/newAanestaja", method = RequestMethod.GET)
	public ModelAndView newAanestaja(ModelAndView model) {
		Aanestaja newAanestaja = new AanestajaImpl();
		model.addObject("aanestaja", newAanestaja);
		model.setViewName("Aanestajat/LisaaForm");
		return model;
	}

	// tallettaa tiedot tietokantaan
	@RequestMapping(value = "/saveAanestaja", method = RequestMethod.POST)
	public ModelAndView saveAanestaja(@ModelAttribute AanestajaImpl aanestaja) {
		aadao.insert(aanestaja);
		return new ModelAndView("redirect:/");
	}
	
	//LISTAA ÄÄNESTÄJÄT
	@RequestMapping(value = "aanestajat", method = RequestMethod.GET)
	public String lista(Model model) {
		List<Aanestaja> listaaAanestajat = aadao.lista();
		model.addAttribute("aanestajat", listaaAanestajat);
		EnvBean envBean = new EnvBean();
		model.addAttribute(envBean);
		
		return "vaihto/aanestajat";
	}
	
	//LISTAA ÄÄNESTYKSET ADMINILLE
	@RequestMapping(value = "aanestys", method = RequestMethod.GET)
					public String getAanestykset(Model model) {
						List<Aanestys> listaaAanestys = edao.lista();
						model.addAttribute("aanestykset", listaaAanestys);
						EnvBean envBean = new EnvBean();
						model.addAttribute(envBean);
						return "vaihto/aanestykset";
	}
	
	//LISTAA ÄÄNESTYKSET ÄÄNESTÄJÄLLE
		@RequestMapping(value = "aanestys1", method = RequestMethod.GET)
						public String getAanestykset1(Model model) {
							List<Aanestys> listaaAanestys = edao.lista();
							model.addAttribute("aanestykset", listaaAanestys);
							EnvBean envBean = new EnvBean();
							model.addAttribute(envBean);
							return "vaihto/listaanestykset";
		}
		
	//POISTAA ÄÄNESTYKSEN
	@RequestMapping(value = "/aanestyspoisto", method = RequestMethod.GET)
	public String poista(@ModelAttribute("envBean") EnvBean envBean) {
//		int tunnus = model.
//		System.out.println("ping");
		int ID = Integer.parseInt(envBean.getEnv());
		//Poistaa äänestyksen vaihtoehdot.
		vdao.deletet(ID);
		//Poistaa äänestyksen äänet.
		adao.delete(ID);
		//Poistaa oikeudet poistettavaan äänestykseen.
		edao.poistaLuvatAanestys(ID);
		//Poistaa itse äänestyksen.
		edao.delete(ID);
		return "redirect:/";
	}
	
	@RequestMapping(value="/lisaavaihtoehdot", method = RequestMethod.GET)
	public String lisaaVaihtoehdot(Model model){
		List<Aanestys> aanestykset = edao.lista();
		model.addAttribute("aanestykset", aanestykset);
		EnvBean envBean = new EnvBean();
		model.addAttribute(envBean);
		
		return "vaihto/VaihtoehtoForm";
	}
	
	@RequestMapping(value="/lisaavaihtoehdot", method = RequestMethod.POST)
	public String tallennavEhdot(@RequestParam("vaihtoehtoNimet") String[] uudetVaihtoehdot, EnvBean envBean, VaihtoehtoImpl temp) {
		
		for (int i = 0; i < uudetVaihtoehdot.length; i++) {
			temp.setAanestysID(Integer.parseInt(envBean.getEnv()));
			temp.setVaihtoehtoNimi(uudetVaihtoehdot[i]);
			temp.setVaihtoehtoID(25);
			vdao.insert(temp);
		}

		
		
		
		return "vaihto/VaihtoehtoForm";
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String admin (Model model){
		return "Admin/admin";
	}

}
