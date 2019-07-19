package search.controllers;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.PageDTO;
import models.dto.SearchDTO;
import models.dto.SearchListDTO;
import search.models.SearchDAO;
import search.models.SearchDAOImpl;

public class SearchFilter extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Search.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		long pg = 1;
		int pageSize = 15;
		int blockSize = 10;

		String location;
		Date startday;
		Date endday;
		int person;
		
		String[] checkFilter = null;
		String col = "";
		
		logger.info("===================");
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		logger.info(user_id);
		


		try {
			pg = Long.parseLong(request.getParameter("pg"));
		} catch(Exception e) {}
			long startNum = (pg - 1) * pageSize + 1;
			long endNum = pg * pageSize;
			long totalCount = 0;
			long totalPage = 0;
			long startBlock = (pg-1) / blockSize * blockSize + 1;
			long endBlock = (pg-1) / blockSize * blockSize + blockSize;

			

			
			logger.info("===================");
			logger.info((String)session.getAttribute("location"));
			logger.info("===================");
			
			logger.info(request.getParameterValues("inlineCeckbox1").toString());
			if(request.getParameterValues("inlineCeckbox1") != null) {
				checkFilter = request.getParameterValues("inlineCeckbox1");
				for(int i=0; i<checkFilter.length; i++) {
					col += " and " + checkFilter[i] + "='y'";
					
				}
				
			}
			
			
		if((String)session.getAttribute("col") != null) {
			session.removeAttribute("col");
		}
		
		logger.info("===날짜세션===="+String.valueOf(session.getAttribute("startday")));
		session.setAttribute("col", col);
			
		if(session.getAttribute("startday") == null) {

			
			location = (String) session.getAttribute("location");
			
			//체크박스 세션 주기 -> 페이징
			
			SearchDTO searchDTO = new SearchDTO();
			
			
			searchDTO.setLocation(location);
			
			
			
			logger.info(checkFilter[0]);
			logger.info(col);
				try {
					PageDTO pageDTO = new PageDTO(startNum, endNum);
					SearchDAO searchDAO = SearchDAOImpl.getInstance();
					totalCount = searchDAO.getTotalCountReFilter(searchDTO, (String)session.getAttribute("col"));
					totalPage = totalCount / pageSize;
					if((totalCount % pageSize) != 0) totalPage++;
					startBlock = ((pg-1) / blockSize) * blockSize + 1;
					endBlock =   ((pg-1) / blockSize) * blockSize + blockSize;
					if (endBlock > totalPage) endBlock = totalPage;
					java.util.List<SearchListDTO> list = searchDAO.searchNewResult_noFilter(pageDTO, searchDTO, (String)session.getAttribute("col"));
					
					if(list.get(0)!=null) {
						ModelAndView mav = new ModelAndView("/WEB-INF/views/search/searchview.jsp");
						mav.addObject("user_id", user_id);
						mav.addObject("list", list);
						mav.addObject("totalPage", totalPage);
						mav.addObject("totalCount", totalCount);
						mav.addObject("startBlock", startBlock);
						mav.addObject("endBlock", endBlock);
						return mav;
					} else {
						ModelAndView mav = new ModelAndView("/WEB-INF/views/search/searchview.jsp");
		
						mav.setViewName("/result.jsp");
						mav.addObject("msg", "검색결과가 없습니다");
						
						return mav;
					}

				} catch (Exception e) {
					ModelAndView mav = new ModelAndView("/WEB-INF/views/search/failview.jsp");
					
					return mav;
				}

		} else {
			
			startday = (Date) session.getAttribute("startday");
			endday = (Date) session.getAttribute("endday");
			person = (int) session.getAttribute("person");
			location = (String) session.getAttribute("location");
			
			//체크박스 세션 주기 -> 페이징
			logger.info(location+"메인페이징==============================");
			SearchDTO searchDTO = new SearchDTO();
			
			searchDTO.setStartday(startday);
			searchDTO.setEndday(endday);
			searchDTO.setPerson(person);
			searchDTO.setLocation(location);
			
			try {
				PageDTO pageDTO = new PageDTO(startNum, endNum);
				SearchDAO searchDAO = SearchDAOImpl.getInstance();
				totalCount = searchDAO.getTotalCountFilter(searchDTO, (String)session.getAttribute("col"));
				totalPage = totalCount / pageSize;
				if((totalCount % pageSize) != 0) totalPage++;
				startBlock = ((pg-1) / blockSize) * blockSize + 1;
				endBlock =   ((pg-1) / blockSize) * blockSize + blockSize;
				if (endBlock > totalPage) endBlock = totalPage;
				java.util.List<SearchListDTO> list = searchDAO.searchResultFilter(pageDTO, searchDTO, (String)session.getAttribute("col"));
				
				logger.info("메소드끝===================");
				logger.info("!!=====");
				//String.valueOf(list.get(0))
				if(list.get(0)!=null) {
					logger.info("ifnotnull====================");
					ModelAndView mav = new ModelAndView("/WEB-INF/views/search/searchview.jsp");
					mav.addObject("user_id", user_id);
					mav.addObject("list", list);
					mav.addObject("totalPage", totalPage);
					mav.addObject("totalCount", totalCount);
					mav.addObject("startBlock", startBlock);
					mav.addObject("endBlock", endBlock);
					logger.info("ifnotnull====================");
					return mav;
				} else {
					ModelAndView mav = new ModelAndView("/WEB-INF/views/search/failview.jsp");
					logger.info("else====================");
					return mav;
				}

			
			} catch (Exception e) {
				ModelAndView mav = new ModelAndView("/WEB-INF/views/search/failview.jsp");
				logger.info("catch=====");
				return mav;
			}
			
		}
		
		
		
		
	}

}
