package com.project.four.model.dto;

public class FuneralListDto {

	private int search_id;
	private String search_nm;
	private String search_addr;
	
	public FuneralListDto() {
		super();
	}

	public FuneralListDto(int search_id, String search_nm, String search_addr) {
		super();
		this.search_id = search_id;
		this.search_nm = search_nm;
		this.search_addr = search_addr;
	}

	public int getSearch_id() {
		return search_id;
	}

	public void setSearch_id(int search_id) {
		this.search_id = search_id;
	}

	public String getSearch_nm() {
		return search_nm;
	}

	public void setSearch_nm(String search_nm) {
		this.search_nm = search_nm;
	}

	public String getSearch_addr() {
		return search_addr;
	}

	public void setSearch_addr(String search_addr) {
		this.search_addr = search_addr;
	}

	@Override
	public String toString() {
		return "FuneralListDto [search_id=" + search_id + ", search_nm=" + search_nm + ", search_addr=" + search_addr
				+ "]";
	}
	
}
