package com.ssafy.webex.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.ResidenceBuyDto;
import com.ssafy.webex.model.dto.ResidenceRentDto;


public interface ResidenceDealRepository {
	public List<ResidenceRentDto> searchRent(Map<String, Object> param) throws SQLException, ClassNotFoundException;
	public List<ResidenceBuyDto> searchBuy(Map<String, Object> param) throws SQLException, ClassNotFoundException;
	public List<ResidenceBuyDto> searchBuyName(Map<String, Object> param) throws SQLException, ClassNotFoundException;
	public List<ResidenceBuyDto> searchBuyDongName(Map<String, Object> param) throws SQLException, ClassNotFoundException;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	public int getTotalCountRent(Map<String, String> map) throws SQLException;
	
	
	public ResidenceBuyDto show(int no) throws SQLException;
	public ResidenceRentDto showRent(int no) throws SQLException;
	public List<ResidenceRentDto> searchRent(int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException;
	public List<ResidenceRentDto> getResidenceInDong(String dong) throws Exception;
	public List<ResidenceBuyDto> getResidenceDealName(String buildingName) throws Exception;
}
