package detail_acom.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;

public class Detail_acom2 extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Detail_acom2.class);
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		//String no = request.getParameter("no");//글번호를 얻어옴
		//String review_no = request.getParameter("review_no");
		
		try {
			
			logger.info("RuntimeException 안들어감");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/views/detail_acom/detail_acom.jsp");
			
			logger.info("mav 값 넘김");
			return mav;
			
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "게시물이 존재하지 않거나 DB연결 오류입니다.");
			mav.addObject("url","home");//없는 파일임 꼭 확인 해라
			
			return mav;
		}
		
	}

}
