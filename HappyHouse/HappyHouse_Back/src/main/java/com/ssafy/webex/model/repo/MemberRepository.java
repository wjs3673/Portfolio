package com.ssafy.webex.model.repo;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.webex.model.dto.MemberDto;

public interface MemberRepository {
	//public MemberDto login(Map<String, String> map) throws SQLException;
	public MemberDto login(MemberDto memberDto) throws SQLException;
	
	public void join(MemberDto memberDto);
	public void modifyInfo(MemberDto memberDto) throws SQLException;
	public MemberDto getInfo(String userid) throws SQLException;
	public void delete(String userid) throws SQLException;
	public MemberDto getPwd(String id, String name, String phone) throws SQLException;
}
