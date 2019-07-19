package profile.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class Step01Update extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(Step01Update.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		long acom_no =Long.parseLong(request.getParameter("acom_no"));
		
		ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
		
		Acom_infoDTO acom_infoUpdateDTO = new Acom_infoDTO();
		
		try {
			acom_infoUpdateDTO=	profileDAO.get_Acom(acom_no);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(session.getAttribute("become_host_update")==null) {
			
			session.setAttribute("become_host_update", acom_infoUpdateDTO );
			
		}else {
			acom_infoUpdateDTO = (Acom_infoDTO) session.getAttribute("become_host_update");
		}
		
		logger.info(acom_infoUpdateDTO.toString());
		
		ModelAndView mav = new ModelAndView("/WEB-INF/views/become_host_update/step01_update.jsp");
		mav.addObject("acom_infoUpdateDTO", acom_infoUpdateDTO);
		
		return mav;
	}

}
