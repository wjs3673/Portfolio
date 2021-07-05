package com.project.four.model.dto;
/*
PRO_ID INT AUTO_INCREMENT,
GONE_ID VARCHAR(20) NOT NULL,
ENTER_DT timestamp NOT NULL,
LEAVE_DT timestamp NOT NULL,
START_DT timestamp NOT NULL,
END_DT timestamp NOT NULL,
KIND INT NOT NULL,
 */
public class ProcedureDto {

	private int pro_id;
	private String gone_id;
	private String enter_dt;
	private String leave_dt;
	private String start_dt;
	private String end_dt;
	private int kind;
	private int open;
	
	public ProcedureDto() {
		super();
	}

	public ProcedureDto(int pro_id, String gone_id, String enter_dt, String leave_dt, String start_dt, String end_dt,
			int kind, int open) {
		super();
		this.pro_id = pro_id;
		this.gone_id = gone_id;
		this.enter_dt = enter_dt;
		this.leave_dt = leave_dt;
		this.start_dt = start_dt;
		this.end_dt = end_dt;
		this.kind = kind;
		this.open = open;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public String getGone_id() {
		return gone_id;
	}

	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}

	public String getEnter_dt() {
		return enter_dt;
	}

	public void setEnter_dt(String enter_dt) {
		this.enter_dt = enter_dt;
	}

	public String getLeave_dt() {
		return leave_dt;
	}

	public void setLeave_dt(String leave_dt) {
		this.leave_dt = leave_dt;
	}

	public String getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}

	public String getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "ProcedureDto [pro_id=" + pro_id + ", gone_id=" + gone_id + ", enter_dt=" + enter_dt + ", leave_dt="
				+ leave_dt + ", start_dt=" + start_dt + ", end_dt=" + end_dt + ", kind=" + kind + ", open=" + open
				+ "]";
	}
	
}
