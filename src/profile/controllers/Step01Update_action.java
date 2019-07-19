package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class Step01Update_action extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(Step01Update_action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String type = request.getParameter("type");
		long maxperson = Long.parseLong(request.getParameter("maxperson") );
		
		long price = Long.parseLong(request.getParameter("price") );
		
		Acom_infoDTO acom_infoUpdateDTO = (Acom_infoDTO) session.getAttribute("become_host_update");
		
		String host_id = (String) session.getAttribute("user_id");
			
		acom_infoUpdateDTO.setHost_id(host_id);
		acom_infoUpdateDTO.setType(type);
		acom_infoUpdateDTO.setMaxperson(maxperson);
		
		acom_infoUpdateDTO.setPrice(price);
		
		session.setAttribute("become_host_update", acom_infoUpdateDTO);
		
		return new ModelAndView("redirect:acom_update2");
	}


}
