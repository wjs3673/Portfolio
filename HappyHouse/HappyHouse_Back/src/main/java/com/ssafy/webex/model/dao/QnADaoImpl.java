package com.ssafy.webex.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.webex.model.dto.QnADto;
import com.ssafy.webex.util.DBUtil;

public class QnADaoImpl implements QnADao {
	@Override
	public void write(QnADto qnaDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("insert into qna (writer, title, content, regtime) \n");
			insertMember.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, qnaDto.getId());
			System.out.println("sql>>>>>>>" + qnaDto.getId());
			pstmt.setString(2, qnaDto.getTitle());
			System.out.println("sql>>>>>>>" + qnaDto.getTitle());
			pstmt.setString(3, qnaDto.getContent());
			System.out.println("sql>>>>>>>" + qnaDto.getContent());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public QnADto show(int no) throws SQLException {
		QnADto qnaDto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, writer, title, content, regtime \n");
			sql.append("from notice \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qnaDto = new QnADto();
				qnaDto.setNo(rs.getInt("no"));
				qnaDto.setId(rs.getString("writer"));
				qnaDto.setTitle(rs.getString("title"));
				qnaDto.setContent(rs.getString("content"));
				qnaDto.setRegtime(rs.getString("regtime"));
				System.out.println("show 완료!");
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return qnaDto;
	}

	@Override
	public void modifyInfo(QnADto qnaDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("update notice \n");
			insertMember.append("set title = ?, content = ?, regtime = now() \n");
			insertMember.append("where no = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, qnaDto.getTitle());
			pstmt.setString(2, qnaDto.getContent());
			pstmt.setInt(3, qnaDto.getNo());
			System.out.println("정보수정>>>>>>>" + qnaDto.getTitle());
			System.out.println("정보수정>>>>>>>" + qnaDto.getContent());
			System.out.println("정보수정>>>>>>>" + qnaDto.getRegtime());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

	}

	@Override
	public void delete(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("delete from qna \n");
			insertMember.append("where no = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			System.out.println("삭제완료!");
		} finally {
			
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public QnADto getInfo(int no) throws SQLException {
		QnADto qnaDto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, writer, title, content, regtime \n");
			sql.append("from qna \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				qnaDto = new QnADto();
				qnaDto.setNo(rs.getInt("no"));
				System.out.println("getinfo>>>>" + qnaDto.getNo());
				qnaDto.setId(rs.getString("writer"));
				System.out.println("getinfo>>>>" + qnaDto.getId());
				qnaDto.setTitle(rs.getString("title"));
				System.out.println("getinfo>>>>" + qnaDto.getTitle());
				qnaDto.setContent(rs.getString("content"));
				System.out.println("getinfo>>>>" + qnaDto.getContent());
				qnaDto.setRegtime(rs.getString("regtime"));
				System.out.println("getinfo>>>>" + qnaDto.getRegtime());
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return qnaDto;
	}

	@Override
	public List<QnADto> list(int currentPage, int sizePerPage) throws SQLException {
		List<QnADto> list = new ArrayList<QnADto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no, writer, title, content, regtime \n");
			sql.append("from qna \n");
			sql.append("order by no desc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnADto qnaDto = new QnADto();
				qnaDto.setNo(rs.getInt("no"));
				qnaDto.setId(rs.getString("writer"));
				qnaDto.setTitle(rs.getString("title"));
				qnaDto.setContent(rs.getString("content"));
				qnaDto.setRegtime(rs.getString("regtime"));

				list.add(qnaDto);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return list;
	}

	@Override
	public int getTotalCount() throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(no) \n");
			sql.append("from qna \n");
			sql.append("order by no desc \n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			System.out.println(cnt);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return cnt;
	}
}
