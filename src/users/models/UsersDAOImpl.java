package users.models;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.dto.UsersDTO;




public class UsersDAOImpl implements UsersDAO {
	private static final UsersDAO usersDAO = new UsersDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(UsersDAOImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private UsersDAOImpl() {
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

	public static UsersDAO getInstance() {
		return usersDAO;
	}

	

	
	
	@Override
	public int usersInsert(UsersDTO usersDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into users(user_id,  name,password, tel, birth,gender,address,email)");
		sql.append(" values(?,?,?,?,?,?,?,?)");

		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, usersDTO.getUser_id());
			pstmt.setString(2,usersDTO.getName());
			pstmt.setString(3, usersDTO.getPassword());
			pstmt.setString(4, usersDTO.getTel());
			pstmt.setDate(5, usersDTO.getBirth());
			pstmt.setString(6, usersDTO.getGender());
			pstmt.setString(7, usersDTO.getAddress());
			pstmt.setString(8, usersDTO.getEmail());
			
			result = pstmt.executeUpdate();
			
			logger.info(sql.toString());
			
		} finally {
			dbClose(pstmt, conn);
		}
		
		
		return result;
	}
	
	@Override
	public int confirmId(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		ResultSet rs2 =null;
		int x=-1;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select user_id");
		sql.append(" from users");
		sql.append(" where user_id=?");

		
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				
				x=1;//아이디 있음
			
				
			
			else 
				
				 x=-1;//아이디없음
				
				
			
		} finally {
			
			dbClose(rs,pstmt, conn);
		}
		return x;
	}

	@Override
	public void sendWelcomeMsg(String user_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Date date = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd hh:mm ss a");
		String reg_date = sdf.format(date);	
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into message(message_no,sender_id,getter_id,content,reg_date) ");
		sql.append("values(message_no.nextval,'admin',?,?,? ) ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			StringBuffer content = new StringBuffer();
			content.append(user_id + "님 가입을 축하드립니다! \n");
			content.append("에어아이콘에서 좋은시간 보내시길 바라며, \n");
			content.append("현재 첫 예약시 30%할인 이벤트를 하고 있습니다 \n");
			content.append("자세한 사항은 고객센터로 문의하시길 바랍니다.");
	
			pstmt.setString(1, user_id);
			pstmt.setString(2, content.toString());
			pstmt.setString(3, reg_date);
			
			pstmt.executeUpdate();
			
					
		}finally {
			dbClose(pstmt, conn);
			
			
			
		}
		
	}

	
			
}
