package com.ssafy.webex.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.webex.model.dto.QnADto;

public interface QnADao {
	public void write(QnADto qnaDto) throws SQLException;
	public QnADto show(int no) throws SQLException;
	public void modifyInfo(QnADto qnaDto) throws SQLException;
	public void delete(int no) throws SQLException;
	public QnADto getInfo(int no) throws SQLException;
	public List<QnADto> list(int currentPage, int sizePerPage) throws SQLException;
	public int getTotalCount() throws SQLException;
}
