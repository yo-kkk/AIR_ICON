package models.dto;

import java.io.Serializable;
import java.sql.Date;

public class UsersDTO implements Serializable{
	private String user_id;
	private String password;
	private String name;
	private String tel;
	private Date birth;
	private String gender;
	private String address;
	private String email;
	private String host_yn;
	private String card_num;
	private String card_date;
	private String card_cvc;
	private String card_password;
	private Date reg_date;
	private Date mod_date;
	private String[] card_num_sub= {"","","",""};
	
	
	public UsersDTO() {}
		
	
	
	public UsersDTO(String user_id, String password, String name, String tel, Date birth, String gender, String address,
			String email, String host_yn, String card_num, String card_date, String card_cvc,
			String card_password, Date reg_date, Date mod_date) {
		this.user_id = user_id;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.birth = birth;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.host_yn = host_yn;
		this.card_num = card_num;
		this.card_date = card_date;
		this.card_cvc = card_cvc;
		this.card_password = card_password;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
	}



	@Override
	public String toString() {
		return "UsersDTO [user_id=" + user_id + ", password=" + password + ", name=" + name + ", tel=" + tel
				+ ", birth=" + birth + ", gender=" + gender + ", address=" + address + ", email=" + email + ", host_yn="
				+ host_yn + ", card_num=" + card_num + ", card_date=" + card_date
				+ ", card_cvc=" + card_cvc + ", card_password=" + card_password + ", reg_date=" + reg_date
				+ ", mod_date=" + mod_date + "]";
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHost_yn() {
		return host_yn;
	}
	public void setHost_yn(String host_yn) {
		this.host_yn = host_yn;
	}
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	public String getCard_date() {
		return card_date;
	}
	public void setCard_date(String card_date) {
		this.card_date = card_date;
	}
	public String getCard_cvc() {
		return card_cvc;
	}
	public void setCard_cvc(String card_cvc) {
		this.card_cvc = card_cvc;
	}
	public String getCard_password() {
		return card_password;
	}
	public void setCard_password(String card_password) {
		this.card_password = card_password;
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
	

	
	
	public String[] getCard_num_sub() {
		return card_num_sub;
	}




	public void setCard_num_sub(String card_num) {
		this.card_num_sub[0] = card_num.substring(0,4);
		this.card_num_sub[1] = card_num.substring(4,8);
		this.card_num_sub[2] = card_num.substring(8,12);
		this.card_num_sub[3] = card_num.substring(12,16);
	}
	
	
}
