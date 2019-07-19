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

public class Acom_deleteAction extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileList2Action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session = request.getSession();
	
	long acom_no =Long.parseLong(request.getParameter("modal_acom_no"));


	try {
		
		ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
		
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
		
	
		//예약 내역삭제 후 취소 메시지*3 보내기
		profileDAO.deleteResrv_SendMsg(acom_no);
		
		//이미지 테이블 삭제
		profileDAO.deleteImg(acom_no);
			
		//북마크 삭제
		profileDAO.deleteBookmark(acom_no);
			
		//후기 삭제
		profileDAO.deleteReview_acom(user_id,acom_no);
		
		//숙소 삭제
		profileDAO.deleteAcomList(user_id, acom_no);
		
			
		
		int cnt = profileDAO.selectAcomCNT(user_id);
		logger.info(cnt+"");
		
		if(cnt==0) {
			profileDAO.change_Hostyn(user_id);
			
			session.setAttribute("host_yn", "n");
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "더이상 숙소 호스트가 아닙니다. 호스트가 되어보세요!");
			mav.addObject("url", "../become_host/introduce");
				
			return mav;
		}else {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "숙소정보가 삭제되었습니다");
			mav.addObject("url", "profilelist6");
		
			return mav;
		}
		
	} catch (Exception e) {
		ModelAndView mav = new ModelAndView("/result.jsp");
		mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
		mav.addObject("url", "javascript:history.go(-1);");
		return mav;
	} 
}

}
