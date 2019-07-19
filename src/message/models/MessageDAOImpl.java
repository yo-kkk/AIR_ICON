package message.models;

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

import message.controllers.MessageDTO_list;
import models.dto.MessageDTO;

public class MessageDAOImpl implements MessageDAO{
	private static final MessageDAO messageDAO = new MessageDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(MessageDAOImpl.class);
	
	private String url;
	private String driver;
	private String username;
	private String password;
	
	private MessageDAOImpl() {
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
	
	public static MessageDAO getInstance() {
		return messageDAO;
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
	public List<MessageDTO_list> getMessageList(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MessageDTO_list> list = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append( " select message_no,sender_id,getter_id,content,reg_date									 	 ");					
		sql.append( " from   message                                                                     ");
		sql.append( " where message_no in (select MAX(message_no)                                        ");
		sql.append( "                      from   message                                                ");
		sql.append( "                      where  (sender_id,getter_id) in (select distinct sender_id,   ");
		sql.append( "                                                                       getter_id    ");
		sql.append( "                                                       from message                 ");
		sql.append( "                                                       where sender_id=?)   		 ");
		sql.append( " group by getter_id                                                                 ");
		sql.append( " )                                                                                  ");
		              
		try {
			conn = getConnection();
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			
			rs	 =pstmt.executeQuery();
			
			while(rs.next()) {
				MessageDTO_list messageDTO_list = new MessageDTO_list();
				
				messageDTO_list.setMessage_no(rs.getLong("message_no"));
				messageDTO_list.setUser_id(rs.getString("sender_id"));
				messageDTO_list.setOther_id(rs.getString("getter_id"));
				messageDTO_list.setContent(rs.getString("content"));
				messageDTO_list.setReg_date(rs.getString("reg_date"));
				
				logger.info(messageDTO_list.toString());
				
				list.add(messageDTO_list);				
			}
			
		} finally {
			dbClose(rs, pstmt, conn);
		}
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append( " select message_no,sender_id,getter_id,content,reg_date									 	  ");					
		sql2.append( " from   message                                                                     ");
		sql2.append( " where  message_no in (select MAX(message_no)                                        ");
		sql2.append( "                      from   message                                                ");
		sql2.append( "                      where  (sender_id,getter_id) in (select distinct sender_id,   ");
		sql2.append( "                                                                       getter_id    ");
		sql2.append( "                                                       from message                 ");
		sql2.append( "                                                       where getter_id=?)   		  ");
		sql2.append( " group by sender_id                                                                 ");
		sql2.append( " )                                                                                  ");
		
		try {
			conn = getConnection();
			
			pstmt=conn.prepareStatement(sql2.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int result = 0;
				
				for(int i=0;i<list.size();i++) {
					
					if( list.get(i).getOther_id().equals(rs.getString("sender_id")) ) {
						
						result = 1;
						
						if(list.get(i).getMessage_no() < rs.getLong("message_no")) {
						
						MessageDTO_list messageDTO_list = new MessageDTO_list();
						
						
						messageDTO_list.setMessage_no(rs.getLong("message_no"));
						messageDTO_list.setUser_id(rs.getString("getter_id"));
						messageDTO_list.setOther_id(rs.getString("sender_id"));
						messageDTO_list.setContent(rs.getString("content"));
						messageDTO_list.setReg_date(rs.getString("reg_date"));
						
						logger.info(messageDTO_list.toString());
						list.set(i,messageDTO_list);	
						
						}
					}
				}
				
				if(result==0) {
					MessageDTO_list messageDTO_list = new MessageDTO_list();					
					
					messageDTO_list.setMessage_no(rs.getLong("message_no"));
					messageDTO_list.setUser_id(rs.getString("getter_id"));
					messageDTO_list.setOther_id(rs.getString("sender_id"));
					messageDTO_list.setContent(rs.getString("content"));
					messageDTO_list.setReg_date(rs.getString("reg_date"));
					
					logger.info(messageDTO_list.toString());
					list.add(messageDTO_list);
				}				
			}
			
		}finally {
			dbClose(rs, pstmt, conn);
		}
		
		for(int i=0;i<list.size();i++) {
			for(int j=i+1;j<list.size();j++) {
				if(list.get(i).getMessage_no()<list.get(j).getMessage_no()) {
					MessageDTO_list temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		
		return list;
	}

	@Override
	public List<MessageDTO> getMessageDetail(String user_id, String other_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MessageDTO> list = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append( "select message_no,sender_id,getter_id,content,reg_date from( 	");
		sql.append( "select * 									   					");
		sql.append( "from message                									");
		sql.append( "where sender_id=?       	    								");
		sql.append( "and   getter_id=?			 									");
		sql.append( "union                      									");
		sql.append( "select *									    				");
		sql.append( "from message               									");
		sql.append( "where sender_id=?           									");
		sql.append( "and   getter_id=?	         									");
		sql.append( ")					         									");
		sql.append( "order by message_no desc				     					");
		
		
		try {
			conn = getConnection();
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, other_id );
			pstmt.setString(2, user_id);
			pstmt.setString(3, user_id);
			pstmt.setString(4, other_id );
			
			rs	 =pstmt.executeQuery();
			
			while(rs.next()) {
				
				MessageDTO messageDTO = new MessageDTO();
				messageDTO.setMessage_no(rs.getLong("message_no"));
				messageDTO.setSender_id(rs.getString("sender_id"));
				messageDTO.setGetter_id(rs.getString("getter_id"));
				messageDTO.setContent(rs.getString("content"));
				messageDTO.setReg_date(rs.getString("reg_date"));
				
				logger.info(messageDTO.toString());
				list.add(messageDTO);				
			}
			
		}finally {
			dbClose(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public void sendMessage(MessageDTO messageDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append( " insert into message(message_no,sender_id,getter_id,content,reg_date)" );
		sql.append( " values(message_no.nextval,?,?,?,?) 							      " );
		
		try {
			conn =getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, messageDTO.getSender_id() );
			pstmt.setString(2, messageDTO.getGetter_id() );
			pstmt.setString(3, messageDTO.getContent()   );
			pstmt.setString(4, messageDTO.getReg_date()  );
			
			
			pstmt.executeUpdate();
			
		} finally {
			dbClose(pstmt,conn);
		}
		
	}
}
