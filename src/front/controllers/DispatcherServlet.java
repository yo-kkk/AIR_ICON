package front.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet({
		"/home/home"	,
		"/home/*"		,
		"/become_host/*",
		"/resrv/*" 		,
		"/profile/*"	,
		"/detail/*" 	,
		"/trip/*" 		,
		"/userInfo/*"	,
		"/message/*"	,
		"/search/*"
}) 
@MultipartConfig(
		location = "\\\\211.238.142.120\\images",
		maxFileSize = -1,
		maxRequestSize = -1,
		fileSizeThreshold = -1
	)
public class DispatcherServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String,AbstractController> controllerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException{
		logger.info("DispatcherServlet Working...");
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(this.getClass().getResource("").getPath()+"/dispatcherServlet.properties"));
			
			for(Object okey : prop.keySet()) {
				String key = ((String) okey).trim();
				
				try {
					Class className = Class.forName(prop.getProperty(key).trim());
					controllerMap.put(key,(AbstractController)className.newInstance());
					logger.info("loaded : " + key);
				} catch (Exception e) {
					logger.info("ActionClass Error : " + e.getMessage());
					e.printStackTrace();
				}
				
			}
			
			
		} catch (IOException e) {
			logger.info("dispatcherServlet.properties File is not exist");
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		logger.warn("dispatcherServlet.service()");
		
		String requestURI =request.getRequestURI().trim();
		String action = requestURI.substring(request.getContextPath().length()).trim();
		
		logger.debug("requestURI : " + requestURI);
		logger.debug(action);		
		AbstractController controller = controllerMap.get(action);
		ModelAndView mav = null;
		
		try {
			mav=controller.handleRequestInternal(request, response);
		}catch(Exception e) {
			logger.info(action + "액션이 존재하지 않습니다");
		}
		
		if(mav !=null) {
			
			String viewName = mav.getViewName();
			if(viewName.substring(0,9).equals("redirect:")) {
				response.sendRedirect(viewName.substring(9));
				return;
			}
			
			request.setAttribute("_action", action);
			Map<String,Object> model = mav.getModel();
			for(String key:model.keySet()) {
				request.setAttribute(key,model.get(key));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
			dispatcher.forward(request, response);
			return;
			
		}else {
			logger.info("Now you lost your way on the DispatcherServlet..");
		}
			
	}

	
}
