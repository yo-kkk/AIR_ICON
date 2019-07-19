package search.controllers;

import java.util.List;

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

public class SearchRe extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Search.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		long pg = 1;
		int pageSize = 15;
		int blockSize = 10;

		String location;

		java.util.List<String> liststack = ArrayList();
		
		
		logger.info("===================");
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		logger.info(user_id);

		
		
		if(session.getAttribute("startday") != null) {
			session.removeAttribute("startday");
			session.removeAttribute("endday");
			session.removeAttribute("person");
		}
		
		//필터..
		if(session.getAttribute("location") == null) {
			logger.info("===================");
			
			location = request.getParameter("location");
		
			session.setAttribute("location", location);
			
			logger.info("===================");
			logger.info((String)session.getAttribute("location"));
			logger.info("===================");
		} else {
			logger.info("===================");
			
		
			//서치뷰에 new를 주자..
			if(request.getParameter("new").equals("new")) {
				session.removeAttribute("location");
				location = request.getParameter("location");
				session.setAttribute("location", location);
			} else { //notnew
				location = (String) session.getAttribute("location");
			}
		
			
		
			

			logger.info("===================");
		
		}
		
		
		try {
			pg = Long.parseLong(request.getParameter("pg"));
		} catch(Exception e) {}
			long startNum = (pg - 1) * pageSize + 1;
			long endNum = pg * pageSize;
			long totalCount = 0;
			long totalPage = 0;
			long startBlock = (pg-1) / blockSize * blockSize + 1;
			long endBlock = (pg-1) / blockSize * blockSize + blockSize;

		SearchDTO searchDTO = new SearchDTO();
		
		searchDTO.setLocation(location);
		
		logger.info(searchDTO.toString());
		
		logger.info("===================");
		logger.info((String)session.getAttribute("location"));
		logger.info("===================");
		

			
		try {
			PageDTO pageDTO = new PageDTO(startNum, endNum);
			SearchDAO searchDAO = SearchDAOImpl.getInstance();
			totalCount = searchDAO.getTotalCountRe(searchDTO);
			totalPage = totalCount / pageSize;
			if((totalCount % pageSize) != 0) totalPage++;
			startBlock = ((pg-1) / blockSize) * blockSize + 1;
			endBlock =   ((pg-1) / blockSize) * blockSize + blockSize;
			if (endBlock > totalPage) endBlock = totalPage;
			java.util.List<SearchListDTO> list = searchDAO.searchNewResult_no(pageDTO, searchDTO);
			
			if(list.get(0) != null) {
				ModelAndView mav = new ModelAndView("/WEB-INF/views/search/searchviewAll.jsp");
				mav.addObject("user_id", user_id);
				mav.addObject("list", list);
				mav.addObject("totalPage", totalPage);
				mav.addObject("totalCount", totalCount);
				mav.addObject("startBlock", startBlock);
				mav.addObject("endBlock", endBlock);
				
				return mav;
			} else {
				
				ModelAndView mav = new ModelAndView("/WEB-INF/views/search/failview.jsp");
				return mav;
			}
			

		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/WEB-INF/views/search/failview.jsp");
			return mav;
			
			
		}
			
			
		
			
		}

	private List<String> ArrayList() {
		// TODO Auto-generated method stub
		return null;
	}
		
	
	}


