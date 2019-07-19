package message.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import message.models.MessageDAO;
import message.models.MessageDAOImpl;
import models.dto.MessageDTO;

public class Msg_detail extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user_id");
		String other_id = request.getParameter("other_id");
		
		List<MessageDTO> list = null;
		
		MessageDAO messageDAO = MessageDAOImpl.getInstance();
		
		try {
			list = messageDAO.getMessageDetail(user_id, other_id);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/message/msg_detail.jsp");
		mav.addObject("list", list);
		mav.addObject("user_id", user_id);
		mav.addObject("other_id", other_id);
		
		return mav;
	}

}
