package com.ssafy.webex.model.dto;

public class SidoCodeDto {
	private String sidoCode;
	private String sidoName;
	public SidoCodeDto() {}
	public SidoCodeDto(String sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	@Override
	public String toString() {
		return "SidoCodeDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
	
}
