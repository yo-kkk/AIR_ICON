package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.UsersDTO;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class ProfileList2Action extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileList2Action.class);
		
	
	
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("location1").length()<4 ||
		   request.getParameter("location2").length()<4 || 
		   request.getParameter("location3").length()<4 || 
           request.getParameter("location4").length()<4 || 
		   request.getParameter("password").length()<2 || 
		   request.getParameter("card_cvc").length()<3) {
			
			
			logger.info("실패!");
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "카드번호가 올바르지 않습니다. 다시 입력해주세요.");
			mav.addObject("url", "profilelist2");
			return mav;
		}
		
		
		String card_num = request.getParameter("location1")+request.getParameter("location2")+request.getParameter("location3")+request.getParameter("location4");
		String card_date =request.getParameter("card_date");
		
		
		String card_cvc = request.getParameter("card_cvc");
		String card_password = request.getParameter("password");
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setCard_num(card_num);
		usersDTO.setCard_date(card_date);
		usersDTO.setCard_cvc(card_cvc);
		usersDTO.setCard_password(card_password);
		usersDTO.setCard_num_sub(card_num);
		
		logger.info(usersDTO.toString());

		try {
			ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
			if (profileDAO.updateAccount(usersDTO,(String)session.getAttribute("user_id")) != 1) {
				throw new RuntimeException("입력 오류");
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "카드정보가 저장되었습니다");
			mav.addObject("url", "profilelist2");
			return mav;
			
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
			mav.addObject("url", "javascript:history.go(-1);");
			return mav;
		} 
	}
}
