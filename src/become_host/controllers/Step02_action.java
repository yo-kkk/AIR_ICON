package become_host.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class Step02_action extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Step02_action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Acom_infoDTO acom_infoDTO = (Acom_infoDTO) session.getAttribute("become_host");
		
		String intro_title = request.getParameter("intro_title");
		String intro_cont = request.getParameter("intro_cont");
		String intro_etc = request.getParameter("intro_etc");
		String intro_pubtrans = request.getParameter("intro_pubtrans");
		
		acom_infoDTO.setIntro_title(intro_title);
		acom_infoDTO.setIntro_cont(intro_cont);
		acom_infoDTO.setIntro_etc(intro_etc);
		acom_infoDTO.setIntro_pubtrans(intro_pubtrans);
		
		session.setAttribute("become_host", acom_infoDTO);
		
		return new ModelAndView("redirect:step03");
		
	}

}
