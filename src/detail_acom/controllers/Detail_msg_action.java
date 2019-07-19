package detail_acom.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import detail_acom.models.Detail_acomDAO;
import detail_acom.models.Detail_acomDAOImpl;
import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.MessageDTO;

public class Detail_msg_action extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Detail_msg_action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		long acom_no = Long.parseLong(request.getParameter("acom_no"));
		String user_id = (String) session.getAttribute("user_id");
		String content = request.getParameter("msg_content");
		
		String host_id = null;
		
		Detail_acomDAO detail_acomDAO = Detail_acomDAOImpl.getInstance();

		try {
			host_id = detail_acomDAO.selectHost_id(acom_no);
			
			if(user_id.equals(host_id)) {
				ModelAndView mav = new ModelAndView();
				
				mav.setViewName("/result.jsp");
				mav.addObject("msg", "자기 자신에게는 메시지를 전송할수 없습니다.");
				mav.addObject("url", "detail_acom?acom_no="+acom_no);
				return mav;
			}
			
			Date date = new Date();
			SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd hh:mm ss a");
			String reg_date = sdf.format(date);
			
			MessageDTO msgDTO = new MessageDTO();
			msgDTO.setSender_id(user_id);
			msgDTO.setGetter_id(host_id);
			msgDTO.setContent(content);
			msgDTO.setReg_date(reg_date);
			logger.info(msgDTO.toString());
		
			detail_acomDAO.send_msg(msgDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/result.jsp");
		mav.addObject("msg", "메시지 전송이 완료 되었습니다.");
		mav.addObject("url", "detail_acom?acom_no="+acom_no);
		return mav;
		
	}

}
