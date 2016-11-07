package fi.softala.jee.aanestys.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.AanestajaImpl;
import fi.softala.jee.aanestys.bean.Aanestys;
import fi.softala.jee.aanestys.bean.AanestysImpl;
import fi.softala.jee.aanestys.bean.Aani;
import fi.softala.jee.aanestys.bean.AaniImpl;
import fi.softala.jee.aanestys.bean.EnvBean;
import fi.softala.jee.aanestys.bean.Excelreader;
import fi.softala.jee.aanestys.bean.Vaihtoehto;
import fi.softala.jee.aanestys.bean.VaihtoehtoImpl;
import fi.softala.jee.aanestys.dao.AanestajaDAO;
import fi.softala.jee.aanestys.dao.AaniDAO;
import fi.softala.jee.aanestys.dao.AanestysDAO;
import fi.softala.jee.aanestys.dao.VaihtoehtoDAO;

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
	@RequestMapping(value = "listaa/{id}", method = RequestMethod.GET)
	public String getCreateForm(@PathVariable("id") int iidee, Model model) {

		//Hakee annetut äänet äänestyksen ID:n perusteella.
		List<Aani> Annetutlista = adao.lista(iidee);
		ArrayList<String> AnnetutTxt = new ArrayList<String>();
		//Muuttaa saadun Ääni-objektiin pohjautuvan listan String-pohjaiseksi,
		//jotta tiettyjen äänien määrä voidaitiin laskea nopasti ja helposti myöhemmin 
		//collections.frequency-toiminnolla.
		for (Aani x : Annetutlista) {
			AnnetutTxt.add(vdao.get(x.getVaihtoehtoID()).getVaihtoehtoNimi());
		}
		
		//Hakee vaihtoehdot tietokannasta.
		List<Vaihtoehto> vaihtoehdot;
		vaihtoehdot = vdao.haeVaihtoehdot(iidee);
	
		//Luo tuloslistan ja käy läpi vaihtoehdon kerrallaan,
		//käyden läpi kuinka monta kertaa se mainitaan äänilistassa,
		//ts. katsoo kuinka monta ääntä se on saanut,
		//laskien summan ja lisäten sen väliaikaiseen vaihtoehto/tulos-objektiin vaihtoehdon nimen ja ID:n kanssa,
		//joka tallennetaan .jsp-sivulle tulostettavaksi lähetettävään listaan.
		ArrayList<VaihtoehtoImpl> tulos = new ArrayList<VaihtoehtoImpl>();
		for (Vaihtoehto v : vaihtoehdot) {
			VaihtoehtoImpl temp = new VaihtoehtoImpl();
			temp.setVaihtoehtoNimi(v.getVaihtoehtoNimi());
			temp.setVaihtoehtoID(v.getVaihtoehtoID());
			temp.setAanlkm(Collections.frequency(AnnetutTxt, v.getVaihtoehtoNimi()));
			tulos.add(temp);
		}

		model.addAttribute("tuloslista", tulos);

		return "tulos/listaaAanet";
	}

	// OTTAA ÄÄNESTETTÄVÄN VAIHTOEHDON VASTAAN JA OHJAA ETEENPÄIN
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public String env(@ModelAttribute("envBean") EnvBean envBean,@ModelAttribute("etunimi") String etunimi,@ModelAttribute("sukunimi") String sukunimi, Model model) {
		Aani a = new AaniImpl();
		int vaihtoehtoID = Integer.parseInt(envBean.getEnv());
		a.setVaihtoehtoID(vaihtoehtoID);
		a.setAanestysID(vdao.get(vaihtoehtoID).getAanestysID());
		adao.insert(a, etunimi, sukunimi);
		int AanestID = vdao.get(vaihtoehtoID).getAanestysID();
		
		return "redirect:listaa/"+AanestID+"";

	}
	
	//HAKEE KANNASTA VAIHTOEHDOT JA LISTAA NE KÄYTTÄJÄLLE
	//EnvBean toimii backup beanina, ei tarvitse kiinnittää huomiota.
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public String getView(@ModelAttribute("envBean") EnvBean envBean, Model model, 
			@ModelAttribute("Aetunimi") String etunimi, @ModelAttribute("Asukunimi") String sukunimi) {
		List<Vaihtoehto> listaaVaihtoehdot = vdao.haeVaihtoehdot(Integer.parseInt(envBean.getEnv()));
		model.addAttribute("etunimi", etunimi);
		model.addAttribute("sukunimi", sukunimi);
		model.addAttribute("vaihtoehdot", listaaVaihtoehdot);
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
	
	//VAIHTOEHTOJEN LISÄÄMINEN ÄÄNESTYKSEEN: OSA 1
	//Hakee äänestykset, jotta voi valita mihin lisää vaihtoehtoja.
	@RequestMapping(value="/lisaavaihtoehdot", method = RequestMethod.GET)
	public String lisaaVaihtoehdot(Model model){
		List<Aanestys> aanestykset = edao.lista();
		model.addAttribute("aanestykset", aanestykset);
		EnvBean envBean = new EnvBean();
		model.addAttribute(envBean);
		
		return "vaihto/VaihtoehtoForm";
	}
	
	//VAIHTOEHTOJEN LISÄÄMINEN ÄÄNESTYKSEEN: OSA 2
	//Hakee valitun äänestyksen ID:n ja annetut vaihtoehdot (String),
	//tallentaen vaihtoehdot yksi kerrallaan tietokantaan.
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
	
	@RequestMapping(value="/bypass", method = RequestMethod.POST)
	public String ohitusredirect(@ModelAttribute("envBean") String iidee, Model model) {
		model.addAttribute("AanestysID", Integer.parseInt(iidee));
		
		return "redirect:listaa";
	}
	
	@RequestMapping(value="/tunnistus", method = RequestMethod.GET)
	public String tunnistusGet(@ModelAttribute("envBean") EnvBean envBean, Model model){
		model.addAttribute("iidee", Integer.parseInt(envBean.getEnv()));
		return "vaihto/tunnistus";
	}
	
	@RequestMapping(value="/tunnistus", method = RequestMethod.POST)
	public RedirectView tunnistusPost(@ModelAttribute("envBean") EnvBean envBean, @RequestParam("iidee") int id, 
			@RequestParam("etunimi") String etunimi, @RequestParam("sukunimi") String sukunimi, Model model, RedirectAttributes lahetettävät){
		String menosuunta = "asdf";
		List<String> nimet =aadao.listaaLuvalliset(id);
		String nimi = etunimi+" "+sukunimi;
		
		if(nimet.contains(nimi)){
			lahetettävät.addFlashAttribute("envBean", new EnvBean(Integer.toString(id)));
			lahetettävät.addFlashAttribute("Aetunimi", etunimi);
			lahetettävät.addFlashAttribute("Asukunimi", sukunimi);
			menosuunta="lista";
		}else{
			lahetettävät.addFlashAttribute("alert","Ei oikeutta äänestää.");
			lahetettävät.addFlashAttribute("envBean", new EnvBean(Integer.toString(id)));
			menosuunta= "tunnistus";
		}
		return new RedirectView(menosuunta);
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String admin (Model model){
		return "Admin/admin";
	}

	@RequestMapping(value = "/newExcelAanestaja", method = RequestMethod.GET)
	public ModelAndView newExcelAanestaja(ModelAndView model, ArrayList<AanestajaImpl> Lista) throws IOException {
		Lista = Excelreader.lueExcel();
		model.addObject("Lista", Lista);
		model.setViewName("Aanestajat/LisaaExcelista");
		return model;
	}
	

	
}
