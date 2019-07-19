package message.models;

import java.sql.SQLException;
import java.util.List;

import message.controllers.MessageDTO_list;
import models.dto.MessageDTO;

public interface MessageDAO {
	
	List<MessageDTO_list> getMessageList(String user_id) throws SQLException;
	List<MessageDTO> getMessageDetail(String user_id,String other_id) throws SQLException;
	void sendMessage(MessageDTO messageDTO) throws SQLException;
	

}
