package com.ssafy.webex.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.HouseDealDto;


public interface HouseDealRepository {
	public abstract List<HouseDealDto> searchAll(Map<String, Object> param) throws ClassNotFoundException, SQLException;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	public List<HouseDealDto> searchAptName(Map<String, Object> param) throws SQLException, ClassNotFoundException;
	public List<HouseDealDto> searchDongName(String dongName) throws SQLException, ClassNotFoundException;
	public HouseDealDto show(int no) throws SQLException;
	public List<HouseDealDto> getAptDealName(String aptName) throws Exception;
}
