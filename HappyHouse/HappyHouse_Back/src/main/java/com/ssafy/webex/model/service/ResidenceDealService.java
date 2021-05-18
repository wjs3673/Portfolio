package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.ResidenceBuyDto;
import com.ssafy.webex.model.dto.ResidenceRentDto;
import com.ssafy.webex.util.PageNavigation;


public interface ResidenceDealService {
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	public PageNavigation makePageNavigationRent(Map<String, String> map) throws Exception;
	public List<ResidenceBuyDto> searchBuy(Map<String, String> map) throws Exception;
	public List<ResidenceRentDto> searchRent(Map<String, String> map) throws Exception;
	List<ResidenceBuyDto> searchBuyName(Map<String, String> map) throws Exception;
	List<ResidenceBuyDto> searchBuyDongName(Map<String, String> map) throws Exception;
	public ResidenceBuyDto show(int no) throws SQLException;
	public ResidenceRentDto showRent(int no) throws SQLException;
	public List<ResidenceRentDto> getResidenceInDong(String dong) throws Exception;
	public List<ResidenceBuyDto> getResidenceDealName(String buildingName) throws Exception;
	
}
