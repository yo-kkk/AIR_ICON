package become_host.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;

public class Introduce extends AbstractController{
	
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new ModelAndView("/WEB-INF/views/become_host/introduce.jsp");
	}
}
