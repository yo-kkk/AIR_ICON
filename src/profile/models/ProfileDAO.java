package profile.models;

import java.sql.SQLException;
import java.util.List;

import models.dto.Acom_infoDTO;
import models.dto.ImagesDTO;
import models.dto.PageDTO;
import models.dto.UsersDTO;



public interface ProfileDAO {
	int updateAccount(UsersDTO usersDTO,String id) throws SQLException;
	UsersDTO getAccount(String id) throws SQLException;
	int updateProfile(UsersDTO usersDTO,String id) throws SQLException;
	UsersDTO getUserInfo(String id) throws SQLException;
	List<Bookmark_profileDTO> getBookmarkList(PageDTO pageDTO,String id) throws SQLException;
	List<Review_profileDTO> getReview_host_List(PageDTO pageDTO,String id) throws SQLException;
	List<Acom_infoDTO> getAcom_info(PageDTO pageDTO,String id) throws SQLException;
	int deleteBookmarkList(String id,long bookmark_no) throws SQLException;
	
	long getTotalCount_bookmark(String id) throws SQLException;
	long getTotalCount_acom(String id) throws SQLException;
	long getTotalCount_host_review(String id) throws SQLException;
	long getTotalCount_user_review(String id) throws SQLException;
	List<Review_profileDTO> getReview_user_List(PageDTO pageDTO,String id) throws SQLException;
	List<Acom_resrv_hostDTO> getResrv_host_List(PageDTO pageDTO, String id) throws SQLException;
	long getTotalCount_host_resrv(String id) throws SQLException;
	Acom_infoDTO get_Acom(long no) throws SQLException;
	int update_Acom(Acom_infoDTO acom_infoDTO,long no) throws SQLException;
	int deleteReview(String id,long review_no) throws SQLException;
	
	//Acom_deleteAction method
	int deleteAcomList(String id,long acom_no) throws SQLException;
	void deleteImg(long acom_no) throws SQLException;
	void deleteResrv_SendMsg(long acom_no) throws SQLException;
	void deleteBookmark(long acom_no) throws SQLException;
	int deleteReview_acom (String id,long acom_no) throws SQLException;

	int selectAcomCNT(String user_id) throws SQLException;
	void change_Hostyn(String user_id) throws SQLException;
	
	List<ImagesDTO> selectImg(long acom_no) throws SQLException;
	void insert_or_update_img(ImagesDTO imgDTO) throws SQLException;
}
