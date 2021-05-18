package com.ssafy.webex.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.webex.model.dto.MemberDto;
import com.ssafy.webex.util.DBUtil;

public class MemberDaoImpl implements MemberDao {
	@Override
	public MemberDto getPwd(String id, String name, String phone) throws SQLException {
		MemberDto memberDto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, password \n");
			sql.append("from user \n");
			sql.append("where id = ? and name = ? and phone = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setId(id);
				memberDto.setName(name);
				memberDto.setPassword(rs.getString("password"));
				memberDto.setPhone(phone);
				System.out.println(memberDto.getId() + "," + memberDto.getPassword() + "," + memberDto.getName());
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return memberDto;
	}
}
