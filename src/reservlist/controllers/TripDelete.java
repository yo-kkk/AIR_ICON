package reservlist.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import profile.controllers.ProfileList2Action;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;
import reservlist.models.Trip_impl;

public class TripDelete extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileList2Action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session = request.getSession();
	
	long acom_reserv_no =Long.parseLong(request.getParameter("modal_acom_no"));


	try {
		
		
		
		String user_id = (String)session.getAttribute("user_id");
		String passwd = (String)session.getAttribute("user_passwd");
		String passwdCheck = DigestUtils.sha512Hex(request.getParameter("passwd"));
		
		
		if(!(passwd.equals(passwdCheck))) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "비밀번호가 맞지 않습니다");
			mav.addObject("url", "javascript:history.go(-1);");
			return mav;
		}
		
		
		
		if(Trip_impl.getInstance().deleteTripReserv(user_id, acom_reserv_no)!=1){
			throw new RuntimeException("예약 번호 오류");
		}
		
		
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "예약이 취소되었습니다");
			mav.addObject("url", "../trip/trip_1");
		
			return mav;
		
		
	} catch (Exception e) {
		ModelAndView mav = new ModelAndView("/result.jsp");
		mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
		mav.addObject("url", "javascript:history.go(-1);");
		return mav;
	} 
}

}
