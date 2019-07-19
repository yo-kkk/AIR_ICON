package become_host.models;

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

import models.dto.Acom_infoDTO;
import models.dto.ImagesDTO;

public class Become_hostDAOImpl implements Become_hostDAO {
	private static final Become_hostDAO become_hostDAO = new Become_hostDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(Become_hostDAOImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private Become_hostDAOImpl() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(this.getClass().getResource("").getPath()+"/database.properties"));
			
			url=prop.getProperty("url").trim();
			driver=prop.getProperty("driver").trim();
			username=prop.getProperty("username").trim();
			password=prop.getProperty("password").trim();
			
			Class.forName(driver);
		
		} catch (Exception e) {
			logger.info(e.toString());
		}
	}
	
	public static Become_hostDAO getInstance() {
		return become_hostDAO;
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
	
	public void dbClose(ResultSet rs,PreparedStatement pstmt,Connection conn) {
		if(rs	!=null) try {rs.close();	} catch(Exception e) {}
		if(pstmt!=null) try {pstmt.close();	} catch(Exception e) {}
		if(conn	!=null) try {conn.close();	} catch(Exception e) {}
	}
	
	public void dbClose(PreparedStatement pstmt,Connection conn) {
		if(pstmt!=null) try {pstmt.close();	} catch(Exception e) {}
		if(conn	!=null) try {conn.close();	} catch(Exception e) {}
	}

	@Override
	public long insert_Acom_and_return_no(Acom_infoDTO acom_infoDTO) throws SQLException {
		
		long acom_no = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result=0;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into acom_info(acom_no,host_id,type,address,price,maxperson,               			 	"); 
		sql.append(" 					  kitchen,parking,toiletries,elevator,tv,aircond,hotwater,washer,wifi, 		"); 
		sql.append(" 					  intro_title,intro_cont,intro_etc,intro_pubtrans,reg_date)                 ");
		sql.append(" 			values	 (acom_info_no.nextval,?,?,?,?,?,                                   		"); 
		sql.append(" 					  ?,?,?,?,?,?,?,?,?,                                                  		"); 
		sql.append(" 					  ?,?,?,?,sysdate)                                                        ");
		
		StringBuffer sql2 = new StringBuffer();		
		sql2.append(" select acom_info_no.currval from dual");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, acom_infoDTO.getHost_id());
			pstmt.setString(2, acom_infoDTO.getType());
			pstmt.setString(3, acom_infoDTO.getAddress());
			pstmt.setLong(4, acom_infoDTO.getPrice());
			pstmt.setLong(5, acom_infoDTO.getMaxperson());
			pstmt.setString(6, acom_infoDTO.getKitchen());
			pstmt.setString(7, acom_infoDTO.getParking());
			pstmt.setString(8, acom_infoDTO.getToiletries());
			pstmt.setString(9, acom_infoDTO.getElevator());
			pstmt.setString(10, acom_infoDTO.getTv());
			pstmt.setString(11, acom_infoDTO.getAircond());
			pstmt.setString(12, acom_infoDTO.getHotwater());
			pstmt.setString(13, acom_infoDTO.getWasher());
			pstmt.setString(14, acom_infoDTO.getWifi());
			pstmt.setString(15, acom_infoDTO.getIntro_title());
			pstmt.setString(16, acom_infoDTO.getIntro_cont());
			pstmt.setString(17, acom_infoDTO.getIntro_etc());
			pstmt.setString(18, acom_infoDTO.getIntro_pubtrans());
			
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				acom_no = rs.getLong("currval");
			}
			
		} finally {
			dbClose(rs, pstmt, conn);
		}	
		
		return acom_no;

	}

	@Override
	public void insert_img(ImagesDTO imgDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" insert into images(acom_no,no,origin_file,save_file)"	);
		sql.append(" values(?,?,?,?)"										);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setLong(1, imgDTO.getAcom_no() );
			pstmt.setLong(2, imgDTO.getNo() );
			pstmt.setString(3, imgDTO.getOrigin_file() );
			pstmt.setString(4, imgDTO.getSave_file() );
			
			pstmt.executeUpdate();			
			
		} finally {
			dbClose(pstmt, conn);
		}	
		
	}

	@Override
	public void change_hostyn(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " update users			");
		sql.append( " set host_yn = 'y'     ");
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
	public void send_message(Acom_infoDTO acom_infoDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		StringBuffer content = new StringBuffer();
		
		content.append(" 호스트 등록을 축하드립니다. \n");
		content.append(" " + acom_infoDTO.getIntro_title() +" ");
		content.append(" 로 검색해보실수 있습니다. \n");
		content.append(" 프로필-내 호스트 창에서 사진을 추가해보세요! \n");
		content.append(" 예약이 발생하면 메시지로 알람을 드리겠습니다! \n");
		
		sql.append(" insert into message(message_no,sender_id,getter_id,content,reg_date)  ");
		sql.append(" values(message_no.nextval,'admin',?,?,?)							   ");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString() );
			
			Date date = new Date();
			SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd hh:mm ss a");
			String reg_date = sdf.format(date);
			
			pstmt.setString(1, acom_infoDTO.getHost_id() );
			pstmt.setString(2, content.toString());
			pstmt.setString(3, reg_date);
			
			pstmt.executeUpdate();
			
		} finally {
			dbClose(pstmt, conn);
		}
		
	}

}
