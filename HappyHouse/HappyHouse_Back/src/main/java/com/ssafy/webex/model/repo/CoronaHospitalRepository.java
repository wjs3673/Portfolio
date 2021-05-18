package com.ssafy.webex.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.CoronaHospitalDto;
import com.ssafy.webex.util.PageNavigation;

public interface CoronaHospitalRepository {
	public abstract List<CoronaHospitalDto> searchAll(Map<String, Object> param) throws SQLException;
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	public List<CoronaHospitalDto> selectAll(int startNum,int count);
	public int getCount();
}
