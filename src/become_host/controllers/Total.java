package become_host.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class Total extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Total.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Acom_infoDTO acom_infoDTO = (Acom_infoDTO) session.getAttribute("become_host");
		
		if(acom_infoDTO.getHost_id()==null) {
			
			ModelAndView mav = new ModelAndView("/WEB-INF/views/become_host/result.jsp");
			mav.addObject("msg", "처음부터 작성해야 합니다.");
			mav.addObject("url", "step01");
			
			return mav;
			
		}else {
			
			ModelAndView mav = new ModelAndView("/WEB-INF/views/become_host/total.jsp");
			mav.addObject("acom_infoDTO", acom_infoDTO);
			
			return mav;
			
		}
	}

}
