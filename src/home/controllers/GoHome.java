package home.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;

public class GoHome extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(GoHome.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("location") != null) {
			session.removeAttribute("startday");
			session.removeAttribute("endday");
			session.removeAttribute("person");
			session.removeAttribute("location");
		}
		

		
		
		if(session.getAttribute("user_id")==null) {
			session.setAttribute("user_id", null);
			
			ModelAndView mav = new ModelAndView("/WEB-INF/views/home/main_intro.jsp");
			mav.addObject("user_id", null);
			
			return mav;
			
		}else {
			String user_id = (String) session.getAttribute("user_id");
			
			ModelAndView mav = new ModelAndView("/WEB-INF/views/home/main_intro.jsp");
			mav.addObject("user_id", user_id);
			
			return mav;

		}

	}

}
