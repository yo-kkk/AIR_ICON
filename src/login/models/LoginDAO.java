package login.models;

import java.sql.SQLException;

import models.dto.UsersDTO;

public interface LoginDAO {
	int logincheck(UsersDTO usersDTO) throws SQLException;

	String get_Hostyn(String user_id) throws SQLException;
}
