package users.controllers;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.UsersDTO;
import users.models.UsersDAO;
import users.models.UsersDAOImpl;

public class SignUpAction extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(SignUpAction.class);
	
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		String user_id = request.getParameter("id").toLowerCase();
		String name = request.getParameter("name");
		String password = DigestUtils.sha512Hex(request.getParameter("password"));
		String password_check = DigestUtils.sha512Hex(request.getParameter("passwordcheck"));
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String gender = request.getParameter("chk_info");	
		Date birth = Date.valueOf(request.getParameter("birth"));
		
		
		UsersDAO usersDAO = UsersDAOImpl.getInstance();


		try {
			
		
			if(usersDAO.confirmId(user_id)==1) {
				ModelAndView mav = new ModelAndView("/result.jsp");
				mav.addObject("msg", "중복된 아이디가 있습니다");
				mav.addObject("url", "javascript:history.go(-1);");
				
				return mav;
				
			}
			
			if(!(password.equals(password_check))){
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", "비밀번호 확인이 일치하지 않습니다");
			mav.addObject("url", "javascript:history.go(-1);");
			
			return mav;
			}
			
			UsersDTO usersDTO = new UsersDTO();
			usersDTO.setUser_id(user_id);
			usersDTO.setName(name);
			usersDTO.setPassword(password);
			usersDTO.setTel(tel);
			usersDTO.setAddress(address);
			usersDTO.setEmail(email);
			usersDTO.setBirth(birth);
			usersDTO.setGender(gender);
			

			
			
			
			logger.info(usersDTO.toString());
		
			if (usersDAO.usersInsert(usersDTO) != 1) {
				
				throw new RuntimeException("입력 오류");
			}
			
			
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "회원가입이 완료되었습니다");
			mav.addObject("url", "../home/home");
			usersDAO.sendWelcomeMsg(user_id);
			return mav;
			
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
			mav.addObject("url", "javascript:history.go(-1);");
			return mav;
		} 
	}
}
