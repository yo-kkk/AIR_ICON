package models.dto;

import java.io.Serializable;
import java.util.Date;

public class Acom_infoDTO implements Serializable{

	private long acom_no			;	
	private String host_id			;	
	private String type				;
	private String address			;
	private long price				;	
	private long maxperson			;	
	private String loc_no			;	
	private String kitchen			;	
	private String parking			;	
	private String toiletries		;	
	private String elevator			;
	private String tv				;	
	private String aircond			;	
	private String hotwater			;
	private String washer			;	
	private String wifi				;
	private String intro_title		;	
	private String intro_cont		;	
	private String intro_etc		;	
	private String intro_pubtrans	;
	private Date reg_dt				;	
	private Date mod_dt				;
	
	public Acom_infoDTO() {}

	public Acom_infoDTO(long acom_no, String host_id, String type, String address, long price, long maxperson,
			String loc_no, String kitchen, String parking, String toiletries, String elevator, String tv,
			String aircond, String hotwater, String washer, String wifi, String intro_title, String intro_cont,
			String intro_etc, String intro_pubtrans, Date reg_dt, Date mod_dt) {
		super();
		this.acom_no = acom_no;
		this.host_id = host_id;
		this.type = type;
		this.address = address;
		this.price = price;
		this.maxperson = maxperson;
		this.loc_no = loc_no;
		this.kitchen = kitchen;
		this.parking = parking;
		this.toiletries = toiletries;
		this.elevator = elevator;
		this.tv = tv;
		this.aircond = aircond;
		this.hotwater = hotwater;
		this.washer = washer;
		this.wifi = wifi;
		this.intro_title = intro_title;
		this.intro_cont = intro_cont;
		this.intro_etc = intro_etc;
		this.intro_pubtrans = intro_pubtrans;
		this.reg_dt = reg_dt;
		this.mod_dt = mod_dt;
	}
	
	@Override
	public String toString() {
		return "Acom_infoDTO [acom_no=" + acom_no + ", host_id=" + host_id + ", type=" + type + ", address=" + address
				+ ", price=" + price + ", maxperson=" + maxperson + ", loc_no=" + loc_no + ", kitchen=" + kitchen
				+ ", parking=" + parking + ", toiletries=" + toiletries + ", elevator=" + elevator + ", tv=" + tv
				+ ", aircond=" + aircond + ", hotwater=" + hotwater + ", washer=" + washer + ", wifi=" + wifi
				+ ", intro_title=" + intro_title + ", intro_cont=" + intro_cont + ", intro_etc=" + intro_etc
				+ ", intro_pubtrans=" + intro_pubtrans + ", reg_dt=" + reg_dt + ", mod_dt=" + mod_dt + "]";
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getMaxperson() {
		return maxperson;
	}
	public void setMaxperson(long maxperson) {
		this.maxperson = maxperson;
	}
	public String getLoc_no() {
		return loc_no;
	}
	public void setLoc_no(String loc_no) {
		this.loc_no = loc_no;
	}
	public String getKitchen() {
		return kitchen;
	}
	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getToiletries() {
		return toiletries;
	}
	public void setToiletries(String toiletries) {
		this.toiletries = toiletries;
	}
	public String getElevator() {
		return elevator;
	}
	public void setElevator(String elevator) {
		this.elevator = elevator;
	}
	public String getTv() {
		return tv;
	}
	public void setTv(String tv) {
		this.tv = tv;
	}
	public String getAircond() {
		return aircond;
	}
	public void setAircond(String aircond) {
		this.aircond = aircond;
	}
	public String getHotwater() {
		return hotwater;
	}
	public void setHotwater(String hotwater) {
		this.hotwater = hotwater;
	}
	public String getWasher() {
		return washer;
	}
	public void setWasher(String washer) {
		this.washer = washer;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	public String getIntro_title() {
		return intro_title;
	}
	public void setIntro_title(String intro_title) {
		this.intro_title = intro_title;
	}
	public String getIntro_cont() {
		return intro_cont;
	}
	public void setIntro_cont(String intro_cont) {
		this.intro_cont = intro_cont;
	}
	public String getIntro_etc() {
		return intro_etc;
	}
	public void setIntro_etc(String intro_etc) {
		this.intro_etc = intro_etc;
	}
	public String getIntro_pubtrans() {
		return intro_pubtrans;
	}
	public void setIntro_pubtrans(String intro_pubtrans) {
		this.intro_pubtrans = intro_pubtrans;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	public Date getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
	}
	
	
}
