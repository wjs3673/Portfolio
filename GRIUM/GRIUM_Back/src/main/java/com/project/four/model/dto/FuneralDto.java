package com.project.four.model.dto;
/*
FUNERAL_ID INT AUTO_INCREMENT,

GONE_ID VARCHAR(20) NOT NULL,

FUNERAL_NM VARCHAR(50) NOT NULL,

FUNERAL_ADR VARCHAR(100) NOT NULL,
 */
public class FuneralDto {

	private int funeral_id;
	private String gone_id;
	private String funeral_nm;
	private String funeral_adr;
	
	public FuneralDto() {
		super();
	}

	public FuneralDto(int funeral_id, String gone_id, String funeral_nm, String funeral_adr) {
		super();
		this.funeral_id = funeral_id;
		this.gone_id = gone_id;
		this.funeral_nm = funeral_nm;
		this.funeral_adr = funeral_adr;
	}

	public int getFuneral_id() {
		return funeral_id;
	}

	public void setFuneral_id(int funeral_id) {
		this.funeral_id = funeral_id;
	}

	public String getGone_id() {
		return gone_id;
	}

	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}

	public String getFuneral_nm() {
		return funeral_nm;
	}

	public void setFuneral_nm(String funeral_nm) {
		this.funeral_nm = funeral_nm;
	}

	public String getFuneral_adr() {
		return funeral_adr;
	}

	public void setFuneral_adr(String funeral_adr) {
		this.funeral_adr = funeral_adr;
	}

	@Override
	public String toString() {
		return "FuneralDto [funeral_id=" + funeral_id + ", gone_id=" + gone_id + ", funeral_nm=" + funeral_nm
				+ ", funeral_adr=" + funeral_adr + "]";
	}
	
}
