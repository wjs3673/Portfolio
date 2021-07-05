package com.project.four.model.dto;

public class ChatDto {
	
	private String chat_id;
	private String gone_id;
	private String writer;
	private String content;
	private String chat_dt;
	private int flower;
	private int flag;
	
	public ChatDto() {
		super();
	}

	public ChatDto(String chat_id, String gone_id, String writer, String content, String chat_dt, int flower, int flag) {
		super();
		this.chat_id = chat_id;
		this.gone_id = gone_id;
		this.writer = writer;
		this.content = content;
		this.chat_dt = chat_dt;
		this.flower = flower;
		this.flag = flag;
	}

	public String getChat_id() {
		return chat_id;
	}

	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}

	public String getGone_id() {
		return gone_id;
	}

	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChat_dt() {
		return chat_dt;
	}

	public void setChat_dt(String chat_dt) {
		this.chat_dt = chat_dt;
	}

	public int getFlower() {
		return flower;
	}

	public void setFlower(int flower) {
		this.flower = flower;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ChatDto [chat_id=" + chat_id + ", gone_id=" + gone_id + ", writer=" + writer + ", content=" + content
				+ ", chat_dt=" + chat_dt + ", flower=" + flower + ", flag=" + flag + "]";
	}
	
}
