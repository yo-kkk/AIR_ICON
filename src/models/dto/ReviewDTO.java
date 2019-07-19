package models.dto;

import java.io.Serializable;
import java.sql.Date;

public class ReviewDTO implements Serializable{
	private long review_no;
	private long acom_no;
	private String user_id;
	private String content;
	private float rate;
	private	Date reg_date;
	private	Date mod_date;
	private long acom_reserv_no;
	
	
	
	public long getReview_no() {
		return review_no;
	}
	public void setReview_no(long review_no) {
		this.review_no = review_no;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	
	public long getAcom_reserv_no() {
		return acom_reserv_no;
	}
	public void setAcom_reserv_no(long acom_reserv_no) {
		this.acom_reserv_no = acom_reserv_no;
	}
	
	
	@Override
	public String toString() {
		return "ReviewDTO [review_no=" + review_no + ", acom_no=" + acom_no + ", user_id=" + user_id + ", content="
				+ content + ", rate=" + rate + ", reg_date=" + reg_date + ", mod_date=" + mod_date + ", acom_reserv_no="
				+ acom_reserv_no + "]";
	}
	
	
	public ReviewDTO(long review_no, long acom_no, String user_id, String content, float rate, Date reg_date,
			Date mod_date, long acom_reserv_no) {
		super();
		this.review_no = review_no;
		this.acom_no = acom_no;
		this.user_id = user_id;
		this.content = content;
		this.rate = rate;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
		this.acom_reserv_no = acom_reserv_no;
	}
	public ReviewDTO() {
	
	}



	
	
	
	
}
