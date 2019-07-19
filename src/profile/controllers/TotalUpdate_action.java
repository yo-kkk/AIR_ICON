package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class TotalUpdate_action extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(TotalUpdate_action.class);

	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		Acom_infoDTO acom_infoUpdateDTO = (Acom_infoDTO) session.getAttribute("become_host_update");
		session.setAttribute("become_host_update", null);
		
		logger.info(acom_infoUpdateDTO.toString());
		
		
		
		try {
			
			
			ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
			if (profileDAO.update_Acom(acom_infoUpdateDTO, acom_infoUpdateDTO.getAcom_no()) != 1) {
				throw new RuntimeException("입력 오류");
			}
			
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "숙소정보가 수정되었습니다");
			mav.addObject("url", "profilelist6");
			return mav;

			
		}catch(Exception e) {
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", "수정에 실패했습니다.");
			mav.addObject("url", "javascript:history.back()");
			
			return mav;
		}
	}
	

}
