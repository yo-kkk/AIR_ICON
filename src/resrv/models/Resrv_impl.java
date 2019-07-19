package resrv.models;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.dto.Acom_infoDTO;
import models.dto.Acom_resrvDTO;
import models.dto.MessageDTO;


public class Resrv_impl {
//	private static final Trip_impl trip_impl = new Trip_impl();
	private static final Logger logger = LoggerFactory.getLogger(Resrv_impl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private Resrv_impl() {
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

	public static Resrv_impl getInstance() {
		return new Resrv_impl();
	}
	

	public Acom_infoDTO reservAction_and_getHost_id(Acom_resrvDTO acom_resrvDTO, String user_id) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Acom_infoDTO acom_infoDTO = new Acom_infoDTO();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ACOM_RESRV(ACOM_RESERV_NO, ACOM_NO, USER_ID, PERSON, RESERV_DATE_start, RESERV_DATE_end)"  );
		sql.append(" values(ACOM_RESRV_NO.nextval, ?, ?, ?, ?, ?)														 "  );
		
		StringBuffer sql2 = new StringBuffer();
		
		sql2.append(" select host_id,address,intro_title ");
		sql2.append(" from acom_info    ");
		sql2.append(" where acom_no = ? ");
		

		try {
			conn = getConnection();
		
			pstmt= conn.prepareStatement(sql.toString());

			pstmt.setLong(1, acom_resrvDTO.getAcom_no());
			pstmt.setString(2, user_id);
			pstmt.setInt(3, acom_resrvDTO.getPerson());
			pstmt.setDate(4, acom_resrvDTO.getReserv_date_start());
			pstmt.setDate(5, acom_resrvDTO.getReserv_date_end());
			
			pstmt.executeUpdate();			
			
			pstmt = conn.prepareStatement(sql2.toString());
			
			pstmt.setLong(1, acom_resrvDTO.getAcom_no());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				acom_infoDTO.setHost_id( rs.getString("host_id") );
				acom_infoDTO.setAddress( rs.getString("address") );
				acom_infoDTO.setIntro_title( rs.getString("intro_title") );
			}
			
		} finally {
			dbClose(rs, pstmt, conn);
		}
		
		return acom_infoDTO;
	}
	
	
	
	public void send_reserv_message(MessageDTO msgDTO) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " insert into message(message_no,sender_id,getter_id,content,reg_date)" );
		sql.append( " values (message_no.nextval,?,?,?,?)" );
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, msgDTO.getSender_id());
			pstmt.setString(2, msgDTO.getGetter_id());
			pstmt.setString(3, msgDTO.getContent());
			pstmt.setString(4, msgDTO.getReg_date());
			
			pstmt.executeUpdate();
					
		}finally {
			dbClose( pstmt, conn );
		}
		
	}
	
	
	public int userCardNum(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0; //카드 넘버 널값이면 1 
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select NVL(card_num, 'N') as cardnum from users where user_id = ?");	
		
		
		try {	
			logger.info("==================");
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			logger.info("카드넘버값===========================");
		//	logger.info(rs.getString("cardnum"));
			logger.info("===========================");
			
			while(rs.next()) {
				logger.info("카드넘버값===========================");
				logger.info(rs.getString("cardnum"));
				logger.info("카드넘버값===========================");

				if(rs.getString("cardnum").equals("N")) result = 1;
			}
			
		} catch(Exception e) {
			logger.info("==================");
			e.printStackTrace();
			
		} finally {
			dbClose(pstmt, conn);
		}
		return result;
	}
	
	public int checkDate(Date checkin, Date checkout, long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0; //예약 가능하면 0
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select acom_no ");
		sql.append(" from (select a.reserv_date_start, a.endday, a.acom_no  ");
		sql.append(" from (select acom_no, acom_reserv_no, reserv_date_start, reserv_date_end-1 as endday");  
		sql.append(" from acom_resrv  ");
		sql.append(" where reserv_date_start between ? and ? ");
		sql.append(" or reserv_date_start <=  ?) a ");
		sql.append(" where a.endday between ? and ? ");
		sql.append(" or  a.endday > ?)");		
		
		try {	
			logger.info("try==================");
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setDate(1, checkin);
			pstmt.setDate(2, checkout);
			pstmt.setDate(3, checkout);
			pstmt.setDate(4, checkin);
			pstmt.setDate(5, checkout);
			pstmt.setDate(6, checkout);
			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				logger.info("rs.next 들어옴===========================");
				//logger.info(rs.getString("acom_no"));
				logger.info("카드넘버값===========================");
				if(rs.getLong("acom_no") == acom_no) {
					result = 1;
				}
				 //예약 안됨, 예약 정보 있음
			}
			
		} catch(Exception e) {
			logger.info("==================");
			e.printStackTrace();
			
		} finally {
			dbClose(pstmt, conn);
		}
		return result;
	}
	
}
