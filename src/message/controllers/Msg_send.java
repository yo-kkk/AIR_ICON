package message.controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import message.models.MessageDAO;
import message.models.MessageDAOImpl;
import models.dto.MessageDTO;

public class Msg_send extends AbstractController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user_id");
		String other_id = request.getParameter("other_id");
		String msg_content = request.getParameter("msg_content");
		
		Date date = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd hh:mm ss a");
		String reg_date = sdf.format(date);
		
		MessageDTO messageDTO = new MessageDTO();
		
		messageDTO.setSender_id(user_id);
		messageDTO.setGetter_id(other_id);
		messageDTO.setContent(msg_content);
		messageDTO.setReg_date(reg_date);
			
		MessageDAO messageDAO = MessageDAOImpl.getInstance();
		
		try {
			messageDAO.sendMessage(messageDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("redirect:msg_detail?other_id="+ other_id);
		
		return mav;
	}

}
