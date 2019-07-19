package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.UsersDTO;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class ProfileList extends AbstractController {
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		try {
			
			ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
			
			UsersDTO usersDTO = profileDAO.getUserInfo((String)session.getAttribute("user_id"));
			if (usersDTO == null) throw new RuntimeException();
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/views/profile/ProfileList.jsp");
			mav.addObject("usersDTO",usersDTO);
			mav.addObject("host_yn", (String) session.getAttribute("host_yn") );
			
			
			return mav;
		} catch (Exception e) {
			if(session.getAttribute("user_id")==null) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("/result.jsp");
				mav.addObject("msg", "로그인 후 이용하세요");
				mav.addObject("url", "../home/home");
				
				return mav;
				
			}
			
			else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "DB연결 오류입니다.");
			mav.addObject("url", "profilelist");
			return mav;}
		}
	}
}
