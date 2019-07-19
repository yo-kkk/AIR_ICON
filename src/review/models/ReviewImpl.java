package review.models;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.dto.ReviewDTO;

public class ReviewImpl {
//	private static final Trip_impl trip_impl = new Trip_impl();
	private static final Logger logger = LoggerFactory.getLogger(ReviewImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private ReviewImpl() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					this.getClass().getResource("").getPath() + 
					"/database.properties"));
			url      = prop.getProperty("url").trim();
			driver   = prop.getProperty("driver").trim();
			username = prop.getProperty("username").trim();
			password = prop.getProperty("password").trim();
			
			Class.forName(driver);
		} catch (Exception e) {
			logger.info(e.toString());
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs    != null) try {rs.close();   } catch(Exception e) {}
		if (pstmt != null) try {pstmt.close();} catch(Exception e) {}
		if (conn  != null) try {conn.close(); } catch(Exception e) {}
	}

	public void dbClose(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) try {pstmt.close();} catch(Exception e) {}
		if (conn  != null) try {conn.close(); } catch(Exception e) {}
	}

	public static ReviewImpl getInstance() {
		return new ReviewImpl();
	}

	public void insertReview(ReviewDTO reviewDTO, String user_session) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into REVIEW(REVIEW_NO, ACOM_NO, USER_ID, CONTENT, RATE,ACOM_RESERV_NO)");
		sql.append(" values(REVIEW_NO.nextval, ?, ?, ?, ?,?)");

		try {
			conn = getConnection();
		
			pstmt= conn.prepareStatement(sql.toString());
			//pstmt.setLong(1, acom_resrvDTO.getAcom_no());
			//pstmt.setString(2, user_session); //id 세션
			
			pstmt.setLong(1, reviewDTO.getAcom_no());
			pstmt.setString(2, user_session);
			pstmt.setString(3, reviewDTO.getContent());
			pstmt.setFloat(4, reviewDTO.getRate());
			pstmt.setLong(5, reviewDTO.getAcom_reserv_no());
		
			pstmt.executeUpdate();
			
		} finally {
			dbClose(pstmt, conn);
		}
	}	
	

	
}
