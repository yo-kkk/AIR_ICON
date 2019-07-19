package bookmark.models;

import java.sql.SQLException;

public interface BookmarkDAO {
	public int bookmark_insert (long acom_no,String user_id) throws SQLException;
	public int confirmBookmark(long acom_no,String user_id) throws Exception;

}
