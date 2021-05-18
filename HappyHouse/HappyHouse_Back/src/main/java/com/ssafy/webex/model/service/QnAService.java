package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.QnADto;
import com.ssafy.webex.util.PageNavigation;

public interface QnAService {
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	public List<QnADto> list(Map<String, String> map) throws Exception;
	public void write(QnADto qnaDto) throws Exception;
	public QnADto show(int no) throws SQLException;
	public void modifyInfo(QnADto qnaDto) throws SQLException;
	public void delete(int no) throws SQLException;
	public QnADto getInfo(int no) throws SQLException;
}
