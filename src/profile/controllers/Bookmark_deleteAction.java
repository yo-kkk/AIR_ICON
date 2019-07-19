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
import java.sql.Date;

public class Bookmark_deleteAction extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileList2Action.class);
		

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		
		
		Long bookmark_no =Long.parseLong(request.getParameter("bookmark_no"));


		try {
			ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
			if (profileDAO.deleteBookmarkList((String)session.getAttribute("user_id"), bookmark_no) != 1) {
				throw new RuntimeException("즐겨찾기 번호 오류");
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "즐겨찾기가 삭제되었습니다");
			mav.addObject("url", "profilelist3");
			return mav;
			
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
			mav.addObject("url", "javascript:history.go(-1);");
			return mav;
		} 
	}
}
