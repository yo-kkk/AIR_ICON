package resrv.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;
import models.dto.Acom_resrvDTO;
import models.dto.MessageDTO;
import resrv.models.Resrv_impl;

public class Acom_resrv_action extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(Acom_resrv_action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user_id");
		
		Date checkin = Date.valueOf(request.getParameter("checkin"));
		Date checkout = Date.valueOf(request.getParameter("checkout"));
		int person = Integer.parseInt(request.getParameter("person"));

		long acom_no = Long.parseLong(request.getParameter("acom_no"));
		
		int cardresult = 0;
		int dateresult = 0;
		
		try {
			dateresult = Resrv_impl.getInstance().checkDate(checkin, checkout, acom_no);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Acom_resrvDTO acom_resrvDTO = new Acom_resrvDTO();
		acom_resrvDTO.setAcom_no(acom_no);
		acom_resrvDTO.setUser_id(user_id);
		acom_resrvDTO.setReserv_date_start(checkin);
		acom_resrvDTO.setReserv_date_end(checkout);
		acom_resrvDTO.setPerson(person);

		
		logger.info(acom_resrvDTO.toString());
		
		try {
			cardresult = Resrv_impl.getInstance().userCardNum(user_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
		
			try {
				
				if(dateresult == 0) {
					
					
					if(cardresult == 0) { //널값 아니면 0
						Acom_infoDTO acom_infoDTO = Resrv_impl.getInstance().reservAction_and_getHost_id(acom_resrvDTO, user_id);
						
						String host_id = acom_infoDTO.getHost_id();
						String address = acom_infoDTO.getAddress();
						String Intro_title = acom_infoDTO.getIntro_title();
						
						StringBuffer content = new StringBuffer();
						content.append(host_id + "님, \n");
						content.append(user_id + "님이, " + checkin + " 부터 " + checkout + "까지 \n");
						content.append(address + "에 위치한 " + Intro_title + " 숙소 에 예약하셨습니다. \n");
						content.append("메시지를 통해 문의하시길 바랍니다. \n");
						
						java.util.Date date = new java.util.Date();
						SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd hh:mm ss a");
						String reg_date = sdf.format(date);
						
						MessageDTO msgDTO = new MessageDTO();
						
						msgDTO.setSender_id(host_id);
						msgDTO.setGetter_id(user_id);
						msgDTO.setContent(content.toString());
						msgDTO.setReg_date(reg_date);
						logger.info(msgDTO.toString());
						
						Resrv_impl.getInstance().send_reserv_message(msgDTO);

						ModelAndView mav = new ModelAndView("/result.jsp");
						mav.addObject("msg", "예약이 완료되었습니다~");		
						mav.addObject("url", "../trip/trip_1");
						return mav;
						
						} else {
							logger.info("=====카드정보가 없당========");
							ModelAndView mav = new ModelAndView("/result.jsp");
							mav.addObject("msg", "카드 정보를 입력해주세요");
							mav.addObject("url", "../profile/profilelist2");
							return mav;
						}
				
				//예약 아예 안됨
				}else {
					logger.info("=====예약된 날짜 찍었을 경우========");
					ModelAndView mav = new ModelAndView("/result.jsp");
					mav.addObject("msg", "예약이 불가능한 날짜입니다");
					mav.addObject("url", "javascript:history.back(-1);");
					return mav;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				ModelAndView mav = new ModelAndView("/result.jsp");
				mav.addObject("msg", "입력 실패");
				mav.addObject("url", "javascript:history.back(-1);");
				return mav;
			}
			
			
	}
	
	
}
