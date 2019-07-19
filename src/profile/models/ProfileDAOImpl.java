package profile.models;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.dto.Acom_infoDTO;
import models.dto.Acom_resrvDTO;
import models.dto.ImagesDTO;
import models.dto.PageDTO;
import models.dto.UsersDTO;




public class ProfileDAOImpl implements ProfileDAO {
	private static final ProfileDAO profileDAO = new ProfileDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(ProfileDAOImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private ProfileDAOImpl() {
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

	public static ProfileDAO getInstance() {
		return profileDAO;
	}

	
	@Override
	public UsersDTO getUserInfo(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UsersDTO usersDTO = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select user_id, password, name, tel,birth, address, gender, email");
		sql.append(" from   users");
		sql.append(" where  user_id=?");
	
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs   = pstmt.executeQuery();
			if (rs.next()) {
				usersDTO = new UsersDTO();
				usersDTO.setUser_id(rs.getString("user_id"));
				usersDTO.setName(rs.getString("name"));
				usersDTO.setPassword(rs.getString("password"));		
				usersDTO.setTel(rs.getString("tel"));
				usersDTO.setBirth(rs.getDate("birth"));
				usersDTO.setAddress(rs.getString("address"));
				usersDTO.setGender(rs.getString("gender").equals("m") ? "남자" : "여자");
				usersDTO.setEmail(rs.getString("email"));
				
				
				
	
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return usersDTO;
	}
	@Override
	public int updateProfile(UsersDTO usersDTO,String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" update users set");
		sql.append("        name=?, password=?, email=?, tel=?, address=?, birth=?, mod_date=sysdate ");
		sql.append(" where  user_id=?");

		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, usersDTO.getName());
			pstmt.setString(2, usersDTO.getPassword());
			pstmt.setString(3,usersDTO.getEmail());
			pstmt.setString(4, usersDTO.getTel());
			pstmt.setString(5, usersDTO.getAddress());
			pstmt.setDate(6, usersDTO.getBirth());
			pstmt.setString(7, id);
			
			result = pstmt.executeUpdate();
			
		} finally {
			dbClose(pstmt, conn);
		}
		return result;
	}


	@Override
	public int updateAccount(UsersDTO usersDTO,String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" update users set");
		sql.append("        card_num=?, card_date=?, card_password=?, card_cvc=? ,mod_date=sysdate  ");
		sql.append(" where  user_id=?");

		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, usersDTO.getCard_num());
			pstmt.setString(2, usersDTO.getCard_date());
			pstmt.setString(3,usersDTO.getCard_password());
			pstmt.setString(4, usersDTO.getCard_cvc());
			pstmt.setString(5, id);
		
			
			result = pstmt.executeUpdate();
			
		} finally {
			dbClose(pstmt, conn);
		}
		return result;
	}
	
	
	@Override
		public UsersDTO getAccount(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UsersDTO usersDTO = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select card_num, card_date, card_cvc, card_password");
		sql.append(" from   users");
		sql.append(" where  user_id=?");
	
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs   = pstmt.executeQuery();
			if (rs.next()) {
				usersDTO = new UsersDTO();
				usersDTO.setCard_num(rs.getString("card_num"));
				
				if(usersDTO.getCard_num()==null) {
					//usersDTO.setCard_num_sub("                ");	
					usersDTO.setCard_password(rs.getString("card_password"));
					usersDTO.setCard_date(rs.getString("card_date"));
					usersDTO.setCard_cvc(rs.getString("card_cvc"));
				}else {
				usersDTO.setCard_num_sub(rs.getString("card_num"));	
				usersDTO.setCard_password(rs.getString("card_password"));
				usersDTO.setCard_date(rs.getString("card_date"));
				usersDTO.setCard_cvc(rs.getString("card_cvc"));
				}
			}
			
			
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return usersDTO;
	}
	
	
	

			
	@Override
	public List<Bookmark_profileDTO> getBookmarkList(PageDTO pageDTO,String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Bookmark_profileDTO> bookmarkList = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select D.*");
		sql.append(" from (select rownum as rnum, C.*");
		sql.append("       from  (select  b.bookmark_no as bookmark_no, b.acom_no as acom_no, a.host_id as host_id,a.price as price ,a.intro_title as intro_title,a.address as address");
		sql.append("              from  acom_info a,bookmark b");
		sql.append("              where (a.acom_no =b.acom_no) and b.user_id=?");
		sql.append(" order by bookmark_no desc)C)D");
		sql.append(" where  rnum between ? and ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			pstmt.setLong(2, pageDTO.getStartNum());
			pstmt.setLong(3, pageDTO.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bookmark_profileDTO bookmarkDTO = new Bookmark_profileDTO();
				bookmarkDTO.setBookmark_no(rs.getLong("bookmark_no"));
				bookmarkDTO.setAddress(rs.getString("address"));
				bookmarkDTO.setAcom_no(rs.getLong("acom_no"));
				bookmarkDTO.setHost_name(rs.getString("host_id"));
				bookmarkDTO.setPrice(rs.getLong("price"));
				bookmarkDTO.setTitle(rs.getString("intro_title"));
				
				
				bookmarkList.add(bookmarkDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return bookmarkList;
	}
	
	@Override
	public int deleteBookmarkList(String id,long bookmark_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from bookmark");
		sql.append(" where  user_id=? and bookmark_no=?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setLong(2, bookmark_no);
			result = pstmt.executeUpdate();
			
		} finally {
			dbClose( pstmt, conn);
		}
		return result;
	}
	
	
	@Override
	public int deleteReview(String id,long review_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" delete from review");
		sql.append(" where  user_id=? and review_no=?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setLong(2, review_no);
			result = pstmt.executeUpdate();
			
			
			
			
		} finally {
			dbClose( pstmt, conn);
		}
		return result;
	}
	
	@Override
	public int deleteAcomList (String id,long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
	
		
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		sql.append(" delete from acom_info");
		sql.append(" where  host_id=? and acom_no=?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setLong(2, acom_no);
			
			
			result = pstmt.executeUpdate();
			
			
			
			
		} finally {
			dbClose( pstmt, conn);
		}
		return result;
	}
	
	@Override
	public int deleteReview_acom (String id,long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
	
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" delete from review");
		sql.append(" where  user_id=? and acom_no=?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setLong(2, acom_no);
			
			
			result = pstmt.executeUpdate();
			
			
			
			
		} finally {
			dbClose( pstmt, conn);
		}
		return result;
	}
	
	@Override
	public List<Review_profileDTO> getReview_host_List(PageDTO pageDTO,String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 List<Review_profileDTO> reviewList = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select D.*");
		sql.append(" from (select rownum as rnum, C.*");
		sql.append("       from  (select a.review_no as review_no , a.user_id as user_id ,b.intro_title as intro_title ,b.host_id as host_id , a.content as content,a.rate as rate,a.acom_no,b.address as address");
		sql.append("             from review a , acom_info b");
		sql.append("         where a.acom_no = b.acom_no and b.host_id=? ");
		sql.append(" order by review_no desc)C)D");
		sql.append(" where  rnum between ? and ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			pstmt.setLong(2, pageDTO.getStartNum());
			pstmt.setLong(3, pageDTO.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Review_profileDTO reviewDTO = new Review_profileDTO();
				reviewDTO.setReview_no(rs.getLong("review_no"));
				reviewDTO.setUser_id(rs.getString("user_id"));
				reviewDTO.setAcom_no(rs.getLong("acom_no"));
				reviewDTO.setAddress(rs.getString("address"));
				reviewDTO.setTitle(rs.getString("intro_title"));
				reviewDTO.setContent(rs.getString("content"));
				reviewDTO.setRate(rs.getFloat("rate"));
				
				
				
				reviewList.add(reviewDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return reviewList;
	}
	
	@Override
	public List<Acom_infoDTO> getAcom_info(PageDTO pageDTO,String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 List<Acom_infoDTO> acom_infoList = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select D.*");
		sql.append(" from (select rownum as rnum, C.*");
		sql.append("       from  (select acom_no, intro_title,price,address, type");
		sql.append("             from acom_info");
		sql.append("         where host_id=? ");
		sql.append(" order by acom_no desc)C)D");
		sql.append(" where  rnum between ? and ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			pstmt.setLong(2, pageDTO.getStartNum());
			pstmt.setLong(3, pageDTO.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Acom_infoDTO acom_infoDTO = new Acom_infoDTO();
				
				acom_infoDTO.setAcom_no(rs.getLong("acom_no"));
				acom_infoDTO.setIntro_title(rs.getString("intro_title"));
				acom_infoDTO.setPrice(rs.getLong("price"));
				acom_infoDTO.setAddress(rs.getString("address"));
				acom_infoDTO.setType(rs.getString("type"));
				
				acom_infoList.add(acom_infoDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return acom_infoList;
	}
	
	@Override
	public long getTotalCount_bookmark(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) as cnt ");
		sql.append(" from(select  b.bookmark_no as bookmark_no, b.acom_no as acom_no, a.host_id as host_id,a.price as price ,a.intro_title as intro_title");
		sql.append(" from  acom_info a,bookmark b");
		sql.append("  where (a.acom_no =b.acom_no) and b.user_id=?)");
		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
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
	public long getTotalCount_acom(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) as cnt ");
		sql.append(" from(select acom_no, intro_title,price,address, type");
		sql.append(" from  acom_info");
		sql.append("  where host_id=?)");
		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
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
	public long getTotalCount_host_review(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) as cnt ");
		sql.append(" from(select a.review_no as review_no , a.user_id as user_id ,b.intro_title as intro_title ,b.host_id as host_id , a.content as content,a.rate as rate,a.acom_no");
		sql.append(" from review a , acom_info b");
		sql.append("  where a.acom_no = b.acom_no and b.host_id=?)");
		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
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
	public List<Review_profileDTO> getReview_user_List(PageDTO pageDTO, String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		 List<Review_profileDTO> reviewList = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select D.*");
		sql.append(" from (select rownum as rnum, C.*");
		sql.append("       from  (select b.review_no as review_no,b.acom_no as acom_no, a.intro_title as intro_title,a.host_id as host_id,b.rate as rate ,b.content as content,a.address as address");
		sql.append("             from acom_info a,review b");
		sql.append("         where a.acom_no=b.acom_no and b.user_id=? ");
		sql.append(" order by review_no desc)C)D");
		sql.append(" where  rnum between ? and ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			pstmt.setLong(2, pageDTO.getStartNum());
			pstmt.setLong(3, pageDTO.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Review_profileDTO reviewDTO = new Review_profileDTO();
				reviewDTO.setReview_no(rs.getLong("review_no"));
				reviewDTO.setHost_id(rs.getString("host_id"));
				reviewDTO.setTitle(rs.getString("intro_title"));
				reviewDTO.setAddress(rs.getString("address"));
				reviewDTO.setContent(rs.getString("content"));
				reviewDTO.setRate(rs.getFloat("rate"));
				reviewDTO.setAcom_no(rs.getLong("acom_no"));

				reviewList.add(reviewDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return reviewList;
	}

	@Override
	public long getTotalCount_user_review(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) as cnt ");
		sql.append(" from(select b.review_no as review_no,b.acom_no as acom_no, a.intro_title as intro_title,a.host_id as host_id,b.rate as rate ,b.content as content");
		sql.append(" from acom_info a,review b");
		sql.append("   where a.acom_no=b.acom_no and b.user_id=?)");
		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
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
	public List<Acom_resrv_hostDTO> getResrv_host_List(PageDTO pageDTO, String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 List<Acom_resrv_hostDTO> acomList = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select D.*");
		sql.append(" from (select rownum as rnum, C.*");
		sql.append("       from  (select a.acom_no as acom_no,a.acom_reserv_no as acom_reserv_no, a.user_id as user_id, b.intro_title as title, a.reserv_date_start as reserv_date_start,a.reserv_date_end as reserv_date_end, a.person as person,a.etc as etc,b.address as address,");
		sql.append(" months_between(a.reserv_date_end,sysdate) as reserv_date_check_end,");
		sql.append(" months_between(a.reserv_date_start,sysdate) as reserv_date_check_start");
		sql.append("             from acom_resrv a, acom_info b");
		sql.append("         where a.acom_no=b.acom_no and b.host_id=?");
		sql.append(" order by acom_reserv_no desc)C)D");
		sql.append(" where  rnum between ? and ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			pstmt.setLong(2, pageDTO.getStartNum());
			pstmt.setLong(3, pageDTO.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Acom_resrv_hostDTO acom_resrv_hostDTO = new Acom_resrv_hostDTO();
				acom_resrv_hostDTO.setAcom_no(rs.getLong("acom_no"));
				acom_resrv_hostDTO.setAcom_reserv_no(rs.getLong("acom_reserv_no"));
				acom_resrv_hostDTO.setTitle(rs.getString("title"));
				acom_resrv_hostDTO.setReserv_date_start(rs.getDate("reserv_date_start"));
				acom_resrv_hostDTO.setReserv_date_end(rs.getDate("reserv_date_end"));
				acom_resrv_hostDTO.setPerson(rs.getInt("person"));
				acom_resrv_hostDTO.setAddress(rs.getString("address"));
				acom_resrv_hostDTO.setUser_id(rs.getString("user_id"));
				acom_resrv_hostDTO.setEtc(rs.getString("etc"));
				acom_resrv_hostDTO.setResrv_check(rs.getFloat("reserv_date_check_end") <=0 ? "이용완료" : (rs.getFloat("reserv_date_check_start")>0 ? "이용예정":"이용중"));
				
				
				
				acomList.add(acom_resrv_hostDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return acomList;
	}
	
	@Override
	public long getTotalCount_host_resrv(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) as cnt ");
		sql.append(" from(select a.acom_no as acom_no,a.acom_reserv_no as acom_reserv_no, a.user_id as user_id, b.intro_title as title, a.reserv_date_start as reserv_date_start,a.reserv_date_end as reserv_date_end, a.person as person,a.etc etc");
		sql.append(" from acom_resrv a, acom_info b");
		sql.append(" where a.acom_no=b.acom_no and b.host_id=?)");
		
		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
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
	public Acom_infoDTO get_Acom(long no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Acom_infoDTO acom_infoDTO = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select acom_no,host_id,type,address,price,maxperson,");
		sql.append(" kitchen,parking,toiletries,elevator,tv,aircond,hotwater,washer,wifi,");
		sql.append(" intro_title,intro_cont,intro_etc,intro_pubtrans");
		sql.append(" from   acom_info");
		sql.append(" where  acom_no=?");
	
		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			
			pstmt.setLong(1, no);
			rs   = pstmt.executeQuery();
			if (rs.next()) {
				acom_infoDTO = new Acom_infoDTO();
				acom_infoDTO.setAcom_no(rs.getLong("acom_no"));
				acom_infoDTO.setIntro_title(rs.getString("intro_title"));
				acom_infoDTO.setIntro_cont(rs.getString("intro_cont"));
				acom_infoDTO.setIntro_etc(rs.getString("intro_etc"));
				acom_infoDTO.setIntro_pubtrans(rs.getString("intro_pubtrans"));
				acom_infoDTO.setHost_id(rs.getString("host_id"));
				acom_infoDTO.setType(rs.getString("type"));	
				acom_infoDTO.setAddress(rs.getString("address"));
				acom_infoDTO.setPrice(rs.getLong("price"));
				acom_infoDTO.setMaxperson(rs.getLong("maxperson"));
				acom_infoDTO.setKitchen(rs.getString("kitchen"));
				acom_infoDTO.setParking(rs.getString("parking"));
				acom_infoDTO.setToiletries(rs.getString("toiletries"));
				acom_infoDTO.setElevator(rs.getString("elevator"));
				acom_infoDTO.setTv(rs.getString("tv"));
				acom_infoDTO.setAircond(rs.getString("aircond"));
				acom_infoDTO.setHotwater(rs.getString("hotwater"));
				acom_infoDTO.setWasher(rs.getString("washer"));
				acom_infoDTO.setWifi(rs.getString("wifi"));
				
				
				
	
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return acom_infoDTO;

	}
	
	public int update_Acom(Acom_infoDTO acom_infoDTO,long no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" update acom_info set");
		sql.append("        type=?,price=?,maxperson=?, ");
		sql.append("        kitchen=?,parking=?,toiletries=?,elevator=?,tv=?,aircond=?,hotwater=?,washer=?,wifi=?, ");
		sql.append("        intro_title=?,intro_cont=?,intro_etc=?,intro_pubtrans=? ,mod_date=sysdate");
		sql.append(" 		where  acom_no=?");

		try {
			conn = getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, acom_infoDTO.getType());
			pstmt.setLong(2,acom_infoDTO.getPrice());
			pstmt.setLong(3,acom_infoDTO.getMaxperson());
			pstmt.setString(4, acom_infoDTO.getKitchen());
			pstmt.setString(5, acom_infoDTO.getParking());
			pstmt.setString(6, acom_infoDTO.getToiletries());
			pstmt.setString(7, acom_infoDTO.getElevator());
			pstmt.setString(8, acom_infoDTO.getTv());
			pstmt.setString(9, acom_infoDTO.getAircond());
			pstmt.setString(10, acom_infoDTO.getHotwater());
			pstmt.setString(11, acom_infoDTO.getWasher());
			pstmt.setString(12, acom_infoDTO.getWifi());
			pstmt.setString(13, acom_infoDTO.getIntro_title());
			pstmt.setString(14, acom_infoDTO.getIntro_cont());
			pstmt.setString(15, acom_infoDTO.getIntro_etc());
			pstmt.setString(16, acom_infoDTO.getIntro_pubtrans());
			pstmt.setLong(17, no);
			
			result = pstmt.executeUpdate();
			
		} finally {
			dbClose(pstmt, conn);
		}
		return result;
	}

	@Override
	public int selectAcomCNT(String user_id) throws SQLException {
		int cnt = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append( " select count(*) as cnt ");
		sql.append( " from acom_info         ");
		sql.append( " where host_id = ?      ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			
		}finally {
			dbClose(rs, pstmt, conn);
		}
		
		return cnt;
	}

	@Override
	public void change_Hostyn(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " update users			");
		sql.append( " set host_yn = 'n'     ");
		sql.append( " where user_id = ?	    ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			
			pstmt.executeUpdate();
			
		}finally {
			dbClose(pstmt, conn);
		}
				
	}

	@Override
	public void deleteResrv_SendMsg(long acom_no) throws SQLException {
		List<Acom_resrvDTO> acom_resrvList = new ArrayList();
		String address = null;
		String intro_title = null;
		String host_id = null;
		
		Date date = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd hh:mm ss a");
		String reg_date = sdf.format(date);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//예약중인 id,startday,endday 불러오기
		StringBuffer select_resrv = new StringBuffer();
		select_resrv.append( "select user_id,reserv_date_start,reserv_date_end  ");
		select_resrv.append( "from acom_resrv      								");
		select_resrv.append( "where acom_no = ?     							");
		select_resrv.append( "and reserv_date_start > sysdate   				");
		
		//호스트 acom_info address,intro_title 불러오기
		StringBuffer select_info = new StringBuffer();
		select_info.append( " select host_id,address,intro_title ");
		select_info.append( " from acom_info			 ");
		select_info.append( " where acom_no = ?			 ");
		
		
		//예약한 사람들에게 admin이 메시지 보내기			
		StringBuffer msg_AdminToResrv = new StringBuffer();
		msg_AdminToResrv.append(" insert into message(message_no,sender_id,getter_id,content,reg_date)" );
		msg_AdminToResrv.append(" values (message_no.nextval,'admin',?,?,?)" );
		
		
		//예약한 사람들에게 Host가 메시지 보내기
		StringBuffer msg_HostToResrv = new StringBuffer();
		msg_HostToResrv.append(" insert into message(message_no,sender_id,getter_id,content,reg_date)" );
		msg_HostToResrv.append(" values (message_no.nextval,?,?,?,?)" );
		
		//admin이 호스트에게 메시지 보내기
		StringBuffer msgAdminToHost = new StringBuffer();
		msgAdminToHost.append(" insert into message(message_no,sender_id,getter_id,content,reg_date)" );
		msgAdminToHost.append(" values (message_no.nextval,'admin',?,?,?)" );	
		
		
		//예약 내역 삭제하기
		StringBuffer delete_resrv = new StringBuffer();		
		delete_resrv.append( " delete from acom_resrv	");
		delete_resrv.append( " where acom_no = ?	    ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(select_resrv.toString());
			pstmt.setLong(1, acom_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Acom_resrvDTO resrvDTO = new Acom_resrvDTO();
				resrvDTO.setUser_id(rs.getString("user_id"));
				resrvDTO.setReserv_date_start(rs.getDate("reserv_date_start"));
				resrvDTO.setReserv_date_end(rs.getDate("reserv_date_end"));
				
				acom_resrvList.add(resrvDTO);
			}
			
			pstmt = conn.prepareStatement(select_info.toString());
			pstmt.setLong(1, acom_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				host_id = rs.getString("host_id");
				address = rs.getString("address");
				intro_title = rs.getString("intro_title");
			}
			
			for(int i=0;i<acom_resrvList.size();i++) {
				pstmt = conn.prepareStatement(msg_AdminToResrv.toString());
				
				StringBuffer msg_content1 = new StringBuffer();
				msg_content1.append( " 안녕하세요."+ acom_resrvList.get(i).getUser_id() +"님, AIR-ICON 입니다. \n");
				msg_content1.append( acom_resrvList.get(i).getReserv_date_start() +" 부터 " +  acom_resrvList.get(i).getReserv_date_end() + " 까지 예약해주신 \n");
				msg_content1.append( address + " 에 있는 " +intro_title + "숙소가 \n");
				msg_content1.append( " 호스트의 개인 사정으로 인해 더이상 호스팅을 할수 없게 되었습니다. \n");
				msg_content1.append( " 부득히하게 취소된점 송구드리며, \n");
				msg_content1.append( " 더욱더 나은 서비스를 제공하기 위해 최선을 다하겠습니다.    \n");
			
				pstmt.setString(1, acom_resrvList.get(i).getUser_id());
				pstmt.setString(2, msg_content1.toString());
				pstmt.setString(3, reg_date);
				
				pstmt.executeUpdate();
			}
			
			for(int i=0;i<acom_resrvList.size();i++) {
				pstmt = conn.prepareStatement(msg_HostToResrv.toString());
				
				StringBuffer msg_content2 = new StringBuffer();
				msg_content2.append( acom_resrvList.get(i).getUser_id() + " 님 죄송합니다. \n");
				msg_content2.append( " 개인 사정으로 인해 \n");
				msg_content2.append( acom_resrvList.get(i).getReserv_date_start() +" 부터 " +  acom_resrvList.get(i).getReserv_date_end() + " 까지 예약해주신 \n");
				msg_content2.append( address + "에 있는 "+ intro_title + " 숙소를 \n");
				msg_content2.append( " 더이상 호스팅 할수 없게 되어 자동으로 예약을 취소하게 되었습니다. \n");
			
				pstmt.setString(1, host_id);
				pstmt.setString(2, acom_resrvList.get(i).getUser_id());
				pstmt.setString(3, msg_content2.toString());
				pstmt.setString(4, reg_date);
				
				pstmt.executeUpdate();
			}
			
			pstmt = conn.prepareStatement(msgAdminToHost.toString());
			
			StringBuffer msg_content3 = new StringBuffer();
			msg_content3.append( address +" 에 위치한 " +intro_title +" 의 숙소가 \n");
			msg_content3.append( " 삭제 되었습니다. \n");
			
			pstmt.setString(1, host_id);
			pstmt.setString(2, msg_content3.toString());
			pstmt.setString(3, reg_date);
			
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(delete_resrv.toString());
			
			pstmt.setLong(1, acom_no);
			
			pstmt.executeUpdate();
				
		} finally {
			dbClose(rs, pstmt, conn);
		}
		
	}

	@Override
	public void deleteBookmark(long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " delete from bookmark		");
		sql.append( " where acom_no = ?	    	");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			
			pstmt.executeUpdate();
			
		}finally {
			dbClose(pstmt, conn);
		}
		
	}

	@Override
	public void deleteImg(long acom_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " delete from images		");
		sql.append( " where acom_no = ?	    	");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, acom_no);
			
			pstmt.executeUpdate();
			
		}finally {
			dbClose(pstmt, conn);
		}
		
	}

	@Override
	public List<ImagesDTO> selectImg(long acom_no) throws SQLException {
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
				
				list.add(imgDTO);
			}
			
		}finally {
			dbClose(pstmt, conn);
		}
	
		return list;
	}

	@Override
	public void insert_or_update_img(ImagesDTO imgDTO) throws SQLException {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer select_cnt = new StringBuffer();		
		select_cnt.append( " select count(*) as cnt  ");
		select_cnt.append( " from images 	    	 ");
		select_cnt.append( " where acom_no = ?	     ");
		select_cnt.append( " and no = ?			     ");
		
		StringBuffer insert_img = new StringBuffer();
		insert_img.append( " insert into images(acom_no,no,origin_file,save_file) ");
		insert_img.append( " values(?,?,?,?)                                      ");
			
		StringBuffer update_img = new StringBuffer();
		update_img.append( " update images			");
		update_img.append( " set origin_file = ? ,  ");
		update_img.append( "     save_file = ?      ");
		update_img.append( " where acom_no = ?      ");
		update_img.append( " and   no =?            ");		
		            		  
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(select_cnt.toString());
			pstmt.setLong(1, imgDTO.getAcom_no());
			pstmt.setInt(2, imgDTO.getNo());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt == 0 ) {
				pstmt = conn.prepareStatement(insert_img.toString());
				pstmt.setLong(1,imgDTO.getAcom_no());
				pstmt.setInt(2,imgDTO.getNo());
				pstmt.setString(3,imgDTO.getOrigin_file());
				pstmt.setString(4,imgDTO.getSave_file());
				
				pstmt.executeUpdate();
				
			}else if(cnt == 1) {
				pstmt = conn.prepareStatement(update_img.toString());
				
				pstmt.setString(1,imgDTO.getOrigin_file());
				pstmt.setString(2,imgDTO.getSave_file());
				pstmt.setLong(3,imgDTO.getAcom_no());
				pstmt.setInt(4,imgDTO.getNo());
				
				pstmt.executeUpdate();
				
			}
			
		}finally {
			dbClose(rs, pstmt, conn);
		}
		
	}
	
}
