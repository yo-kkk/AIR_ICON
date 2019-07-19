package users.models;

import java.sql.SQLException;

import models.dto.UsersDTO;



public interface UsersDAO {
	public int usersInsert(UsersDTO usersDTO) throws SQLException ;
	public int confirmId(String id) throws Exception;
	public void sendWelcomeMsg(String user_id) throws Exception;
}
