package fi.softala.jee.aanestys.control;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

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
import org.springframework.web.servlet.view.RedirectView;

import fi.softala.jee.aanestys.bean.Aanestaja;
import fi.softala.jee.aanestys.bean.AanestajaImpl;
import fi.softala.jee.aanestys.bean.Aanestys;
import fi.softala.jee.aanestys.bean.AanestysImpl;
import fi.softala.jee.aanestys.bean.Aani;
import fi.softala.jee.aanestys.bean.AaniImpl;
import fi.softala.jee.aanestys.bean.EnvBean;
import fi.softala.jee.aanestys.bean.Excelreader;
import fi.softala.jee.aanestys.bean.Ryhma;
import fi.softala.jee.aanestys.bean.RyhmaImpl;
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
	// l�ytyy esimerkki EnvBean:ista.
	
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
	
	

	// HAKEE ANNETUT ��NET KANNASTA JA OHJAA NE .jsp SIVULLE.
	@RequestMapping(value = "listaa/{id}", method = RequestMethod.GET)
	public String getCreateForm(@PathVariable("id") int iidee, Model model) {

		//Hakee annetut ��net ��nestyksen ID:n perusteella.
		List<Aani> Annetutlista = adao.lista(iidee);
		ArrayList<String> AnnetutTxt = new ArrayList<String>();
		//Muuttaa saadun ��ni-objektiin pohjautuvan listan String-pohjaiseksi,
		//jotta tiettyjen ��nien m��r� voidaitiin laskea nopasti ja helposti my�hemmin 
		//collections.frequency-toiminnolla.
		for (Aani x : Annetutlista) {
			AnnetutTxt.add(vdao.get(x.getVaihtoehtoID()).getVaihtoehtoNimi());
		}
		
		//Hakee vaihtoehdot tietokannasta.
		List<Vaihtoehto> vaihtoehdot;
		vaihtoehdot = vdao.haeVaihtoehdot(iidee);
	
		//Luo tuloslistan ja k�y l�pi vaihtoehdon kerrallaan,
		//k�yden l�pi kuinka monta kertaa se mainitaan ��nilistassa,
		//ts. katsoo kuinka monta ��nt� se on saanut,
		//laskien summan ja lis�ten sen v�liaikaiseen vaihtoehto/tulos-objektiin vaihtoehdon nimen ja ID:n kanssa,
		//joka tallennetaan .jsp-sivulle tulostettavaksi l�hetett�v��n listaan.
		int aanisumma = adao.haeAnnettujenAanienMaara(iidee);
		DecimalFormat df = new DecimalFormat("0.00");
		ArrayList<VaihtoehtoImpl> tulos = new ArrayList<VaihtoehtoImpl>();
		for (Vaihtoehto v : vaihtoehdot) {
			VaihtoehtoImpl temp = new VaihtoehtoImpl();
			temp.setVaihtoehtoNimi(v.getVaihtoehtoNimi());
			temp.setVaihtoehtoID(v.getVaihtoehtoID());
			int aania = Collections.frequency(AnnetutTxt, v.getVaihtoehtoNimi());
			temp.setAanlkm(aania);
			int hylatyt =adao.hylattyjenAanienMaara(iidee);
			String luku;
			if(aanisumma-hylatyt==0){
				luku = df.format(0);
			}else{
				luku = df.format((aania/((aanisumma*1.0)-hylatyt))*100);
				
			}
			temp.setAaniosuus(Double.parseDouble((luku).replace(",", ".")));
				tulos.add(temp);
			
			
		}
		
		Collections.sort(tulos, new Comparator<VaihtoehtoImpl>() {
			
	        public int compare(VaihtoehtoImpl v1, VaihtoehtoImpl v2) {
	            return v2.getAanlkm() - v1.getAanlkm(); 
	        }

	    });
		double oikeussumma = adao.haeLupaSumma(iidee);
		double aanestysprosentti = (aanisumma/oikeussumma)*100;
		String aanPr=df.format(aanestysprosentti);
		model.addAttribute("aanestysprosentti", aanPr.replace(",", "."));
		model.addAttribute("tuloslista", tulos);
		//Hakee itse��n ��nest�neiden h�pe�listan ja list�� sen.
		model.addAttribute("hapealista", adao.listaaKusimutterit());

		return "tulos/listaaAanet";
	}

	// OTTAA ��NESTETT�V�N VAIHTOEHDON VASTAAN JA OHJAA ETEENP�IN
	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public RedirectView env(@ModelAttribute("envBean") EnvBean envBean, @ModelAttribute("KID") String kayttajaID, Model model, RedirectAttributes lahetett�v�t) {
		String matkalippu = null;
		Aani a = new AaniImpl();
		int vaihtoehtoID = Integer.parseInt(envBean.getEnv());
		a.setVaihtoehtoID(vaihtoehtoID);
		a.setAanestysID(vdao.get(vaihtoehtoID).getAanestysID());
		if(!aadao.tarkistaAanestysoikeus(Integer.parseInt(kayttajaID), a.getAanestysID())){
			System.out.println("main:"+kayttajaID);
			adao.insert(a, Integer.parseInt(kayttajaID));
			int AanestID = vdao.get(vaihtoehtoID).getAanestysID();
			matkalippu = "listaa/"+AanestID+"";
		}else{
			lahetett�v�t.addFlashAttribute("alert","Ei oikeutta ��nest��.");
			lahetett�v�t.addFlashAttribute("envBean", new EnvBean(Integer.toString(a.getAanestysID())));
			matkalippu= "tunnistus";
		}
		
		return new RedirectView(matkalippu);

	}
	
	//HAKEE KANNASTA VAIHTOEHDOT JA LISTAA NE K�YTT�J�LLE
	//EnvBean toimii backup beanina, ei tarvitse kiinnitt�� huomiota.
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public String getView(@ModelAttribute("envBean") EnvBean envBean, Model model, 
			@ModelAttribute("KID") int kayttajaID) {
		List<Vaihtoehto> listaaVaihtoehdot = vdao.haeVaihtoehdot(Integer.parseInt(envBean.getEnv()));
		model.addAttribute("vaihtoehto", new VaihtoehtoImpl());
		model.addAttribute("KID", kayttajaID);
		model.addAttribute("vaihtoehdot", listaaVaihtoehdot);
		return "vaihto/listaavEhdot";
	}

	// tallettaa tiedot tietokantaan
	@RequestMapping(value = "/saveAanestys", method = RequestMethod.POST)
	public ModelAndView saveAanestys(@ModelAttribute AanestysImpl aanestys) {
		edao.saveOrUpdate(aanestys);
		return new ModelAndView("redirect:/Main/admin");
	}

	// Luo ��nestysformin
	@RequestMapping(value = "/newAanestys", method = RequestMethod.GET)
	public ModelAndView newAanestys(ModelAndView model) {
		Aanestys newAanestys = new AanestysImpl();
		model.addObject("aanestys", newAanestys);
		model.setViewName("tulos/aanestysForm");
		return model;
	}

	

	// Luo ��nest�j�lis�ysformin
	
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
		return new ModelAndView("redirect:/Main/admin");
	}
	
	//LISTAA ��NEST�J�T
	@RequestMapping(value = "aanestajat", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("aanestajat", aadao.lista());
		model.addAttribute("aanestykset", edao.lista());
		model.addAttribute("ryhmat", aadao.haeRyhmat());
		EnvBean envBean = new EnvBean();
		model.addAttribute(envBean);
		
		return "vaihto/aanestajat";
	}
	
	//LISTAA ��NESTYKSET ADMINILLE
	@RequestMapping(value = "aanestys", method = RequestMethod.GET)
	public String getAanestykset(Model model) {
		List<Aanestys> listaaAanestys = edao.lista();
		model.addAttribute("aanestykset", listaaAanestys);
		EnvBean envBean = new EnvBean();
		model.addAttribute(envBean);
		return "vaihto/aanestykset";
	}
	
	//LISTAA ��NESTYKSET ��NEST�J�LLE
	@RequestMapping(value = "aanestys1", method = RequestMethod.GET)
	public String getAanestykset1(Model model) {
		List<Aanestys> listaaAanestys = edao.lista();
		model.addAttribute("aanestykset", listaaAanestys);
		EnvBean envBean = new EnvBean();
		model.addAttribute(envBean);
		return "vaihto/listaanestykset";
	}
		
	//POISTAA ��NESTYKSEN
	@RequestMapping(value = "/aanestyspoisto", method = RequestMethod.GET)
	public String poista(@ModelAttribute("envBean") EnvBean envBean) {
		int ID = Integer.parseInt(envBean.getEnv());
		//Poistaa ��nestyksen ��net.
		adao.delete(ID);
		//Poistaa ��nestyksen vaihtoehdot.
		vdao.deletet(ID);
		//Poistaa oikeudet poistettavaan ��nestykseen.
		edao.poistaLuvatAanestys(ID);
		//Poistaa itse ��nestyksen.
		edao.delete(ID);
		return "redirect:/Main/admin";
	}
	
	
	//VAIHTOEHTOJEN LIS��MINEN ��NESTYKSEEN: OSA 1
	//Hakee ��nestykset, jotta voi valita mihin lis�� vaihtoehtoja.
	@RequestMapping(value="/lisaavaihtoehdot", method = RequestMethod.GET)
	public String lisaaVaihtoehdot(Model model){
		List<Aanestys> aanestykset = edao.lista();
		List<Ryhma> ryhmat = aadao.haeRyhmat();
		model.addAttribute("aanestykset", aanestykset);
		model.addAttribute("ryhmat", ryhmat);
		Vaihtoehto v = new VaihtoehtoImpl();
		model.addAttribute("vaihtoehto", v);
		
		return "vaihto/VaihtoehtoForm";
	}
	
	//VAIHTOEHTOJEN LIS��MINEN ��NESTYKSEEN: OSA 2
	//Hakee valitun ��nestyksen ID:n ja annetut vaihtoehdot (String),
	//tallentaen vaihtoehdot yksi kerrallaan tietokantaan.
	@RequestMapping(value="/lisaavaihtoehdot", method = RequestMethod.POST)
	public String tallennavEhdot(@ModelAttribute VaihtoehtoImpl vaihtoehto, RedirectAttributes viesti) {
		
		if(vaihtoehto.getVaihtoehtoNimi().isEmpty()){
			viesti.addFlashAttribute("viestivari", "red");
			viesti.addFlashAttribute("alert", "Lis�� vaihtoehdolle nimi.");
		}else if(vaihtoehto.getRyhmaTunnus().isEmpty()){
			viesti.addFlashAttribute("viestivari", "red");
			viesti.addFlashAttribute("alert", "Lis�� vaihtoehdolle ryhm�tunnus.");
		}else{
			viesti.addFlashAttribute("viestivari", "green");
			viesti.addFlashAttribute("alert", "Vaihtoehto lis�tty!");
			vdao.insert(vaihtoehto);
		}
		
		return "redirect:lisaavaihtoehdot";
	}
	
	@RequestMapping(value="/tunnistus", method = RequestMethod.GET)
	public String tunnistusGet(@ModelAttribute("envBean") EnvBean envBean, Model model){
		model.addAttribute("iidee", Integer.parseInt(envBean.getEnv()));
		return "vaihto/tunnistus";
	}
	
	@RequestMapping(value="/tunnistus", method = RequestMethod.POST)
	public RedirectView tunnistusPost(@ModelAttribute("envBean") EnvBean envBean, @RequestParam("iidee") int id, 
			@RequestParam("etunimi") String etunimi, @RequestParam("sukunimi") String sukunimi, @RequestParam(value="rTun", required=false) String RyhTun, Model model, RedirectAttributes lahetett�v�t){
		String menosuunta = "asdf";
		if(RyhTun.isEmpty()){
			RyhTun="��nest�j�";
		}
		List<String> nimet =aadao.listaaLuvalliset(id);
		String nimi = etunimi+" "+sukunimi+" "+RyhTun;
		nimi = nimi.toLowerCase();
		
		if(nimet.contains(nimi)){
			AaniImpl p = new AaniImpl();
			p.setAanestysID(id);
			int Kaytt�j�ID = aadao.haeVapaaAanestajaID(p, etunimi, sukunimi, RyhTun);
			lahetett�v�t.addFlashAttribute("envBean", new EnvBean(Integer.toString(id)));
			lahetett�v�t.addFlashAttribute("KID", Kaytt�j�ID);
			menosuunta="lista";
		}else{
			lahetett�v�t.addFlashAttribute("alert","Ei oikeutta ��nest��.");
			lahetett�v�t.addFlashAttribute("envBean", new EnvBean(Integer.toString(id)));
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
	
	@RequestMapping(value = "/saveExcelAanestaja", method = RequestMethod.GET)
	public <Lista> ModelAndView saveExcelAanestaja(ModelAndView model, ArrayList<AanestajaImpl> Lista) throws IOException {
		Lista = Excelreader.lueExcel();
		
		for(int i = 0; i<Lista.size(); i++){
			AanestajaImpl newAanestaja = new AanestajaImpl();
			newAanestaja.setEtunimi(Lista.get(i).getEtunimi());
			newAanestaja.setSukunimi(Lista.get(i).getSukunimi());
			aadao.insert(newAanestaja);
			
		}
		
		return new ModelAndView("Admin/admin");
	}
	
	//��NESTYSOIKEUKSIEN LIS��MINEN K�YTT�JILLE
	//K�YTT�JIEN LIS��MINEN RYHM��N
	@RequestMapping(value="/lisaaOikeudet", method= RequestMethod.POST)
	public String listaaAanestVal(@RequestParam("lahetysnappi") String nappivalinta, @RequestParam(value="valRyhma", required=false) int ryhmavalinta, @RequestParam("valAanestaja") int[] aanestajalista, @RequestParam(value="valAanestykset", required=false) int[] aanestyslista , RedirectAttributes viesti){
		if(nappivalinta.equals("oikeus")){
			if(aanestyslista.length>0){
				viesti.addFlashAttribute("viestivari","green");
				viesti.addFlashAttribute("alert","Oikeutta jaettu.");
				aadao.lisaaAanestysOikeudet(aanestajalista, aanestyslista);
			}else{
				viesti.addFlashAttribute("viestivari","red");
				viesti.addFlashAttribute("alert","Valitse ��nestyksi�.");
			}		
			
		}else if(nappivalinta.equals("ryhma")){
			aadao.lisaaRyhmiin(ryhmavalinta, aanestajalista);
			viesti.addFlashAttribute("viestivari","green");
			viesti.addFlashAttribute("alert","Valitut lis�tty ryhmiin.");
			
		}else if(nappivalinta.equals("poisto")){	
			for (int i = 0; i < aanestajalista.length; i++) {
				aadao.poistaLuvatAanestaja(aanestajalista[i]);
				aadao.delete(aanestajalista[i]);
			}
			viesti.addFlashAttribute("viestivari","green");	
			viesti.addFlashAttribute("alert","��nest�j�(t) poistettu.");
			
		}else{
			viesti.addFlashAttribute("viestivari","red");	
			viesti.addFlashAttribute("alert","Jokin meni vikaan");	
		}
		
		return "redirect:aanestajat";
	}
	
	@RequestMapping(value = "/aanestajapoisto", method = RequestMethod.POST)
	public String poistaAanestaja(@ModelAttribute("envBean") EnvBean envBean) {
		int ID = Integer.parseInt(envBean.getEnv());
		//Poistaa oikeudet poistettavaan ��nestykseen.
		aadao.poistaLuvatAanestaja(ID);
		//Poistaa ��nest�j�n.
		aadao.delete(ID);
		return "redirect:aanestajat";
	}
	
	@RequestMapping(value = "/newRyhma", method = RequestMethod.GET)
	public ModelAndView luoRyhma(ModelAndView model) {
		model.addObject("Ryhma", new RyhmaImpl());
		model.setViewName("Aanestajat/RyhmaForm");
		return model;
	}
	
	// Tarkistaa, ettei ryhm�n nime� ja tunnusta ole varattu, tallentaen tiedot tietokantaan. 
	@RequestMapping(value = "/saveRyhma", method = RequestMethod.POST)
	public ModelAndView saveRyhma(@ModelAttribute RyhmaImpl ryhma, RedirectAttributes viesti) {
		List<Ryhma> ryhmalista = aadao.haeRyhmat();
		boolean onkoNimiTunnusVapaa = true;
		for (int i = 0; i < ryhmalista.size(); i++) {
			if(ryhmalista.get(i).getRyhmaNimi().equals(ryhma.getRyhmaNimi())){
				viesti.addFlashAttribute("viestivari", "red");
				viesti.addFlashAttribute("alert", "Ryhm�n nimi on jo k�yt�ss�!");
				onkoNimiTunnusVapaa=false;
			}else if(ryhmalista.get(i).getRyhmaTunnus().equals(ryhma.getRyhmaTunnus())){
				viesti.addFlashAttribute("viestivari", "red");
				viesti.addFlashAttribute("alert", "Ryhm�n tunnus on jo k�yt�ss�!");
				onkoNimiTunnusVapaa=false;
			}
		}
		
		if(onkoNimiTunnusVapaa){
			viesti.addFlashAttribute("viestivari", "green");
			viesti.addFlashAttribute("alert", "Ryhm� tallennettu.");
			aadao.lisaaRyhma(ryhma);
		}
				
			return new ModelAndView("redirect:newRyhma");
	}
	
	@RequestMapping(value = "ryhmat", method = RequestMethod.GET)
	public String listaRyhmat(Model model) {
		model.addAttribute("ryhmat", aadao.haeRyhmat());
		EnvBean envBean = new EnvBean();
		model.addAttribute(envBean);
		
		return "vaihto/ryhmat";
	}
	
	@RequestMapping(value = "ryhmapoisto", method = RequestMethod.POST)
	public String poistaRyhma(Model model, @ModelAttribute("envBean") EnvBean envBean, RedirectAttributes viesti) {
		aadao.poistaRyhma(Integer.parseInt(envBean.getEnv()));
		model.addAttribute("ryhmat", aadao.haeRyhmat());
		EnvBean envBean2 = new EnvBean();
		model.addAttribute(envBean2);
		viesti.addFlashAttribute("alert","Ryhm� poistettu.");
		viesti.addFlashAttribute("viestivari", "red");
		return "vaihto/ryhmat";
	}
	
	
 
	@RequestMapping(value="/loginpage", method = RequestMethod.GET)
	public String login(Model model) {
 
		return "login";
 
	}
 
	@RequestMapping(value="/loginfail", method = RequestMethod.GET)
	public String loginerror(Model model) {
 
		model.addAttribute("loginerror", "true");
		return "login";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Model model) {

		model.addAttribute("loggedout", "true");
		return "login";
 
	}
	
	 String path = "/error";
	 
	 @RequestMapping(value="/400")
	 public String error400(){
	  System.out.println("custom error handler");
	  return path;
	 }
	 
	 @RequestMapping(value="/404")
	 public String error404(){
	  System.out.println("custom error handler");
	  return path;
	 }
	 
	 @RequestMapping(value="/500")
	 public String error500(){
	  System.out.println("custom error handler");
	  return path;
	 }
	 
	 
	}
	

