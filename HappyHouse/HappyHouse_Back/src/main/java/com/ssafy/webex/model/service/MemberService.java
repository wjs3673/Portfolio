package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.webex.model.dto.MemberDto;

public interface MemberService {
	//public MemberDto login(Map<String, String> map) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	
	public void join(MemberDto memberDto) throws Exception;
	public void modifyInfo(MemberDto memberDto) throws SQLException;
	public MemberDto getInfo(String userid) throws Exception;
	public void delete(String userid) throws Exception;
	
//	public MemberDto login(MemberDto memberDto) throws Exception;
	
//	public MemberDto getPwd(String id, String name, String phone) throws SQLException;
}
