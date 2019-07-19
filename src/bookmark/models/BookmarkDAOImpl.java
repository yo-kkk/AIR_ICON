package bookmark.models;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.dto.Acom_infoDTO;
import models.dto.PageDTO;
import models.dto.UsersDTO;




public class BookmarkDAOImpl implements BookmarkDAO {
	private static final BookmarkDAO BookmarkDAO = new BookmarkDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(BookmarkDAOImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private BookmarkDAOImpl() {
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

	public static BookmarkDAO getInstance() {
		return BookmarkDAO;
	}

	
	@Override
	public int bookmark_insert (long acom_no,String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into bookmark(bookmark_no,acom_no,user_id)");
		sql.append(" values(bookmark_no.nextval,?,?)");

		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			pstmt.setString(2, user_id);
			
			
			result = pstmt.executeUpdate();
			
			logger.info(sql.toString());
			
		} finally {
			dbClose(pstmt, conn);
		}
		
		
		return result;
	}
	
	@Override
	public int confirmBookmark(long acom_no,String user_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		ResultSet rs2 =null;
		int x=-1;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select acom_no,user_id");
		sql.append(" from bookmark");
		sql.append(" where user_id=? and acom_no=?");

		
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setLong(2, acom_no);
			
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				
				x=1;//즐겨찾기 있음
			
				
			
			else 
				
				 x=-1;//즐겨찾기 없음
				
				
			
		} finally {
			
			dbClose(rs,pstmt, conn);
		}
		return x;
	}
	
}
