package com.ssafy.webex.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.webex.model.dto.ResidenceBuyDto;
import com.ssafy.webex.model.dto.ResidenceRentDto;


public interface ResidenceDealDao {
	public List<ResidenceBuyDto> searchBuy(int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException;
	public List<ResidenceRentDto> searchRent(int currentPage, int sizePerPage) throws SQLException, ClassNotFoundException;
	
	public ResidenceBuyDto show(int no) throws SQLException;
	
	public ResidenceRentDto showRent(int no) throws SQLException;
	
	public int getTotalCount(String key, String word) throws SQLException;
	
	public int getTotalCountRent(String key, String word) throws SQLException;
}
