package reservlist.models;

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

import models.dto.Acom_resrvDTO;
import models.dto.PageDTO;
import models.dto.TripAcomListDTO;


public class Trip_impl {
//	private static final Trip_impl trip_impl = new Trip_impl();
	private static final Logger logger = LoggerFactory.getLogger(Trip_impl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private Trip_impl() {
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

	public static Trip_impl getInstance() {
		return new Trip_impl();
	}
	
	public List<TripAcomListDTO> tripdata(PageDTO pageDTO, String sessionID, String col) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql;
		//StringBuffer sql = new StringBuffer();
		List<TripAcomListDTO> list = new ArrayList<TripAcomListDTO>();
		
//		sql.append(" select d.* ");
//		sql.append(" from (select rownum as rnum, c.*");
//		sql.append(" from (select b.intro_title, b.host_id, a.reserv_date_end, b.price");
//		sql.append("       from (select acom_no, reserv_date_end");
//		sql.append("             from ACOM_RESRV");
//		sql.append("             where user_id = 'ddd') a, acom_info b");
//		sql.append("       where a.acom_no = b.acom_no");
//		sql.append("       AND a.reserv_date_end > sysdate");
//		sql.append("       order by reserv_date_end desc) c) d");
//		sql.append(" where rnum between ? and ?");
		
		
		sql = "select d.* from (select rownum as rnum, c.*"
				+ " from (select b.acom_no, b.intro_title, b.host_id,a.acom_reserv_no, a.reserv_date_start, a.reserv_date_end, (a.reserv_date*b.price) as price"
				+ " from (select acom_reserv_no,acom_no, reserv_date_end, reserv_date_start , TO_NUMBER(reserv_date_end - reserv_date_start) as reserv_date"
				+ " from ACOM_RESRV where user_id = ?) a, acom_info b"
				+ " where a.acom_no = b.acom_no AND"
		        + col + " sysdate order by reserv_date_end desc) c) d"
		        + " where rnum between ? and ?";
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			//pstmt.setString(1, sessionID);
			//pstmt.setString(1, "ddd");
			pstmt.setString(1, sessionID);
			pstmt.setLong(2, pageDTO.getStartNum());
			pstmt.setLong(3, pageDTO.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				TripAcomListDTO tripAcomListDTO = new TripAcomListDTO();
				Acom_resrvDTO acom_resrvDTO = new Acom_resrvDTO();
				tripAcomListDTO.setAcom_reserv_no(rs.getLong("acom_reserv_no"));
				tripAcomListDTO.setAcom_no(rs.getLong("acom_no"));
				tripAcomListDTO.setIntro_title(rs.getString("intro_title"));
				logger.info(rs.getString("reserv_date_start"));
				tripAcomListDTO.setReserv_date_end(rs.getDate("reserv_date_end"));
				tripAcomListDTO.setReserv_date_start(rs.getDate("reserv_date_start"));
				tripAcomListDTO.setHost_id(rs.getString("host_id"));
				tripAcomListDTO.setPrice(rs.getLong("price"));
				tripAcomListDTO.setReviewCheck(reviewCheck(sessionID,tripAcomListDTO.getAcom_reserv_no()));
				list.add(tripAcomListDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return list;

	}

	
	
	public long getTotalCount(String col,String sessionID) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select count(*) as cnt from"+" (select acom_no, reserv_date_end"
				+ " from ACOM_RESRV where user_id = ?) a, acom_info b"
				+ " where a.acom_no = b.acom_no AND"
		        + col + " sysdate";
		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sessionID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong("cnt");
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return count;
	}
	
	public int deleteTripReserv (String id,long acom_reserv_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
	
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" delete from acom_resrv");
		sql.append(" where  user_id=? and acom_reserv_no=?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setLong(2, acom_reserv_no);
			
			
			result = pstmt.executeUpdate();
			
			
			
			
		} finally {
			dbClose( pstmt, conn);
		}
		return result;
	}
	
	public int reviewCheck (String user_session,long acom_reserv_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int result = -1;
	
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select acom_reserv_no");
		sql.append(" from review");
		sql.append(" where  user_id=? and acom_reserv_no=?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_session);
			pstmt.setLong(2, acom_reserv_no);
			
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) 
				
				result=1;//후기 있음
			
				
			
			else 
				
				 result=-1;//후기 없음
			
			
		} finally {
			dbClose(rs,pstmt, conn);
		}
		return result;
	}

	
}
