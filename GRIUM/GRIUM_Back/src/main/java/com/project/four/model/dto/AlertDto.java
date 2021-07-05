package com.project.four.model.dto;
/*
ALERT_ID int NOT NULL auto_increment,
BOARD_ID int,
CHAT_ID int,
PHOTO_ID int,
USER_ID VARCHAR(13) NOT NULL,
ALERT_DT TIMESTEMP NOT NULL,
READ_CHECK int NOT NULL,
TYPE int NOT NULL,
 */
public class AlertDto {
	
	private int alert_id;
	private int board_id;
	private int chat_id;
	private int photo_id;
	private String user_id;
	private String gone_id;
	private String alert_dt;
	private int read_check;
	private int type;
	
	public AlertDto() {
		super();
	}

	public AlertDto(int alert_id, int board_id, int chat_id, int photo_id, String user_id, String gone_id,
			String alert_dt, int read_check, int type) {
		super();
		this.alert_id = alert_id;
		this.board_id = board_id;
		this.chat_id = chat_id;
		this.photo_id = photo_id;
		this.user_id = user_id;
		this.gone_id = gone_id;
		this.alert_dt = alert_dt;
		this.read_check = read_check;
		this.type = type;
	}

	public int getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(int alert_id) {
		this.alert_id = alert_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getChat_id() {
		return chat_id;
	}

	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
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

	public String getGone_id() {
		return gone_id;
	}

	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}

	public String getAlert_dt() {
		return alert_dt;
	}

	public void setAlert_dt(String alert_dt) {
		this.alert_dt = alert_dt;
	}

	public int getRead_check() {
		return read_check;
	}

	public void setRead_check(int read_check) {
		this.read_check = read_check;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AlertDto [alert_id=" + alert_id + ", board_id=" + board_id + ", chat_id=" + chat_id + ", photo_id="
				+ photo_id + ", user_id=" + user_id + ", gone_id=" + gone_id + ", alert_dt=" + alert_dt
				+ ", read_check=" + read_check + ", type=" + type + "]";
	}
	
}
