package users.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;

public class Logout extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		ModelAndView mav = new ModelAndView("/result.jsp");
		mav.addObject("msg", "로그아웃되었습니다!");		
		mav.addObject("url", "../home/home");
	
		return mav;
		
	}
	
	
}
