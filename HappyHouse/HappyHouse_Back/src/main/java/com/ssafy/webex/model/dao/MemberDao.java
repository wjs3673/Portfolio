package com.ssafy.webex.model.dao;

import java.sql.SQLException;

import com.ssafy.webex.model.dto.MemberDto;

public interface MemberDao {
	public MemberDto getPwd(String id, String name, String phone) throws SQLException;
}
