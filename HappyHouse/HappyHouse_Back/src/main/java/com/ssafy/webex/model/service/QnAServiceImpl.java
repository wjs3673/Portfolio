package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webex.model.dto.QnADto;
import com.ssafy.webex.model.repo.QnARepository;
import com.ssafy.webex.util.PageNavigation;

@Service
public class QnAServiceImpl implements QnAService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void write(QnADto qnaDto) throws Exception {
		if (qnaDto.getTitle() == null || qnaDto.getContent() == null)
			throw new Exception();
		sqlSession.getMapper(QnARepository.class).write(qnaDto);
	}

	@Override
	public QnADto show(int no) throws SQLException {
		return sqlSession.getMapper(QnARepository.class).show(no);
	}

	@Override
	public void modifyInfo(QnADto qnaDto) throws SQLException {
		sqlSession.getMapper(QnARepository.class).modifyInfo(qnaDto);

	}

	@Override
	public void delete(int no) throws SQLException {
		sqlSession.getMapper(QnARepository.class).delete(no);
	}

	@Override
	public QnADto getInfo(int no) throws SQLException {
		return sqlSession.getMapper(QnARepository.class).show(no);
	}

	public PageNavigation makePageNavigation(Map<String, String> map)
			throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));	// 현재 페이지 번호
		int sizePerPage = Integer.parseInt(map.get("spp"));	// 페이지 글 갯수
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(QnARepository.class).getTotalCount(map);
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
	public List<QnADto> list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(QnARepository.class).list(param);
	}
}
