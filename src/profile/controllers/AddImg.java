package profile.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.ImagesDTO;
import profile.models.ProfileDAO;
import profile.models.ProfileDAOImpl;

public class AddImg extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(AddImg.class);

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	
		long acom_no= Long.parseLong(request.getParameter("acom_no") );		
		
		ProfileDAO profileDAO = ProfileDAOImpl.getInstance();
		
		List<ImagesDTO> list = new ArrayList<ImagesDTO>();
		
		String file1 = null;
		String file2 = null;
		String file3 = null;
		
		try {
			list = profileDAO.selectImg(acom_no);
			
			for(int i=0;i<list.size();i++) {
				int no = list.get(i).getNo();
				
				if(no==1) {
					file1 = list.get(i).getSave_file();
				}
				
				if(no==2) {
					file2 = list.get(i).getSave_file();
				}
				
				if(no==3) {
					file3 = list.get(i).getSave_file();
				}
			}

			
		} catch (SQLException e) {
			logger.info("사진이 없습니다.");
		}
				
		ModelAndView mav = new ModelAndView("/WEB-INF/views/profile/add_Img.jsp");
		mav.addObject("file1", file1);
		mav.addObject("file2", file2);
		mav.addObject("file3", file3);
		mav.addObject("acom_no", acom_no);
		
		return mav;
	}

}
