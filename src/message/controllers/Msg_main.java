package message.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import message.models.MessageDAO;
import message.models.MessageDAOImpl;

public class Msg_main extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Msg_main.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user_id");
		
		MessageDAO messageDAO = MessageDAOImpl.getInstance();
		
		List<MessageDTO_list> list = new ArrayList<MessageDTO_list>();
		
		try {
			list = messageDAO.getMessageList(user_id);
							
		} catch (Exception e) {

		}
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/message/msg_main.jsp");
		mav.addObject("list", list);
		mav.addObject("user_id", user_id);
		
		return mav;
		
	}
	
}
