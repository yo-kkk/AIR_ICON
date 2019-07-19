package become_host.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import become_host.models.Become_hostDAO;
import become_host.models.Become_hostDAOImpl;
import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;
import models.dto.ImagesDTO;

public class Total_action extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Total_action.class);

	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();		
		Acom_infoDTO acom_infoDTO = (Acom_infoDTO) session.getAttribute("become_host");
		session.setAttribute("become_host", null);		
		logger.info(acom_infoDTO.toString());
		
		//acom_no를 쿼리에서 받아오기 위한 객체생성
		long acom_no = -1;
		
		String user_id = acom_infoDTO.getHost_id();
		
		Become_hostDAO become_hostDAO = Become_hostDAOImpl.getInstance();
		
		try {
			acom_no = become_hostDAO.insert_Acom_and_return_no(acom_infoDTO);
			
			become_hostDAO.change_hostyn(user_id);
			session.setAttribute("host_yn", "y");
			become_hostDAO.send_message(acom_infoDTO);
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("insert_Acom_and_return_no 오류 입니다");
		}
		
		ImagesDTO imgDTO = new ImagesDTO();
				
		try {
			Part part = request.getPart("img_up");
			
			String origin_file = getFilename(part);
			String extension = origin_file.substring(origin_file.indexOf(".")+1);
			String save_file = String.valueOf(acom_no) + "_1." + extension;
			
			imgDTO.setAcom_no(acom_no);
			imgDTO.setNo(1);
			imgDTO.setOrigin_file(origin_file);
			imgDTO.setSave_file(save_file);
			
			become_hostDAO.insert_img(imgDTO);
	
			part.write(save_file);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("request.getPart 오류 입니다.");
		}		
		
		session.removeAttribute("become_host");
		return new ModelAndView("redirect:../profile/profilelist6");
		
	}
	
	 private String getFilename(Part part){
		 String fileName = null;  
		 
	        String contentDispositionHeader = part.getHeader("content-disposition");

	        String [] elements = contentDispositionHeader.split(";");
	             
	        for(String element: elements){	        	
	            fileName = element.substring(element.indexOf('=')+1);
	            fileName = fileName.trim().replace("\""," ");
	        }        
        
	        return fileName;     
	 }
	 
	 
}
