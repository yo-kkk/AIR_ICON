package profile.models;

import java.sql.Date;

public class Acom_resrv_hostDTO {
	
	private long acom_reserv_no;
	private long acom_no;
	private String user_id;
	private int person;
	private Date reserv_date_start;
	private Date reserv_date_end;
	private String etc;
	private Date reg_date;
	private Date mod_date;
	private String resrv_check;
	private String title;
	private String address;
	public long getAcom_reserv_no() {
		return acom_reserv_no;
	}
	public void setAcom_reserv_no(long acom_reserv_no) {
		this.acom_reserv_no = acom_reserv_no;
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
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
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
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
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
	public String getResrv_check() {
		return resrv_check;
	}
	public void setResrv_check(String resrv_check) {
		this.resrv_check = resrv_check;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Acom_resrv_hostDTO [acom_reserv_no=" + acom_reserv_no + ", acom_no=" + acom_no + ", user_id=" + user_id
				+ ", person=" + person + ", reserv_date_start=" + reserv_date_start + ", reserv_date_end="
				+ reserv_date_end + ", etc=" + etc + ", reg_date=" + reg_date + ", mod_date=" + mod_date
				+ ", resrv_check=" + resrv_check + ", title=" + title + ", address=" + address + "]";
	}
	
	public Acom_resrv_hostDTO(long acom_reserv_no, long acom_no, String user_id, int person, Date reserv_date_start,
			Date reserv_date_end, String etc, Date reg_date, Date mod_date, String resrv_check, String title,
			String address) {
		super();
		this.acom_reserv_no = acom_reserv_no;
		this.acom_no = acom_no;
		this.user_id = user_id;
		this.person = person;
		this.reserv_date_start = reserv_date_start;
		this.reserv_date_end = reserv_date_end;
		this.etc = etc;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
		this.resrv_check = resrv_check;
		this.title = title;
		this.address = address;
	}
	public Acom_resrv_hostDTO() {
		
	}
	
	
	

}
