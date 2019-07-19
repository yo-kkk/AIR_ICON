package models.dto;

import java.io.Serializable;

public class BookmarkDTO implements Serializable{
	
	private long bookmark_no;
	private	long acom_no;
	private String user_id;
	public long getBookmark_no() {
		return bookmark_no;
	}
	public void setBookmark_no(long bookmark_no) {
		this.bookmark_no = bookmark_no;
	}
	public long getAcom_no() {
		return acom_no;
	}
	public void setAcom_no(long acom_no) {
		this.acom_no = acom_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "BookmarkDTO [bookmark_no=" + bookmark_no + ", acom_no=" + acom_no + ", user_id=" + user_id + "]";
	}
	public BookmarkDTO(long bookmark_no, long acom_no, String user_id) {
		super();
		this.bookmark_no = bookmark_no;
		this.acom_no = acom_no;
		this.user_id = user_id;
	}
	public BookmarkDTO() {
		
	}

	
	
	
	
	

	
	
}
