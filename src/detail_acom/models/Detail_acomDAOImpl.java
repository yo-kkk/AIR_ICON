package detail_acom.models;

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
import models.dto.Acom_resrvDTO;
import models.dto.ImagesDTO;
import models.dto.MessageDTO;
import models.dto.ReviewDTO;

public class Detail_acomDAOImpl implements Detail_acomDAO {
	private static final Detail_acomDAO detail_acomDAO = new Detail_acomDAOImpl();//Detail_acomDAO인스턴스 생성
	private static final Logger logger = LoggerFactory.getLogger(Detail_acomDAOImpl.class);//logger 객체 생성
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private Detail_acomDAOImpl() {
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
	
	public static Detail_acomDAO getInstance() {
		return detail_acomDAO;
	}

	/**
	 * method:db에서 값 받아서 dto에 넣기
	 */
	@Override
	public Acom_infoDTO getAcom_infoDTO(long acom_no) throws SQLException {
		Connection conn = null;        
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Acom_infoDTO acom_infoDTO = null; 
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select acom_no, host_id,address,price,kitchen,parking,	 ");
		sql.append(" 		toiletries,elevator,tv,aircond,hotwater, 		");
		sql.append(" 		washer,wifi,intro_title,intro_cont,      		");
		sql.append(" 		intro_etc,intro_pubtrans,maxperson             	");
		sql.append("   from acom_info                            	 		");
		sql.append("  where	acom_no=?                                		");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1,acom_no);
			rs = pstmt.executeQuery();//결과를 받아와야 함
			
			if(rs.next()) {//rs.next 가 존재한다면
				acom_infoDTO = new Acom_infoDTO();
				acom_infoDTO.setAcom_no(rs.getLong("acom_no"));
				acom_infoDTO.setHost_id(rs.getString("host_id"));
				acom_infoDTO.setAddress(rs.getString("address"));
				acom_infoDTO.setPrice(rs.getLong("price"));
				acom_infoDTO.setKitchen(rs.getString("kitchen"));
				acom_infoDTO.setParking(rs.getString("parking"));
				acom_infoDTO.setToiletries(rs.getString("toiletries"));
				acom_infoDTO.setElevator(rs.getString("elevator"));
				acom_infoDTO.setTv(rs.getString("tv"));
				acom_infoDTO.setAircond(rs.getString("aircond"));
				acom_infoDTO.setHotwater(rs.getString("hotwater"));
				acom_infoDTO.setWasher(rs.getString("washer"));
				acom_infoDTO.setWifi(rs.getString("wifi"));
				acom_infoDTO.setIntro_title(rs.getString("intro_title"));
				acom_infoDTO.setIntro_cont(rs.getString("intro_cont"));
				acom_infoDTO.setIntro_etc(rs.getString("intro_etc"));
				acom_infoDTO.setIntro_pubtrans(rs.getString("intro_pubtrans"));
				acom_infoDTO.setMaxperson(rs.getLong("maxperson"));
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return acom_infoDTO;
	}

	@Override
	public List<ImagesDTO> getImagesDTO(long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ImagesDTO> imagesList = new ArrayList<ImagesDTO>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select acom_no, no, origin_file, save_file	");
		sql.append("   from images                              ");
		sql.append("  where acom_no=? 	                        ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			rs=pstmt.executeQuery();
			
			while(rs.next() ) {
				ImagesDTO imagesDTO = new ImagesDTO();
				imagesDTO.setAcom_no(rs.getLong("acom_no"));
				imagesDTO.setNo(rs.getInt("no"));
				imagesDTO.setOrigin_file(rs.getString("origin_file"));
				imagesDTO.setSave_file(rs.getString("save_file"));
				imagesList.add(imagesDTO);
				
			}
		} finally {
			dbClose(rs, pstmt, conn);

		}
		
		return imagesList;
	}

	@Override
	public List<ReviewDTO> getReviewList(long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select review_no, acom_no, user_id, content,  	");
		sql.append(" 		rate, reg_date, mod_date				");
		sql.append("   from review								   	");
		sql.append("   where acom_no=?                            	");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO reviewDTO = new ReviewDTO();
				reviewDTO.setReview_no(rs.getLong("review_no"));
				reviewDTO.setAcom_no(rs.getLong("acom_no"));
				reviewDTO.setUser_id(rs.getString("user_id"));
				reviewDTO.setContent(rs.getString("content"));
				reviewDTO.setRate(rs.getFloat("rate"));
				reviewDTO.setReg_date(rs.getDate("reg_date"));
				reviewDTO.setMod_date(rs.getDate("mod_date"));
				
				logger.info(reviewDTO.toString());
				list.add(reviewDTO);
			}
			
		} finally {
			dbClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	@Override
	public long reviewDetailCount(long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) as cnt ");
		sql.append(" from review");
		sql.append(" where acom_no=?");
		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong("cnt");
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return count;
	
	}

	@Override
	public List<Acom_resrvDTO> getResrvDate(long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
				
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT distinct dateall FROM(");
		sql.append(" SELECT TO_DATE (a.startday, 'YYYYMMDD')+(LEVEL - 1) as dateall");
		sql.append(" FROM (select acom_no, to_number(to_char(reserv_date_start, 'yyyymmdd')) as startday, to_number(to_char(reserv_date_end-1, 'yyyymmdd')) as endday");
		sql.append(" from acom_resrv ");
		sql.append(" where acom_no = ?) a");
		sql.append(" CONNECT BY TO_DATE (a.startday, 'YYYYMMDD')+(LEVEL-1) <=TO_DATE (a.endday, 'YYYYMMDD'))");
		sql.append(" where dateall > sysdate");
		List<Acom_resrvDTO> list = new ArrayList<Acom_resrvDTO>();

		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Acom_resrvDTO acom_resrvDTO = new Acom_resrvDTO();
				acom_resrvDTO.setResrvDateAll(rs.getDate("dateAll"));
				
				list.add(acom_resrvDTO);
				
			}
			
		} finally {
			dbClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	@Override
	public String selectHost_id(long acom_no) throws SQLException {
		String host_id = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select host_id from acom_info");
		sql.append(" where acom_no = ?  		 ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				host_id = rs.getString("host_id");
			}
			
			
		}finally {
			dbClose(rs, pstmt, conn);
		}

		return host_id;
	}
	

	@Override
	public void send_msg(MessageDTO msgDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into message(message_no,sender_id,getter_id,content,reg_date)");
		sql.append("values(message_no.nextval,?,?,?,?)		 							");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, msgDTO.getSender_id());
			pstmt.setString(2, msgDTO.getGetter_id());
			pstmt.setString(3, msgDTO.getContent());
			pstmt.setString(4, msgDTO.getReg_date());
			
			pstmt.executeUpdate();
			
		}finally {
			dbClose(pstmt, conn);
		}
		
	}

	@Override
	public List<ImagesDTO> getImg(long acom_no) throws SQLException {
List<ImagesDTO> list = new ArrayList<ImagesDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " select no,save_file					");
		sql.append( " from images where acom_no = ?	    	");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ImagesDTO imgDTO = new ImagesDTO();
				
				imgDTO.setNo(rs.getInt("no"));
				imgDTO.setSave_file(rs.getString("save_file") );
				
				logger.info("save_file");
				list.add(imgDTO);
			}
			
		}finally {
			dbClose(pstmt, conn);
		}
		logger.info(list.toString());
		return list;
	}

	

}
