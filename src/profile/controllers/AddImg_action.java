package profile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.ImagesDTO;
import profile.models.ProfileDAOImpl;

public class AddImg_action extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(AddImg_action.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("user_id");
		long acom_no = Long.parseLong(request.getParameter("acom_no") );
		
		try {
			
			for(int i=1;i<=3;i++) {
	
				Part part = request.getPart("img0"+i);		
				String origin_file = getFilename(part);
				
				if(origin_file.contains(".")) {
					String extension = origin_file.substring(origin_file.indexOf(".")+1);
					String save_file = String.valueOf(acom_no) + "_"+i+"." + extension;
					
					ImagesDTO imgDTO = new ImagesDTO();
					
					imgDTO.setAcom_no(acom_no);
					imgDTO.setNo(i);
					imgDTO.setOrigin_file(origin_file);
					imgDTO.setSave_file(save_file);
					logger.info(imgDTO.toString());
					
					ProfileDAOImpl.getInstance().insert_or_update_img(imgDTO);
					
					part.write(save_file);
				}
			}
		} catch (Exception e) {
			
		} 	
		
		ModelAndView mav = new ModelAndView("/result.jsp");
		mav.addObject("msg", "사진이 변경 되었습니다.");
		mav.addObject("url", "profilelist6");
		
		
		return mav;
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
