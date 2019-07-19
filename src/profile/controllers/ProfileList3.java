package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.PageDTO;
import profile.models.Bookmark_profileDTO;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class ProfileList3 extends AbstractController {
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		long pg = 1;
		int pageSize = 10;
		int blockSize= 10;
		
		
		try {
			pg = Long.parseLong(request.getParameter("pg"));
		} catch (Exception e) {	}
		long startNum = (pg - 1) * pageSize + 1;
		long endNum   = pg * pageSize;
		long totalCount = 0;			//전체 게시물 수
		long totalPage = 0;				//전체 페이지 수
		long startBlock= 0;				//페이지 블럭의 시작
		long endBlock  = 0;				//페이지 블럭의 끝
		try {
			PageDTO pageDTO = new PageDTO(startNum, endNum);
			
			ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
			
			if(session.getAttribute("user_id")!=null) {
			
			totalCount = profileDAO.getTotalCount_bookmark((String)session.getAttribute("user_id"));
			totalPage = totalCount / pageSize;
			if (totalCount % pageSize != 0) totalPage++;
			startBlock = ((pg-1) / blockSize) * blockSize + 1;
			endBlock =   ((pg-1) / blockSize) * blockSize + blockSize;
			if (endBlock > totalPage) endBlock = totalPage;
			
			java.util.List<Bookmark_profileDTO> bookmarkList = profileDAO.getBookmarkList(pageDTO, (String)session.getAttribute("user_id"));
			
		
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/views/profile/ProfileList3.jsp");
			mav.addObject("bookmarkList", bookmarkList);
			mav.addObject("totalPage", totalPage);
			mav.addObject("totalCount", totalCount);
			mav.addObject("startBlock", startBlock);
			mav.addObject("endBlock", endBlock);
			mav.addObject("pg", pg);
			mav.addObject("host_yn", (String) session.getAttribute("host_yn") );
			return mav;}
		
			else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "로그인 후 이용하세요");
			mav.addObject("url", "../home/home");
			return mav;
			
		}
			
		} 
		

	catch (Exception e) {
		
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/result.jsp");
		mav.addObject("msg", "DB연결 오류입니다.");
		mav.addObject("url", "profilelist");
		return mav;
		}
		
	}
}