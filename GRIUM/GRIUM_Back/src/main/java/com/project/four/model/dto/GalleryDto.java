package com.project.four.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class GalleryDto {

	/*
	 * CREATE TABLE PHOTO_TB( PHOTO_ID INT AUTO_INCREMENT, GONE_ID VARCHAR(20) NOT
	 * NULL, SERVER_PHOTO VARCHAR(50) NOT NULL, PHOTO_PATH VARCHAR(100) NOT NULL,
	 * ORIGIN_PHOTO VARCHAR(50) NOT NULL, WRITER VARCHAR(20) NOT NULL, PHOTO_DT
	 * TIMESTAMP NOT NULL, COUNT INT, SECRET INT NOT NULL, PRIMARY KEY (PHOTO_ID),
	 * FOREIGN KEY (GONE_ID) REFERENCES GONE_TB(GONE_ID) );
	 */

	private int photo_id;
	private String gone_id;
	private String user_id;
	private String email;
	private String writer; // 아마 userid이지 않을까?
	private Timestamp photo_dt;
	private int count;
	private int secret;
	private int flag;
	private int alerttype;
	private int riptype;
	private String name;
	
	private List<GaddressDto> gaddress;
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getGone_id() {
		return gone_id;
	}
	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getPhoto_dt() {
		return photo_dt;
	}
	public void setPhoto_dt(Timestamp photo_dt) {
		this.photo_dt = photo_dt;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSecret() {
		return secret;
	}
	public void setSecret(int secret) {
		this.secret = secret;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List<GaddressDto> getGaddress() {
		return gaddress;
	}
	public void setGaddress(List<GaddressDto> gaddress) {
		this.gaddress = gaddress;
	}
	public int getAlerttype() {
		return alerttype;
	}
	public void setAlerttype(int alerttype) {
		this.alerttype = alerttype;
	}
	public int getRiptype() {
		return riptype;
	}
	public void setRiptype(int riptype) {
		this.riptype = riptype;
	}
	public GalleryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public GalleryDto(int photo_id, String gone_id, String user_id, String email, String writer, Timestamp photo_dt,
			int count, int secret, int flag, int alerttype, int riptype, String name, List<GaddressDto> gaddress) {
		super();
		this.photo_id = photo_id;
		this.gone_id = gone_id;
		this.user_id = user_id;
		this.email = email;
		this.writer = writer;
		this.photo_dt = photo_dt;
		this.count = count;
		this.secret = secret;
		this.flag = flag;
		this.alerttype = alerttype;
		this.riptype = riptype;
		this.name = name;
		this.gaddress = gaddress;
	}
	
	@Override
	public String toString() {
		return "GalleryDto [photo_id=" + photo_id + ", gone_id=" + gone_id + ", user_id=" + user_id + ", email=" + email
				+ ", writer=" + writer + ", photo_dt=" + photo_dt + ", count=" + count + ", secret=" + secret
				+ ", flag=" + flag + ", alerttype=" + alerttype + ", riptype=" + riptype + ", name=" + name
				+ ", gaddress=" + gaddress + "]";
	}
	
	
}
