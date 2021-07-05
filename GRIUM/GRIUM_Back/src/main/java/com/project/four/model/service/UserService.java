package com.project.four.model.service;

import com.project.four.model.dto.UserDto;

public interface UserService {
	
	public int signup(UserDto user); //KY
	
	UserDto userLogin(String email, String password) throws Exception; //LS
	
	int edit(UserDto user) throws Exception; // SB
	
	public int checkOut(String passemail) throws Exception;
	
	public int withdraw(UserDto user);
	
	UserDto overid(String line) throws Exception; //KY userid 중복확인할꺼에요~
	UserDto overemail(String email) throws Exception; //KY useremail 중복확인
	
	public UserDto findPassword(String email, String name) throws Exception;

	public UserDto existGet(String email)throws Exception; //KY getout에 존재 확인

	public int newPassword(String email, String newPassword) throws Exception; //KY 임시비밀번호발급

	public String sendAuthMail(String mail) throws Exception;

	public int authemail(String email, String auth_num) throws Exception;

	public int delauth(String email, String auth_num) throws Exception;

}
