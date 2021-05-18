package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webex.model.dao.NoticeDao;
import com.ssafy.webex.model.dao.NoticeDaoImpl;
import com.ssafy.webex.model.dto.NoticeDto;
import com.ssafy.webex.model.repo.NoticeRepository;
import com.ssafy.webex.util.PageNavigation;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void write(NoticeDto noticeDto) throws Exception {
		if (noticeDto.getTitle() == null || noticeDto.getContent() == null)
			throw new Exception();
		sqlSession.getMapper(NoticeRepository.class).write(noticeDto);
	}

	@Override
	public NoticeDto show(int no) throws SQLException {
		return sqlSession.getMapper(NoticeRepository.class).show(no);
	}

	@Override
	public void modifyInfo(NoticeDto noticeDto) throws SQLException {
		sqlSession.getMapper(NoticeRepository.class).modifyInfo(noticeDto);

	}

	@Override
	public void delete(int no) throws SQLException {
		sqlSession.getMapper(NoticeRepository.class).delete(no);
	}

	@Override
	public NoticeDto getInfo(int no) throws SQLException {
		return sqlSession.getMapper(NoticeRepository.class).show(no);
	}

	public PageNavigation makePageNavigation(Map<String, String> map)
			throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));	// 현재 페이지 번호
		int sizePerPage = Integer.parseInt(map.get("spp"));	// 페이지 글 갯수
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(NoticeRepository.class).getTotalCount(map);
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
	public List<NoticeDto> list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(NoticeRepository.class).list(param);
	}
}
