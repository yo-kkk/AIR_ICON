package review.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import login.controllers.LoginAction;
import models.dto.ReviewDTO;
import review.models.ReviewImpl;

public class Review extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		long acom_no = Long.parseLong(request.getParameter("modal_acom_no"));
		String content = request.getParameter("content");
		float rate = Float.parseFloat(request.getParameter("rate"));
		long acom_reserv_no=Long.parseLong(request.getParameter("modal_acom_reserv_no"));
		
		String user_id = (String)session.getAttribute("user_id");
		
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setAcom_no(acom_no);
		reviewDTO.setUser_id(user_id);
		reviewDTO.setContent(content);
		reviewDTO.setRate(rate);
		reviewDTO.setAcom_reserv_no(acom_reserv_no);
		

		
		logger.info(reviewDTO.toString());
		
		try {
			ReviewImpl.getInstance().insertReview(reviewDTO, user_id);
			

			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/views/home/result.jsp");
			mav.addObject("msg", "작성이 완료되었습니다");
			mav.addObject("url", "../trip/trip_2");
			return mav;
		} catch(Exception e){
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
			//mav.addObject("url", "javascript:history.go(-1);");
			return mav;
		}
	}

}
