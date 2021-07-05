package com.project.four.model.dto;
/*
FAMILY_ID INT AUTO_INCREMENT,
GONE_ID VARCHAR(20) NOT NULL,
FAMILY_NM VARCHAR(20) NOT NULL,
RELATION VARCHAR(10) NOT NULL,
PHONE VARCHAR(20) NOT NULL,
 */
public class FamilyDto {

	private int family_id;
	private String gone_id;
	private String family_nm;
	private String relation;
	private String phone;
	
	public FamilyDto() {
		super();
	}

	public FamilyDto(int family_id, String gone_id, String family_nm, String relation, String phone) {
		super();
		this.family_id = family_id;
		this.gone_id = gone_id;
		this.family_nm = family_nm;
		this.relation = relation;
		this.phone = phone;
	}

	public int getFamily_id() {
		return family_id;
	}

	public void setFamily_id(int family_id) {
		this.family_id = family_id;
	}

	public String getGone_id() {
		return gone_id;
	}

	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}

	public String getFamily_nm() {
		return family_nm;
	}

	public void setFamily_nm(String family_nm) {
		this.family_nm = family_nm;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "FamilyDto [family_id=" + family_id + ", gone_id=" + gone_id + ", family_nm=" + family_nm + ", relation="
				+ relation + ", phone=" + phone + "]";
	}
	
}
