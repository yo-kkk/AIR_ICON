package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class Step03Update_action extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(Step03Update_action.class);
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Acom_infoDTO acom_infoUpdateDTO = (Acom_infoDTO) session.getAttribute("become_host_update");
		
		if(request.getParameter("kitchen")!= null) 		acom_infoUpdateDTO.setKitchen("y"); 	 else acom_infoUpdateDTO.setKitchen("n");
		if(request.getParameter("parking")!= null) 		acom_infoUpdateDTO.setParking("y"); 	 else acom_infoUpdateDTO.setParking("n");
		if(request.getParameter("toiletries")!= null) 	acom_infoUpdateDTO.setToiletries("y"); else acom_infoUpdateDTO.setToiletries("n");
		if(request.getParameter("elevator")!= null) 	acom_infoUpdateDTO.setElevator("y"); 	 else acom_infoUpdateDTO.setElevator("n");
		if(request.getParameter("tv")!= null) 			acom_infoUpdateDTO.setTv("y"); 		 else acom_infoUpdateDTO.setTv("n");
		if(request.getParameter("aircond")!= null) 		acom_infoUpdateDTO.setAircond("y"); 	 else acom_infoUpdateDTO.setAircond("n");
		if(request.getParameter("hotwater")!= null) 	acom_infoUpdateDTO.setHotwater("y"); 	 else acom_infoUpdateDTO.setHotwater("n");
		if(request.getParameter("washer")!= null)		acom_infoUpdateDTO.setWasher("y"); 	 else acom_infoUpdateDTO.setWasher("n");
		if(request.getParameter("wifi")!= null) 		acom_infoUpdateDTO.setWifi("y"); 		 else acom_infoUpdateDTO.setWifi("n");

		session.setAttribute("become_host_update",acom_infoUpdateDTO);
		
		return new ModelAndView("redirect:acom_total");
		
	}
	
}
