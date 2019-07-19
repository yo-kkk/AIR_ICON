package become_host.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class Step01_action extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(Step01_action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String type = request.getParameter("type");
		long maxperson = Long.parseLong(request.getParameter("maxperson") );
		String address = request.getParameter("address");
		long price = Long.parseLong(request.getParameter("price") );
		
		Acom_infoDTO acom_infoDTO = (Acom_infoDTO) session.getAttribute("become_host");
		
		String host_id = (String) session.getAttribute("user_id");
			
		acom_infoDTO.setHost_id(host_id);
		acom_infoDTO.setType(type);
		acom_infoDTO.setMaxperson(maxperson);
		acom_infoDTO.setAddress(address);
		acom_infoDTO.setPrice(price);
		
		session.setAttribute("become_host", acom_infoDTO);
		
		return new ModelAndView("redirect:step02");
	}


}
