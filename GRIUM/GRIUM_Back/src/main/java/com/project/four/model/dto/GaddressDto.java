package com.project.four.model.dto;

public class GaddressDto {

	/* 파일저장 주소 DTO
	 * create table GADDRESS_TB(
		PHOTO_ID int not null,
		SERVER_PHOTO varchar(50) not null,
		SERVER_PATH varchar(100) not null,
		ORIGIN_PHOTO varchar(50) not null,
		primary key(SERVER_PATH),
		foreign key(PHOTO_ID) references PHOTO_TB(PHOTO_ID)
		);
	 * 
	 */
	
	private String server_photo;
	private String server_path;
	private String origin_photo;
	
	public GaddressDto() {
		
	}
	
	public GaddressDto(String server_photo, String server_path, String origin_photo) {
		super();
		this.server_photo = server_photo;
		this.server_path = server_path;
		this.origin_photo = origin_photo;
	}

	public String getServer_photo() {
		return server_photo;
	}

	public void setServer_photo(String server_photo) {
		this.server_photo = server_photo;
	}

	public String getServer_path() {
		return server_path;
	}

	public void setServer_path(String server_path) {
		this.server_path = server_path;
	}

	public String getOrigin_photo() {
		return origin_photo;
	}

	public void setOrigin_photo(String origin_photo) {
		this.origin_photo = origin_photo;
	}

	@Override
	public String toString() {
		return "GaddressDto [server_photo=" + server_photo + ", server_path=" + server_path + ", origin_photo="
				+ origin_photo + "]";
	}
	
	

}
