package com.ssafy.webex.model.dto;

public class CoronaClinicDto {
	private String update_date;
	private char testable;
	private String city;
	private String gugun;
	private String clinic_name;
	private String address;
	private String opening_hours_weekday;
	private String opening_hours_sat;
	private String opening_hours_sun_ph;
	private String phone;
	
	public CoronaClinicDto() {}
	public CoronaClinicDto(String update_date, char testable, String city, String gugun, String clinic_name,
			String address, String opening_hours_weekday, String opening_hours_sat, String opening_hours_sun_ph,
			String phone) {
		super();
		this.update_date = update_date;
		this.testable = testable;
		this.city = city;
		this.gugun = gugun;
		this.clinic_name = clinic_name;
		this.address = address;
		this.opening_hours_weekday = opening_hours_weekday;
		this.opening_hours_sat = opening_hours_sat;
		this.opening_hours_sun_ph = opening_hours_sun_ph;
		this.phone = phone;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public char getTestable() {
		return testable;
	}
	public void setTestable(char testable) {
		this.testable = testable;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getClinic_name() {
		return clinic_name;
	}
	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpening_hours_weekday() {
		return opening_hours_weekday;
	}
	public void setOpening_hours_weekday(String opening_hours_weekday) {
		this.opening_hours_weekday = opening_hours_weekday;
	}
	public String getOpening_hours_sat() {
		return opening_hours_sat;
	}
	public void setOpening_hours_sat(String opening_hours_sat) {
		this.opening_hours_sat = opening_hours_sat;
	}
	public String getOpening_hours_sun_ph() {
		return opening_hours_sun_ph;
	}
	public void setOpening_hours_sun_ph(String opening_hours_sun_ph) {
		this.opening_hours_sun_ph = opening_hours_sun_ph;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "CoronaClinic [update_date=" + update_date + ", testable=" + testable + ", city=" + city + ", gugun="
				+ gugun + ", clinic_name=" + clinic_name + ", address=" + address + ", opening_hours_weekday="
				+ opening_hours_weekday + ", opening_hours_sat=" + opening_hours_sat + ", opening_hours_sun_ph="
				+ opening_hours_sun_ph + ", phone=" + phone + "]";
	}
}
