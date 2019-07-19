package become_host.models;

import java.sql.SQLException;

import models.dto.Acom_infoDTO;
import models.dto.ImagesDTO;

public interface Become_hostDAO {
	long insert_Acom_and_return_no(Acom_infoDTO acom_infoDTO) throws SQLException;
	void insert_img(ImagesDTO imgDTO) throws SQLException;
	void change_hostyn(String user_id) throws SQLException;
	void send_message(Acom_infoDTO acom_infoDTO) throws SQLException;
}
