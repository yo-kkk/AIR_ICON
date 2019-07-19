package become_host.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;

public class Step03_action extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(Step03_action.class);
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Acom_infoDTO acom_infoDTO = (Acom_infoDTO) session.getAttribute("become_host");
		
		if(request.getParameter("kitchen")!= null) 		acom_infoDTO.setKitchen("y"); 	 else acom_infoDTO.setKitchen("n");
		if(request.getParameter("parking")!= null) 		acom_infoDTO.setParking("y"); 	 else acom_infoDTO.setParking("n");
		if(request.getParameter("toiletries")!= null) 	acom_infoDTO.setToiletries("y"); else acom_infoDTO.setToiletries("n");
		if(request.getParameter("elevator")!= null) 	acom_infoDTO.setElevator("y"); 	 else acom_infoDTO.setElevator("n");
		if(request.getParameter("tv")!= null) 			acom_infoDTO.setTv("y"); 		 else acom_infoDTO.setTv("n");
		if(request.getParameter("aircond")!= null) 		acom_infoDTO.setAircond("y"); 	 else acom_infoDTO.setAircond("n");
		if(request.getParameter("hotwater")!= null) 	acom_infoDTO.setHotwater("y"); 	 else acom_infoDTO.setHotwater("n");
		if(request.getParameter("washer")!= null)		acom_infoDTO.setWasher("y"); 	 else acom_infoDTO.setWasher("n");
		if(request.getParameter("wifi")!= null) 		acom_infoDTO.setWifi("y"); 		 else acom_infoDTO.setWifi("n");

		session.setAttribute("become_host",acom_infoDTO);
		
		return new ModelAndView("redirect:total");
	}
	
}
