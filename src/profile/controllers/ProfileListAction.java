package profile.controllers;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.BookmarkDTO;
import models.dto.UsersDTO;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;
public class ProfileListAction extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileListAction.class);
	
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
		String password = DigestUtils.sha512Hex(request.getParameter("password"));
		String password_check = DigestUtils.sha512Hex(request.getParameter("passwordcheck"));
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		Date birth = Date.valueOf(request.getParameter("birth"));
		
		if(!(password.equals(password_check))){
		ModelAndView mav = new ModelAndView("/result.jsp");
		mav.addObject("msg", "비밀번호 확인이 일치하지 않습니다");
		mav.addObject("url", "javascript:history.go(-1);");
		
		return mav;
		}
		
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setName(name);
		usersDTO.setPassword(password);
		usersDTO.setTel(tel);
		usersDTO.setAddress(address);
		usersDTO.setEmail(email);
		usersDTO.setBirth(birth);
		
		
		logger.info(usersDTO.toString());

		try {
			ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
			if (profileDAO.updateProfile(usersDTO,(String)session.getAttribute("user_id")) != 1) {
				throw new RuntimeException("입력 오류");
			}
			
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "회원정보가 수정되었습니다");
			mav.addObject("url", "profilelist");
			return mav;
			
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
			mav.addObject("url", "javascript:history.go(-1);");
			return mav;
		} 
	}
	

}
