package detail_acom.models;

import java.sql.SQLException;
import java.util.List;

import models.dto.Acom_infoDTO;
import models.dto.Acom_resrvDTO;
import models.dto.ImagesDTO;
import models.dto.MessageDTO;
import models.dto.ReviewDTO;

public interface Detail_acomDAO {
	Acom_infoDTO getAcom_infoDTO(long acom_no) throws SQLException;
	List<ImagesDTO> getImagesDTO(long acom_no) throws SQLException;
	List<ReviewDTO> getReviewList(long acom_no) throws SQLException;
	long reviewDetailCount(long acom_no) throws SQLException;
	List<Acom_resrvDTO> getResrvDate(long acom_no) throws SQLException;
	void send_msg(MessageDTO msgDTO) throws SQLException;
	String selectHost_id(long acom_no) throws SQLException;
	public List<ImagesDTO> getImg(long acom_no) throws SQLException;
}
