package reservlist.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.PageDTO;
import models.dto.TripAcomListDTO;
import reservlist.models.Trip_impl;

public class Trip1 extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Trip1.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		long pg = 1;
		int pageSize = 10;
		int blockSize = 10;
		HttpSession session = request.getSession();
		logger.info("===================");
		try {
			pg = Long.parseLong(request.getParameter("pg"));
		} catch(Exception e) {}
			long startNum = (pg - 1) * pageSize + 1;
			long endNum = pg * pageSize;
			long totalCount = 0;
			long totalPage = 0;
			long startBlock = 0;
			long endBlock = 0;
		
		
		try {
			PageDTO pageDTO = new PageDTO(startNum, endNum);

			String col = " a.reserv_date_end >";
		    totalCount = Trip_impl.getInstance().getTotalCount(col,(String)session.getAttribute("user_id"));
		    totalPage = totalCount / pageSize;
			if (totalCount % pageSize != 0) totalPage++;
			startBlock = ((pg-1) / blockSize) * blockSize + 1;
			endBlock =   ((pg-1) / blockSize) * blockSize + blockSize;
			if (endBlock > totalPage) endBlock = totalPage;
			
			java.util.List<TripAcomListDTO> list = Trip_impl.getInstance().tripdata(pageDTO, (String)session.getAttribute("user_id"), col);				
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/views/reservlist/trip1.jsp");
			mav.addObject("list", list);
			mav.addObject("totalPage", totalPage);
			mav.addObject("totalCount", totalCount);
			mav.addObject("startBlock", startBlock);
			mav.addObject("endBlock", endBlock);
			
			return mav;
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "게시물 리스트 에러");
			mav.addObject("url", "../home/home");
			return mav;
		}
		
	}

}
