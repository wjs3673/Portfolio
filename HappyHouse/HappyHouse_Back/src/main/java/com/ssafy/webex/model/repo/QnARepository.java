package com.ssafy.webex.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.QnADto;

public interface QnARepository {
	public List<QnADto> list(Map<String, Object> param) throws SQLException;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	public void write(QnADto qnaDto) throws SQLException;
	public void modifyInfo(QnADto qnaDto) throws SQLException;
	public void delete(int no) throws SQLException;
	
	public QnADto show(int no) throws SQLException;
	public QnADto getInfo(int no) throws SQLException;
}
