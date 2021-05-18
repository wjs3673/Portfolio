package com.ssafy.webex.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webex.model.dto.BookmarkDto;
import com.ssafy.webex.model.repo.BookmarkRepository;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BookmarkDto> select(BookmarkDto bookmarkDto) {
		return sqlSession.getMapper(BookmarkRepository.class).select(bookmarkDto);
	}

	@Override
	public int insert(BookmarkDto bookmarkDto) throws Exception{
		if(sqlSession.getMapper(BookmarkRepository.class).insert(bookmarkDto) == 1) {
			return sqlSession.getMapper(BookmarkRepository.class).selectNo(bookmarkDto);
		}
		return 0;
	}

	@Override
	public int delete(int no)throws Exception {
		return sqlSession.getMapper(BookmarkRepository.class).delete(no);
	}
	

}



