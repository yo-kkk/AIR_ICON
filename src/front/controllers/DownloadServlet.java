package front.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/Download")
@MultipartConfig(
		location = "\\\\211.238.142.120\\images",
		maxFileSize = -1,
		maxRequestSize = -1,
		fileSizeThreshold = -1
	)
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final Logger logger = LoggerFactory.getLogger(DownloadServlet.class);
	   @Override
	   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	     String location="\\\\211.238.142.120\\images";
	     String fileName = request.getParameter("fileName");
	      
	     logger.info("fileName : " + fileName);
	     response.setContentType("application/octet-stream");
	     try {
	         response.setHeader(
	            "Content-Disposition", "attachment;filename=\"" + 
	            URLEncoder.encode(fileName,"UTF-8") +"\"");
	      
	         byte[] data = new byte[1024 * 100];
	         InputStream  is = new BufferedInputStream(new FileInputStream(location+"\\"+fileName));
	         OutputStream os = new BufferedOutputStream(response.getOutputStream());
	         while(is.read(data) != -1){
	            os.write(data);
	         }
	         if (os != null) try{
	            os.flush();
	            os.close();} catch(Exception e){}
	         if (is != null) try{is.close();} catch(Exception e){}
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}