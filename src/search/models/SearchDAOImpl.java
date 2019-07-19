package search.models;

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

import models.dto.PageDTO;
import models.dto.SearchDTO;
import models.dto.SearchListDTO;

public class SearchDAOImpl implements SearchDAO {
	private static final SearchDAO searchDAO = new SearchDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(SearchDAOImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private SearchDAOImpl() {
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
	
	public static SearchDAO getInstance() {
		return searchDAO;
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
	public List<SearchListDTO> searchResult(PageDTO pageDTO, SearchDTO searchDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SearchListDTO> list = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select m.*");
		sql.append(" from(select rownum as rnum, n.*");
		sql.append(" from(select l.no, l.save_file, l.origin_file, k.acom_no, k.intro_title, k.price, k.rate");
		sql.append(" from(select g.acom_no, g.intro_title, g.price, h.rate");
		sql.append(" from(select distinct f.acom_no, f.intro_title, f.price"); 
		sql.append(" from(select e.intro_title, e.acom_no, e.price, e.maxperson"); 
		sql.append(" from(select acom_no, intro_title, price, maxperson"); 
		sql.append(" from acom_info ");
		sql.append(" where (address like '%' || ? || '%' ");
		sql.append(" OR intro_title like '%' || ? || '%') ");
		sql.append(" ) e )f, ");
		sql.append(" (");
		sql.append(" select acom_no");
		sql.append(" from acom_resrv ");
		sql.append(" where acom_no not in(select acom_no");
		sql.append(" from (select a.reserv_date_start, a.endday, a.acom_no ");
		sql.append(" from (select acom_no, acom_reserv_no, reserv_date_start, reserv_date_end-1 as endday ");
		sql.append(" from acom_resrv ");
		sql.append(" where reserv_date_start between ? and ?");
		sql.append(" or reserv_date_start <=  ?) a");
		sql.append(" where a.endday between ? and ?");
		sql.append(" or  a.endday > ?))");
		sql.append(" )d ");
		sql.append(" where d.acom_no=f.acom_no ");
		sql.append(" and f.maxperson >= ? ");
		sql.append(" union ");
		sql.append(" select acom_no, intro_title, price"); 
		sql.append(" from acom_info ");
		sql.append(" where (address like '%' || ? || '%' ");
		sql.append(" OR intro_title like '%' || ? || '%') ");
		sql.append(" and acom_no not in (select acom_no ");
		sql.append(" from acom_resrv) ");
		sql.append(" and maxperson >= ?)g, ");
		sql.append(" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate ");
		sql.append(" from (select acom_no, rate ");
		sql.append(" from review ");
		sql.append(" union ");
		sql.append(" select acom_no, 0 as rate ");
		sql.append(" from acom_info ");
		sql.append(" where acom_no not in (select acom_no ");
		sql.append(" from review)) ");
		sql.append(" group by acom_no) h ");
		sql.append(" where g.acom_no = h.acom_no)k, (select acom_no, no, save_file, origin_file ");
		sql.append(" from images ");
		sql.append(" where no = 1)l ");
		sql.append(" where k.acom_no = l.acom_no(+) ");
		sql.append(" order by rate desc) n ) m ");
		sql.append(" where rnum between ? and ?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());
			pstmt.setDate(3, searchDTO.getStartday());
			pstmt.setDate(4, searchDTO.getEndday());
			
			pstmt.setDate(5, searchDTO.getStartday());
			pstmt.setDate(6, searchDTO.getStartday());
			pstmt.setDate(7, searchDTO.getEndday());
			pstmt.setDate(8, searchDTO.getEndday());
			
			pstmt.setInt(9, searchDTO.getPerson());
			pstmt.setString(10, searchDTO.getLocation());
			pstmt.setString(11, searchDTO.getLocation());
			pstmt.setInt(12, searchDTO.getPerson());
			pstmt.setLong(13, pageDTO.getStartNum());
			pstmt.setLong(14, pageDTO.getEndNum());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SearchListDTO searchListDTO = new SearchListDTO();
				searchListDTO.setIntro_title(rs.getString("intro_title"));
				searchListDTO.setPrice(rs.getLong("price"));
				searchListDTO.setAcom_no(rs.getLong("acom_no"));
				searchListDTO.setRate(rs.getFloat("rate"));

				searchListDTO.setSave_file(rs.getString("save_file"));
				
				list.add(searchListDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public long getTotalCount(SearchDTO searchDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		

		
		sql.append(" select count(*) as cnt");
		sql.append(" from(select l.no, l.save_file, l.origin_file, k.acom_no, k.intro_title, k.price, k.rate");
		sql.append(" from(select g.acom_no, g.intro_title, g.price, h.rate");
		sql.append(" from(select distinct f.acom_no, f.intro_title, f.price"); 
		sql.append(" from(select e.intro_title, e.acom_no, e.price, e.maxperson"); 
		sql.append(" from(select acom_no, intro_title, price, maxperson"); 
		sql.append(" from acom_info ");
		sql.append(" where (address like '%' || ? || '%' ");
		sql.append(" OR intro_title like '%' || ? || '%') ");
		sql.append(" ) e )f, ");
		sql.append(" (");
		sql.append(" select acom_no");
		sql.append(" from acom_resrv ");
		sql.append(" where acom_no not in(select acom_no");
		sql.append(" from (select a.reserv_date_start, a.endday, a.acom_no ");
		sql.append(" from (select acom_no, acom_reserv_no, reserv_date_start, reserv_date_end-1 as endday ");
		sql.append(" from acom_resrv ");
		sql.append(" where reserv_date_start between ? and ?");
		sql.append(" or reserv_date_start <=  ?) a");
		sql.append(" where a.endday between ? and ?");
		sql.append(" or  a.endday > ?))");
		sql.append(" )d ");
		sql.append(" where d.acom_no=f.acom_no ");
		sql.append(" and f.maxperson >= ? ");
		sql.append(" union ");
		sql.append(" select acom_no, intro_title, price"); 
		sql.append(" from acom_info ");
		sql.append(" where (address like '%' || ? || '%' ");
		sql.append(" OR intro_title like '%' || ? || '%') ");
		sql.append(" and acom_no not in (select acom_no ");
		sql.append(" from acom_resrv) ");
		sql.append(" and maxperson >= ?)g, ");
		sql.append(" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate ");
		sql.append(" from (select acom_no, rate ");
		sql.append(" from review ");
		sql.append(" union ");
		sql.append(" select acom_no, 0 as rate ");
		sql.append(" from acom_info ");
		sql.append(" where acom_no not in (select acom_no ");
		sql.append(" from review)) ");
		sql.append(" group by acom_no) h ");
		sql.append(" where g.acom_no = h.acom_no)k, (select acom_no, no, save_file, origin_file ");
		sql.append(" from images ");
		sql.append(" where no = 1)l ");
		sql.append(" where k.acom_no = l.acom_no(+) ");
		sql.append(" order by rate desc)");
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());
			pstmt.setDate(3, searchDTO.getStartday());
			pstmt.setDate(4, searchDTO.getEndday());
			
			pstmt.setDate(5, searchDTO.getStartday());
			pstmt.setDate(6, searchDTO.getStartday());
			pstmt.setDate(7, searchDTO.getEndday());
			pstmt.setDate(8, searchDTO.getEndday());
			
			pstmt.setInt(9, searchDTO.getPerson());
			pstmt.setString(10, searchDTO.getLocation());
			pstmt.setString(11, searchDTO.getLocation());
			pstmt.setInt(12, searchDTO.getPerson());
			

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
	public List<SearchListDTO> searchNewResult_no(PageDTO pageDTO, SearchDTO searchDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SearchListDTO> list = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select f.*");
		sql.append(" from(select rownum as rnum, e.*");
		sql.append(" from(select c.acom_no, c.intro_title, c.price, c.rate, d.save_file");
		sql.append(" from(select a.acom_no, a.intro_title, a.price, b.rate from");
		sql.append(" (select acom_no, intro_title, price, maxperson");
		sql.append(" from acom_info");
		sql.append(" where (address like '%' || ? || '%'");
		sql.append(" OR intro_title like '%' || ? || '%')");
		sql.append(" ) a,");
		sql.append(" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate");
		sql.append(" from (select acom_no, rate");
		sql.append(" from review");
		sql.append(" union");
		sql.append(" select acom_no, 0 as rate");
		sql.append(" from acom_info");
		sql.append(" where (address like '%' || ? || '%'");
		sql.append(" OR intro_title like '%' || ? || '%')");
		sql.append(" and acom_no not in (select acom_no");
		sql.append(" from review))");
		sql.append(" group by acom_no) b");
		sql.append(" where a.acom_no = b.acom_no)c,");
		sql.append(" (select acom_no, no, save_file");
		sql.append(" from images");
		sql.append(" where no = 1)d");
		sql.append(" where c.acom_no = d.acom_no");
		sql.append(" order by rate desc) e ) f");
		sql.append(" where rnum between ? and ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());
			pstmt.setString(3, searchDTO.getLocation());
			pstmt.setString(4, searchDTO.getLocation());
			pstmt.setLong(5, pageDTO.getStartNum());
			pstmt.setLong(6, pageDTO.getEndNum());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SearchListDTO searchListDTO = new SearchListDTO();
				searchListDTO.setIntro_title(rs.getString("intro_title"));
				searchListDTO.setPrice(rs.getLong("price"));
				searchListDTO.setAcom_no(rs.getLong("acom_no"));
				searchListDTO.setRate(rs.getFloat("rate"));
				searchListDTO.setSave_file(rs.getString("save_file"));
				
				list.add(searchListDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public long getTotalCountRe(SearchDTO searchDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		

		sql.append(" select count(*) as cnt from");
		sql.append(" (select c.acom_no, c.intro_title, c.price, c.rate, d.save_file");
		sql.append(" from(select a.acom_no, a.intro_title, a.price, b.rate from");
		sql.append(" (select acom_no, intro_title, price, maxperson");
		sql.append(" from acom_info");
		sql.append(" where (address like '%' || ? || '%'");
		sql.append(" OR intro_title like '%' || ? || '%')");
		sql.append(" ) a,");
		sql.append(" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate");
		sql.append(" from (select acom_no, rate");
		sql.append(" from review");
		sql.append(" union");
		sql.append(" select acom_no, 0 as rate");
		sql.append(" from acom_info");
		sql.append(" where (address like '%' || ? || '%'");
		sql.append(" OR intro_title like '%' || ? || '%')");
		sql.append(" and acom_no not in (select acom_no");
		sql.append(" from review))");
		sql.append(" group by acom_no) b");
		sql.append(" where a.acom_no = b.acom_no)c,");
		sql.append(" (select acom_no, no, save_file");
		sql.append(" from images");
		sql.append(" where no = 1)d");
		sql.append(" where c.acom_no = d.acom_no)");

		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());
			pstmt.setString(3, searchDTO.getLocation());
			pstmt.setString(4, searchDTO.getLocation());
			

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong("cnt");
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return count;
	}

	
	

	//검색바 필터==============================================
	@Override
	public List<SearchListDTO> searchNewResult_noFilter(PageDTO pageDTO, SearchDTO searchDTO, String col)
			throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SearchListDTO> list = new ArrayList<>();
		
		String sql;
		
		sql =  " select f.*"
				+" from(select rownum as rnum, e.*"
				+" from(select c.acom_no, c.intro_title, c.price, c.rate, d.save_file"
				+" from(select a.acom_no, a.intro_title, a.price, b.rate from"
				+" (select acom_no, intro_title, price, maxperson"
				+" from acom_info"
				+" where (address like '%' || ? || '%'"
				+" OR intro_title like '%' || ? || '%')"
				+ col
				+" ) a,"
				+" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate"
				+" from (select acom_no, rate"
				+" from review"
				+" union"
				+" select acom_no, 0 as rate"
				+" from acom_info"
				+" where (address like '%' || ? || '%'"
				+" OR intro_title like '%' || ? || '%')"
				+" and acom_no not in (select acom_no"
				+" from review))"
				+" group by acom_no) b"
				+" where a.acom_no = b.acom_no)c,"
				+" (select acom_no, no, save_file"
				+" from images"
				+" where no = 1)d"
				+" where c.acom_no = d.acom_no"
				+" order by rate desc) e ) f"
				+" where rnum between ? and ? " ;
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());		
			pstmt.setString(3, searchDTO.getLocation());
			pstmt.setString(4, searchDTO.getLocation());
			pstmt.setLong(5, pageDTO.getStartNum());
			pstmt.setLong(6, pageDTO.getEndNum());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SearchListDTO searchListDTO = new SearchListDTO();
				searchListDTO.setIntro_title(rs.getString("intro_title"));
				searchListDTO.setPrice(rs.getLong("price"));
				searchListDTO.setAcom_no(rs.getLong("acom_no"));
				searchListDTO.setRate(rs.getFloat("rate"));
				searchListDTO.setSave_file(rs.getString("save_file"));
				
				list.add(searchListDTO);
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return list;

	}

	
	
	
	
	@Override
	public long getTotalCountReFilter(SearchDTO searchDTO, String col) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql;
		
		sql =    " select count(*) as cnt from"
				+" (select c.acom_no, c.intro_title, c.price, c.rate, d.save_file"
				+" from(select a.acom_no, a.intro_title, a.price, b.rate from"
				+" (select acom_no, intro_title, price, maxperson"
				+" from acom_info"
				+" where (address like '%' || ? || '%'"
				+" OR intro_title like '%' || ? || '%')"
				+ col
				+" ) a,"
				+" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate"
				+" from (select acom_no, rate"
				+" from review"
				+" union"
				+" select acom_no, 0 as rate"
				+" from acom_info"
				+" where (address like '%' || ? || '%'"
				+" OR intro_title like '%' || ? || '%')"
				+" and acom_no not in (select acom_no"
				+" from review))"
				+" group by acom_no) b"
				+" where a.acom_no = b.acom_no)c,"
				+" (select acom_no, no, save_file"
				+" from images"
				+" where no = 1)d"
				+" where c.acom_no = d.acom_no)";
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());
			pstmt.setString(3, searchDTO.getLocation());
			pstmt.setString(4, searchDTO.getLocation());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong("cnt");
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return count;
	}

	//메인필터============================================
	
	@Override
	public List<SearchListDTO> searchResultFilter(PageDTO pageDTO, SearchDTO searchDTO, String col)
			throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SearchListDTO> list = new ArrayList<>();
		
		String sql;
		
		sql = "select m.* "
				+" from(select rownum as rnum, n.* "
				+" from(select l.no, l.save_file, l.origin_file, k.acom_no, k.intro_title, k.price, k.rate" 
				+" from(select g.acom_no, g.intro_title, g.price, h.rate "
				+" from(select distinct f.acom_no, f.intro_title, f.price "
				+" from(select e.intro_title, e.acom_no, e.price, e.maxperson "
				+" from(select acom_no, intro_title, price, maxperson "
				+" from acom_info "
				+" where (address like '%' || ? || '%' "
				+" OR intro_title like '%' || ? || '%') "
				+col
				+" ) e )f, "
				+" ("
				+" select acom_no "
				+" from acom_resrv "
				+" where acom_no not in(select acom_no"
				+" from (select a.reserv_date_start, a.endday, a.acom_no "
				+" from (select acom_no, acom_reserv_no, reserv_date_start, reserv_date_end-1 as endday "
				+" from acom_resrv "
				+" where reserv_date_start between ? and ?"
				+" or reserv_date_start <=  ?) a"
				+" where a.endday between ? and ?"
				+" or  a.endday > ?))"
				+" )d "
				+" where d.acom_no=f.acom_no "
				+" and f.maxperson >= ?"
				+" union "
				+" select acom_no, intro_title, price "
				+" from acom_info "
				+" where (address like '%' || ? || '%' "
				+" OR intro_title like '%' || ? || '%') "
				+col
				+" and acom_no not in (select acom_no "
				+" from acom_resrv) "
				+" and maxperson >= ?)g, "
				+" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate "
				+" from (select acom_no, rate "
				+" from review "
				+" union "
				+" select acom_no, 0 as rate "
				+" from acom_info "
				+" where acom_no not in (select acom_no "
				+" from review)) "
				+" group by acom_no) h "
				+" where g.acom_no = h.acom_no)k, (select acom_no, no, save_file, origin_file "
				+" from images "
				+" where no = 1)l "
				+" where k.acom_no = l.acom_no(+) "
				+" order by rate desc) n) m "
				+" where rnum between ? and ?";
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());
			pstmt.setDate(3, searchDTO.getStartday());
			pstmt.setDate(4, searchDTO.getEndday());
			
			pstmt.setDate(5, searchDTO.getStartday());
			pstmt.setDate(6, searchDTO.getStartday());
			pstmt.setDate(7, searchDTO.getEndday());
			pstmt.setDate(8, searchDTO.getEndday());
			
			pstmt.setInt(9, searchDTO.getPerson());
			pstmt.setString(10, searchDTO.getLocation());
			pstmt.setString(11, searchDTO.getLocation());
			pstmt.setInt(12, searchDTO.getPerson());
			pstmt.setLong(13, pageDTO.getStartNum());
			pstmt.setLong(14, pageDTO.getEndNum());
			logger.info("set===========================");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				logger.info("while문====================");
				SearchListDTO searchListDTO = new SearchListDTO();
				searchListDTO.setIntro_title(rs.getString("intro_title"));
				searchListDTO.setPrice(rs.getLong("price"));
				searchListDTO.setAcom_no(rs.getLong("acom_no"));
				searchListDTO.setRate(rs.getFloat("rate"));
				logger.info("타이틀=============="+searchListDTO.getIntro_title());
				searchListDTO.setSave_file(rs.getString("save_file"));
				
				list.add(searchListDTO);
				
				logger.info("리스트0번========="+String.valueOf(list.get(0)));
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	
	@Override
	public long getTotalCountFilter(SearchDTO searchDTO, String col) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql;
		
		sql = " select count(*) as cnt"
				 +" from(select l.no, l.save_file, l.origin_file, k.acom_no, k.intro_title, k.price, k.rate" 
				 +" from(select g.acom_no, g.intro_title, g.price, h.rate "
				 +" from(select distinct f.acom_no, f.intro_title, f.price "
				 +" from(select e.intro_title, e.acom_no, e.price, e.maxperson "
				 +" from(select acom_no, intro_title, price, maxperson "
				 +" from acom_info "
				 +" where (address like '%' || ? || '%' "
				 +" OR intro_title like '%' || ? || '%') "
				 +col
				 +" ) e )f, "
				 +" ("
				 +" select acom_no "
				 +" from acom_resrv "
				 +" where acom_no not in(select acom_no"
				 +" from (select a.reserv_date_start, a.endday, a.acom_no "
				 +" from (select acom_no, acom_reserv_no, reserv_date_start, reserv_date_end-1 as endday "
				 +" from acom_resrv "
				 +" where reserv_date_start between ? and ?"
				 +" or reserv_date_start <=  ?) a"
				 +" where a.endday between ? and ?"
				+" or  a.endday > ?))"
				 +" )d "
				 +" where d.acom_no=f.acom_no "
				 +" and f.maxperson >= ? "
				 +" union "
				 +" select acom_no, intro_title, price "
				 +" from acom_info "
				 +" where (address like '%' || ? || '%' "
				 +" OR intro_title like '%' || ? || '%') "
				 +col
				 +" and acom_no not in (select acom_no "
				 +" from acom_resrv) "
				 +" and maxperson >= ?)g, "
				 +" (select acom_no, TRUNC(AVG(NVL(rate, 0)),1) as rate "
				 +" from (select acom_no, rate "
				 +" from review "
				 +" union "
				 +" select acom_no, 0 as rate "
				 +" from acom_info "
				 +" where acom_no not in (select acom_no "
				 +" from review)) "
				 +" group by acom_no) h "
				 +" where g.acom_no = h.acom_no)k, (select acom_no, no, save_file, origin_file "
				 +" from images "
				 +" where no = 1)l "
				 +" where k.acom_no = l.acom_no(+) "
				 +" order by rate desc) ";

		
		
		long count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchDTO.getLocation());
			pstmt.setString(2, searchDTO.getLocation());
			pstmt.setDate(3, searchDTO.getStartday());
			pstmt.setDate(4, searchDTO.getEndday());
			
			pstmt.setDate(5, searchDTO.getStartday());
			pstmt.setDate(6, searchDTO.getStartday());
			pstmt.setDate(7, searchDTO.getEndday());
			pstmt.setDate(8, searchDTO.getEndday());
			
			pstmt.setInt(9, searchDTO.getPerson());
			pstmt.setString(10, searchDTO.getLocation());
			pstmt.setString(11, searchDTO.getLocation());
			pstmt.setInt(12, searchDTO.getPerson());
			


			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong("cnt");
			}
		} finally {
			dbClose(rs, pstmt, conn);
		}
		
		logger.info("===count:"+count);
		return count;
	}
	
}
