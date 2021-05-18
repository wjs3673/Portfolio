package com.ssafy.webex.model.dto;

public class BookmarkDto {
	private int no;
	private String user_id;
	private String dongName;
	private String buildingno;
	private String buildingName;
	
	
	@Override
	public String toString() {
		return "BookmarkDto [no=" + no + ", user_id=" + user_id + ", dongName=" + dongName + ", buildingno="
				+ buildingno + ", buildingName=" + buildingName + "]";
	}


	public BookmarkDto(int no, String user_id, String dongName, String buildingno, String buildingName) {
		super();
		this.no = no;
		this.user_id = user_id;
		this.dongName = dongName;
		this.buildingno = buildingno;
		this.buildingName = buildingName;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getDongName() {
		return dongName;
	}


	public void setDongName(String dongName) {
		this.dongName = dongName;
	}


	public String getBuildingno() {
		return buildingno;
	}


	public void setBuildingno(String buildingno) {
		this.buildingno = buildingno;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public BookmarkDto() {
		super();
	}
	
	
	
	
	
}






