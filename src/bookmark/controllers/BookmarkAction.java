package bookmark.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bookmark.models.BookmarkDAO;
import bookmark.models.BookmarkDAOImpl;
import front.controllers.AbstractController;
import front.controllers.ModelAndView;
public class BookmarkAction extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(BookmarkAction.class);
	
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		long acom_no = Long.parseLong(request.getParameter("acom_no"));
		String user_id = (String)session.getAttribute("user_id");
			
		
		
		try {
			
			BookmarkDAO bookmarkDAO = BookmarkDAOImpl.getInstance();
			ModelAndView mav = new ModelAndView();
			if(bookmarkDAO.confirmBookmark(acom_no,user_id)==1) {
			
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "이미 추가된 숙소입니다");
			mav.addObject("url", "javascript:history.go(-1);");
			
			return mav;}
			
			if (bookmarkDAO.bookmark_insert(acom_no,user_id) != 1) {
				throw new RuntimeException("숙소 번호 오류");
			}
			
				
				
				
				
				
				mav.setViewName("/result.jsp");
				mav.addObject("msg", "즐겨찾기 추가되었습니다");
				mav.addObject("url", "javascript:history.go(-1);");
					
				return mav;
		
			
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
			mav.addObject("url", "javascript:history.go(-1);");
			return mav;
		} 
	}
	

}
