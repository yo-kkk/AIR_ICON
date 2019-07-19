package login.models;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.dto.UsersDTO;

public class LoginDAOImpl implements LoginDAO{
	private static final LoginDAO loginDAO = new LoginDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(LoginDAOImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private LoginDAOImpl() {
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

	public static LoginDAO getInstance() {
		return loginDAO;
	}

	@Override
	public int logincheck(UsersDTO usersDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select user_id, password from users where user_id = ? and password = ?");	
		
		try {	
			logger.info("==================");
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, usersDTO.getUser_id());
			pstmt.setString(2, usersDTO.getPassword());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = 1;
			}
			
		} catch(Exception e) {
			logger.info("==================");
			e.printStackTrace();
			
		} finally {
			dbClose(pstmt, conn);
		}
		return result;
	}

	@Override
	public String get_Hostyn(String user_id) throws SQLException {
		String host_yn = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " select host_yn		");
		sql.append( " from users            ");
		sql.append( " where user_id= ?	    ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				host_yn = rs.getString("host_yn");
			}
			
		}finally {
			dbClose(rs, pstmt, conn);
		}
		
		return host_yn;
	}


}
