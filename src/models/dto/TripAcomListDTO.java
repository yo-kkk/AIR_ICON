package models.dto;

import java.sql.Date;

public class TripAcomListDTO {
	private long acom_no;
	private long acom_reserv_no;
	private String host_id;
	private long price;
	private String intro_title;
	private Date reserv_date_start;
	private Date reserv_date_end;
	private int reviewCheck;

	public TripAcomListDTO() {
		
	}
	
	public long getAcom_no() {
		return acom_no;
	}
	public void setAcom_no(long acom_no) {
		this.acom_no = acom_no;
	}
	public String getHost_id() {
		return host_id;
	}
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getIntro_title() {
		return intro_title;
	}
	public void setIntro_title(String intro_title) {
		this.intro_title = intro_title;
	}
	public Date getReserv_date_start() {
		return reserv_date_start;
	}
	public void setReserv_date_start(Date reserv_date_start) {
		this.reserv_date_start = reserv_date_start;
	}
	public Date getReserv_date_end() {
		return reserv_date_end;
	}
	public void setReserv_date_end(Date reserv_date_end) {
		this.reserv_date_end = reserv_date_end;
	}

	
	public long getAcom_reserv_no() {
		return acom_reserv_no;
	}

	public void setAcom_reserv_no(long acom_reserv_no) {
		this.acom_reserv_no = acom_reserv_no;
	}

	@Override
	public String toString() {
		return "TripAcomListDTO [acom_no=" + acom_no + ", acom_reserv_no=" + acom_reserv_no + ", host_id=" + host_id
				+ ", price=" + price + ", intro_title=" + intro_title + ", reserv_date_start=" + reserv_date_start
				+ ", reserv_date_end=" + reserv_date_end + "]";
	}

	public int getReviewCheck() {
		return reviewCheck;
	}

	public void setReviewCheck(int reviewCheck) {
		this.reviewCheck = reviewCheck;
	}

	
	

}
