package com.project.four.model.dto;
/*
ACCOUNT VARCHAR(30) NOT NULL,
GONE_ID VARCHAR(20) NOT NULL,
HOLDER VARCHAR(30) NOT NULL,
BANK VARCHAR(30) NOT NULL,
 */
public class CondolanceDto {

	private String account;
	private String gone_id;
	private String holder;
	private String bank;
	
	public CondolanceDto() {
		super();
	}

	public CondolanceDto(String account, String gone_id, String holder, String bank) {
		super();
		this.account = account;
		this.gone_id = gone_id;
		this.holder = holder;
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getGone_id() {
		return gone_id;
	}

	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "CondolanceDto [account=" + account + ", gone_id=" + gone_id + ", holder=" + holder + ", bank=" + bank
				+ "]";
	}
	
}
