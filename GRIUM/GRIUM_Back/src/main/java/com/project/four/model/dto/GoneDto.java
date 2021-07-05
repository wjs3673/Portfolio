package com.project.four.model.dto;

public class GoneDto {
	
	private String gone_id;
	private String user_id;
	private String gone_nm;
	private String birth_dt;
	private String death_dt;
	private String photo_path;
	private String server_photo;
	private String origin_photo;
	
	public GoneDto() {
		super();
	}

	public GoneDto(String gone_id, String user_id, String gone_nm, String birth_dt, String death_dt, String photo_path,
			String server_photo, String origin_photo) {
		super();
		this.gone_id = gone_id;
		this.user_id = user_id;
		this.gone_nm = gone_nm;
		this.birth_dt = birth_dt;
		this.death_dt = death_dt;
		this.photo_path = photo_path;
		this.server_photo = server_photo;
		this.origin_photo = origin_photo;
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

	public String getGone_nm() {
		return gone_nm;
	}

	public void setGone_nm(String gone_nm) {
		this.gone_nm = gone_nm;
	}

	public String getBirth_dt() {
		return birth_dt;
	}

	public void setBirth_dt(String birth_dt) {
		this.birth_dt = birth_dt;
	}

	public String getDeath_dt() {
		return death_dt;
	}

	public void setDeath_dt(String death_dt) {
		this.death_dt = death_dt;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public String getServer_photo() {
		return server_photo;
	}

	public void setServer_photo(String server_photo) {
		this.server_photo = server_photo;
	}

	public String getOrigin_photo() {
		return origin_photo;
	}

	public void setOrigin_photo(String origin_photo) {
		this.origin_photo = origin_photo;
	}

	@Override
	public String toString() {
		return "GoneDto [gone_id=" + gone_id + ", user_id=" + user_id + ", gone_nm=" + gone_nm + ", birth_dt="
				+ birth_dt + ", death_dt=" + death_dt + ", photo_path=" + photo_path + ", server_photo=" + server_photo
				+ ", origin_photo=" + origin_photo + "]";
	}
	
}
