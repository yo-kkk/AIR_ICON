package detail_acom.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import detail_acom.models.Detail_acomDAO;
import detail_acom.models.Detail_acomDAOImpl;
import front.controllers.AbstractController;
import front.controllers.ModelAndView;
import models.dto.Acom_infoDTO;
import models.dto.Acom_resrvDTO;
import models.dto.ImagesDTO;
import models.dto.ReviewDTO;

public class Detail_acom extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(Detail_acom.class);
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		long acom_no = Long.parseLong(request.getParameter("acom_no"));//글번호를 얻어옴
		long reviewCount=0;
		HttpSession session = request.getSession();
		
		session.setAttribute("acom_no",request.getParameter("acom_no"));

		List<ImagesDTO> imgList = new ArrayList<ImagesDTO>();
		
		String file1 = null;
		String file2 = null;
		String file3 = null;
		//String review_no = request.getParameter("review_no");
		//long no = 2;
		//long acom_no = 3;
		try {
			Detail_acomDAO detail_acomDAO = Detail_acomDAOImpl.getInstance();
			//Acom_infoDTO acom_infoDTO = detail_acomDAO.getAcom_infoDTO(no);
			Acom_infoDTO acom_infoDTO = detail_acomDAO.getAcom_infoDTO(acom_no);
			java.util.List<ReviewDTO> list = detail_acomDAO.getReviewList(acom_no);
			java.util.List<ImagesDTO> imagesList = detail_acomDAO.getImagesDTO(acom_no);
			reviewCount = detail_acomDAO.reviewDetailCount(acom_no);
			logger.info("일단 dto까진 받아옴");
			
			//해당 숙소 이미지를 불러올 리스트
			imgList = detail_acomDAO.getImg(acom_no);
			
			for(int i=0;i<imgList.size();i++) {
				int no = imgList.get(i).getNo();
					
				if(no==1) {
					file1 = imgList.get(i).getSave_file();
				}
				
				if(no==2) {
					file2 = imgList.get(i).getSave_file();
				}
					
				if(no==3) {
					file3 = imgList.get(i).getSave_file();
				}
			}
			
			
			java.util.List<Acom_resrvDTO> dateAll = detail_acomDAO.getResrvDate(acom_no);
			
			if(acom_infoDTO == null || list==null) 
				throw new RuntimeException();
			
			logger.info("RuntimeException 안들어감");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/views/detail_acom/detail_acom.jsp");
			mav.addObject("acom_infoDTO", acom_infoDTO);
			mav.addObject("list", list);
			mav.addObject("imagesList", imagesList);
			mav.addObject("dateAll", dateAll);
			mav.addObject("reviewCount", reviewCount);
			mav.addObject("acom_no", acom_no);
			mav.addObject("imgList", imgList);
			mav.addObject("file1", file1);
			mav.addObject("file2", file2);
			mav.addObject("file3", file3);
			
			logger.info("file1 = "+file1);
			logger.info("file2 = "+file2);
			logger.info("file3 = "+file3);
			logger.info("mav 값 넘김");
			return mav;
			
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/result.jsp");
			mav.addObject("msg", "게시물이 존재하지 않거나 DB연결 오류입니다.");
			mav.addObject("url","home");//없는 파일임 꼭 확인 해라
			
			return mav;
		}
		
	}

}
