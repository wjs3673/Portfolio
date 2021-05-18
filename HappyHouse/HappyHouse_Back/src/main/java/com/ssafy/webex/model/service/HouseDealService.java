package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.HouseDealDto;
import com.ssafy.webex.util.PageNavigation;


public interface HouseDealService {
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	public List<HouseDealDto> searchAll(Map<String, String> map) throws Exception;
	public List<HouseDealDto> searchAptName(Map<String, String> map) throws Exception;
	public List<HouseDealDto> searchDong(String dongName) throws Exception;
	
	public HouseDealDto show(int no) throws SQLException;
	public List<HouseDealDto> getAptDealName(String aptName) throws Exception;
}
