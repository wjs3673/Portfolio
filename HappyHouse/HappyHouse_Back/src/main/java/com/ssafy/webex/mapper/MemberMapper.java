package com.ssafy.webex.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.webex.model.dto.MemberDto;

@Mapper
public interface MemberMapper {
	public MemberDto login(MemberDto memberDto) throws SQLException;
}
