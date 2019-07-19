package login.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import login.models.LoginDAO;
import login.models.LoginDAOImpl;
import models.dto.UsersDTO;

public class LoginAction extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {	
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String passwd = DigestUtils.sha512Hex(request.getParameter("passwd"));
	
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setUser_id(user_id);
		usersDTO.setPassword(passwd);
		
		logger.info(usersDTO.toString());
		
		try {
			LoginDAO loginDAO = LoginDAOImpl.getInstance();
			int result = loginDAO.logincheck(usersDTO);
			
			
			if(result != 1) {
				
				ModelAndView mav = new ModelAndView("/result.jsp");
				mav.addObject("msg", "ID 혹은 비밀번호가 틀렸습니다");
				mav.addObject("url", "javascript:history.back();");
				return mav;
			} else {
			
				String host_yn = loginDAO.get_Hostyn(user_id);
			
				session.setAttribute("user_id", user_id);
				session.setAttribute("user_passwd", passwd);
				session.setAttribute("host_yn", host_yn);
				logger.info(host_yn);
			
				logger.info((String) session.getAttribute(user_id));
				session.setAttribute("new", "notnew");
				String action =request.getParameter("_action")== null ? "../home/home" : request.getParameter("_action").equals("/detail/detail_acom") ? "../detail/detail_acom?acom_no="+(String) session.getAttribute("acom_no"):request.getParameter("_action").equals("/search/searchviewnew")?"../search/searchviewnew?pg=1&new=notnew":request.getParameter("_action");

				System.out.println("테스트"+action);
				ModelAndView mav = new ModelAndView();
				mav.setViewName("/WEB-INF/views/home/result.jsp");
				mav.addObject("msg", "로그인되었습니다");
				mav.addObject("url", action);
				
				return mav;
			
			}
			
		} catch(Exception e){
			ModelAndView mav = new ModelAndView("/result.jsp");
			mav.addObject("msg", e.getMessage().replace("\n", "\\n"));
			
			return mav;
		}

	}

}
