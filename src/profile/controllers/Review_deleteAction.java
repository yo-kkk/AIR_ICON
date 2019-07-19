package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class Review_deleteAction extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileList2Action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session = request.getSession();
	
	long review_no =Long.parseLong(request.getParameter("review_no"));


	try {
		
		ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
		
		String user_id = (String)session.getAttribute("user_id");
		
		
		
		
		
		
		if (profileDAO.deleteReview(user_id, review_no) != 1) {
			throw new RuntimeException("후기 번호 오류");
		}
		
		
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "후기가 삭제되었습니다");
			mav.addObject("url", "profilelist4");
		
			return mav;
		
		
	} catch (Exception e) {
		ModelAndView mav = new ModelAndView("/result.jsp");
		mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
		mav.addObject("url", "javascript:history.go(-1);");
		return mav;
	} 
}

}
