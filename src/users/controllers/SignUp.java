package users.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class SignUp extends AbstractController {
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			ProfileDAO profileDAO = ProfileDAOImpl.getInstance();


			ModelAndView mav = new ModelAndView();
			mav.setViewName("/SignUp.jsp");
			
			
			
			return mav;
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "DB연결 오류입니다.");
			mav.addObject("url", "profilelist");
			return mav;
		}
	}
}
