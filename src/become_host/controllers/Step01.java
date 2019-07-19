package become_host.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class Step01 extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(Step01.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Acom_infoDTO acom_infoDTO = new Acom_infoDTO();
				
		if(session.getAttribute("become_host")==null) {
			
			session.setAttribute("become_host", acom_infoDTO );
			
		}else {
			acom_infoDTO = (Acom_infoDTO) session.getAttribute("become_host");
		}
		
		logger.info(acom_infoDTO.toString());
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/become_host/step01.jsp");
		mav.addObject("acom_infoDTO", acom_infoDTO);
		
		return mav;
	}

}
