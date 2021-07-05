package com.project.four.model.dto;

public class RipDto {

	private int rip_id;
	private int board_id;
	private int photo_id;
	private String user_id;
	private int type;
	
	public RipDto() {
		super();
	}

	public RipDto(int rip_id, int board_id, int photo_id, String user_id, int type) {
		super();
		this.rip_id = rip_id;
		this.board_id = board_id;
		this.photo_id = photo_id;
		this.user_id = user_id;
		this.type = type;
	}

	public int getRip_id() {
		return rip_id;
	}

	public void setRip_id(int rip_id) {
		this.rip_id = rip_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RipDto [rip_id=" + rip_id + ", board_id=" + board_id + ", photo_id=" + photo_id + ", user_id=" + user_id
				+ ", type=" + type + "]";
	}
	
}
