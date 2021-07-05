package com.project.four.model.dto;

public class UserDto {
	/*
	 * 
	 * email VARCHAR(30) NOT NULL, name VARCHAR(20) NOT NULL, password VARCHAR(20)
	 * NOT NULL, photo LONGBLOB, phone VARCHAR(15) NOT NULL,
	 */
	private String user_id;
	private String email;
	private String password;
	private String name;
	private String phone;

	public UserDto(String user_id, String email, String password, String name, String phone) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public UserDto() {
		super();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserDto [user_id=" + user_id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + "]";
	}

}