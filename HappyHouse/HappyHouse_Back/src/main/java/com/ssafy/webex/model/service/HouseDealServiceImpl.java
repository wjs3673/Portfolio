package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webex.model.dto.HouseDealDto;
import com.ssafy.webex.model.repo.HouseDealRepository;
import com.ssafy.webex.util.PageNavigation;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private SqlSession sqlSession;
	
	public List<HouseDealDto> searchAll(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		try {
			return sqlSession.getMapper(HouseDealRepository.class).searchAll(param);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<HouseDealDto> searchAptName(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		param.put("aptName", map.get("aptName"));
		try {
			return sqlSession.getMapper(HouseDealRepository.class).searchAptName(param);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<HouseDealDto> searchDong(String dongName) throws Exception {
//	public List<HouseDealDto> searchDong(Map<String, String> map) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("key", map.get("key") == null ? "" : map.get("key"));
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pg"));
//		int sizePerPage = Integer.parseInt(map.get("spp"));
//		int start = (currentPage - 1) * sizePerPage;
//		param.put("start", start);
//		param.put("spp", sizePerPage);
//		param.put("dongName", map.get("dongName"));
		try {
			return sqlSession.getMapper(HouseDealRepository.class).searchDongName(dongName);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public HouseDealDto show(int aptno) throws SQLException {
		return sqlSession.getMapper(HouseDealRepository.class).show(aptno);
	}
	
	public PageNavigation makePageNavigation(Map<String, String> map)
			throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));	// 현재 페이지 번호
		int sizePerPage = Integer.parseInt(map.get("spp"));	// 페이지 글 갯수
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(HouseDealRepository.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public List<HouseDealDto> getAptDealName(String aptName) throws Exception {
		return sqlSession.getMapper(HouseDealRepository.class).getAptDealName(aptName);
	}
	
	
}
