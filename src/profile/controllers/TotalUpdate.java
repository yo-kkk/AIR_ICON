package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class TotalUpdate extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(TotalUpdate.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Acom_infoDTO acom_infoUpdateDTO = (Acom_infoDTO) session.getAttribute("become_host_update");
		
		if(acom_infoUpdateDTO.getHost_id()==null) {
			
			ModelAndView mav = new ModelAndView("/WEB-INF/views/become_host/result.jsp");
			mav.addObject("msg", "처음부터 작성해야 합니다.");
			mav.addObject("url", "acom_update");
			
			return mav;
			
			
		}else {
			
			ModelAndView mav = new ModelAndView("/WEB-INF/views/become_host_update/total_update.jsp");
			mav.addObject("acom_infoUpdateDTO", acom_infoUpdateDTO);
			
			return mav;
			
		}
	}

}
