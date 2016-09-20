package fi.softala.jee.aanestys.control;

import java.io.IOException;
import java.util.List;

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
@RequestMapping (value="/vaihtoehdot")
public class VaihtoehtoControl {

	@Inject
	private VaihtoehtoDAO dao;
	
	public VaihtoehtoDAO getDao() {
		return dao;
	}

	public void setDao(VaihtoehtoDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="lista", method=RequestMethod.GET)
	public String getView(Model model) {
		List<Vaihtoehto> listaaVaihtoehdot = dao.lista();
		model.addAttribute("vaihtoehdot", listaaVaihtoehdot);
		return "vaihto/listaavEhdot";
	}
	
}
