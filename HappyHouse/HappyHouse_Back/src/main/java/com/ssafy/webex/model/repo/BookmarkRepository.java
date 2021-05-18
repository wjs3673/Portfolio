package com.ssafy.webex.model.repo;

import java.util.List;

import com.ssafy.webex.model.dto.BookmarkDto;

public interface BookmarkRepository {
	List<BookmarkDto> select(BookmarkDto bookmarkDto);
	int selectNo(BookmarkDto bookmarkDto)throws Exception;
	int insert(BookmarkDto bookmarkDto)throws Exception;
	int delete(int no)throws Exception;
}