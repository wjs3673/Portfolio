package com.ssafy.webex.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.ResidenceBuyDto;
import com.ssafy.webex.model.dto.ResidenceRentDto;
import com.ssafy.webex.model.repo.ResidenceDealRepository;
import com.ssafy.webex.util.DBUtil;

public class ResidenceDealDaoImpl implements ResidenceDealDao {
	
	public List<ResidenceBuyDto> searchBuy(int currentPage, int sizePerPage)
			throws SQLException, ClassNotFoundException {
		List<ResidenceBuyDto> list = new ArrayList<ResidenceBuyDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,city,gugun,dong,bungi,bonbun,bubun,buildingName,area,daejiarea,substr(dealyearmonth, 1, 4) dealYear,substr(dealyearmonth, 5, 6) dealMonth,dealDay,dealAmount,floor,buildYear,roadname \n");
			sql.append("from residencebuy \n");
			sql.append("order by no asc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				ResidenceBuyDto residenceBuy = new ResidenceBuyDto();
				residenceBuy.setNo(rs.getInt("no"));
				residenceBuy.setCity(rs.getString("city"));
				residenceBuy.setGugun(rs.getString("gugun"));
				residenceBuy.setDong(rs.getString("dong"));
				residenceBuy.setBungi(rs.getString("bungi"));
				residenceBuy.setBonbun(rs.getString("bonbun"));
				residenceBuy.setBubun(rs.getString("bubun"));
				residenceBuy.setBuildingName(rs.getString("buildingName"));
				residenceBuy.setArea(rs.getDouble("area"));
				residenceBuy.setDaejiarea(rs.getDouble("daejiarea"));
				residenceBuy.setDealYear(rs.getString("dealYear"));
				residenceBuy.setDealMonth(rs.getString("dealMonth"));
				residenceBuy.setDealDay(rs.getString("dealDay"));
				residenceBuy.setDealAmount(rs.getString("dealAmount"));
				residenceBuy.setFloor(rs.getInt("floor"));
				residenceBuy.setBuildYear(rs.getInt("buildYear"));
				residenceBuy.setRoadname(rs.getString("roadname"));
				
				list.add(residenceBuy);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}
	
	@Override
	public List<ResidenceRentDto> searchRent(int currentPage, int sizePerPage)
			throws SQLException, ClassNotFoundException {

		List<ResidenceRentDto> list = new ArrayList<ResidenceRentDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,city,gugun,dong,bunji,bonbun,bubun,buildingname,type,area,substr(contract_year_month, 1, 4) contractYear, substr(contract_year_month, 5, 6) contractMonth, contract_day contractDay,bondfee,rentmoney,floor,buildyear,address \n");
			sql.append("from residencerent \n");
			sql.append("order by no asc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				ResidenceRentDto residenceRent = new ResidenceRentDto();
				residenceRent.setNo(rs.getInt("no"));
				residenceRent.setCity(rs.getString("city"));
				residenceRent.setGugun(rs.getString("gugun"));
				residenceRent.setDong(rs.getString("dong"));
				residenceRent.setBunji(rs.getString("bunji"));
				residenceRent.setBonbun(rs.getString("bonbun"));
				residenceRent.setBubun(rs.getString("bubun"));
				residenceRent.setBuildingName(rs.getString("buildingName"));
				residenceRent.setType(rs.getString("type"));
				residenceRent.setArea(rs.getDouble("area"));
				residenceRent.setContractYear(rs.getString("contractYear"));
				residenceRent.setContractMonth(rs.getString("contractMonth"));
				residenceRent.setContractDay(rs.getString("contractDay"));
				residenceRent.setBondFee(rs.getInt("bondfee"));
				residenceRent.setRentMoney(rs.getInt("rentmoney"));
				residenceRent.setFloor(rs.getInt("floor"));
				residenceRent.setBuildYear(rs.getInt("buildyear"));
				residenceRent.setAddress(rs.getString("address"));
				
				list.add(residenceRent);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}
	
	public ResidenceBuyDto show(int no) throws SQLException {
		ResidenceBuyDto residenceBuy = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,city,gugun,dong,bungi,bonbun,bubun,buildingName,area,daejiarea,substr(dealyearmonth, 1, 4) dealYear,substr(dealyearmonth, 5, 6) dealMonth,dealDay,dealAmount,floor,buildYear,roadname \n");
			sql.append("from residencebuy \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				residenceBuy = new ResidenceBuyDto();
				residenceBuy.setNo(rs.getInt("no"));
				residenceBuy.setCity(rs.getString("city"));
				residenceBuy.setGugun(rs.getString("gugun"));
				residenceBuy.setDong(rs.getString("dong"));
				residenceBuy.setBungi(rs.getString("bungi"));
				residenceBuy.setBonbun(rs.getString("bonbun"));
				residenceBuy.setBubun(rs.getString("bubun"));
				residenceBuy.setBuildingName(rs.getString("buildingName"));
				residenceBuy.setArea(rs.getDouble("area"));
				residenceBuy.setDaejiarea(rs.getDouble("daejiarea"));
				residenceBuy.setDealYear(rs.getString("dealYear"));
				residenceBuy.setDealMonth(rs.getString("dealMonth"));
				residenceBuy.setDealDay(rs.getString("dealDay"));
				residenceBuy.setDealAmount(rs.getString("dealAmount"));
				residenceBuy.setFloor(rs.getInt("floor"));
				residenceBuy.setBuildYear(rs.getInt("buildYear"));
				residenceBuy.setRoadname(rs.getString("roadname"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return residenceBuy;
	}
	
	public ResidenceRentDto showRent(int no) throws SQLException {
		ResidenceRentDto residenceRent = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select no,city,gugun,dong,bunji,bonbun,bubun,buildingname,type,area,substr(contract_year_month, 1, 4) contractYear, substr(contract_year_month, 5, 6) contractMonth, contract_day contractDay,bondfee,rentmoney,floor,buildyear,address \n");
			sql.append("from residencerent \n");
			sql.append("where no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				residenceRent = new ResidenceRentDto();
				residenceRent.setNo(rs.getInt("no"));
				residenceRent.setCity(rs.getString("city"));
				residenceRent.setGugun(rs.getString("gugun"));
				residenceRent.setDong(rs.getString("dong"));
				residenceRent.setBunji(rs.getString("bunji"));
				residenceRent.setBonbun(rs.getString("bonbun"));
				residenceRent.setBubun(rs.getString("bubun"));
				residenceRent.setBuildingName(rs.getString("buildingName"));
				residenceRent.setType(rs.getString("type"));
				residenceRent.setArea(rs.getDouble("area"));
				residenceRent.setContractYear(rs.getString("contractYear"));
				residenceRent.setContractMonth(rs.getString("contractMonth"));
				residenceRent.setContractDay(rs.getString("contractDay"));
				residenceRent.setBondFee(rs.getInt("bondfee"));
				residenceRent.setRentMoney(rs.getInt("rentmoney"));
				residenceRent.setFloor(rs.getInt("floor"));
				residenceRent.setBuildYear(rs.getInt("buildyear"));
				residenceRent.setAddress(rs.getString("address"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return residenceRent;
	}
	
	public int getTotalCount(String key, String word) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(no) \n");
			sql.append("from residencebuy \n");
			if(!key.equals(""))
				sql.append("where " + key + " like ? ");
			sql.append("order by no desc \n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
			if(!key.equals(""))
				pstmt.setString(1, "%" + word + "%");
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
	
	public int getTotalCountRent(String key, String word) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(no) \n");
			sql.append("from residencerent \n");
			if(!key.equals(""))
				sql.append("where " + key + " like ? ");
			sql.append("order by no desc \n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
			if(!key.equals(""))
				pstmt.setString(1, "%" + word + "%");
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
