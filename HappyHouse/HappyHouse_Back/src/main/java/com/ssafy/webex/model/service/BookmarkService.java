package com.ssafy.webex.model.service;

import java.util.List;

import com.ssafy.webex.model.dto.BookmarkDto;

public interface BookmarkService {
	List<BookmarkDto> select(BookmarkDto bookmarkDto);
	int insert(BookmarkDto bookmarkDto)throws Exception;
	int delete(int no)throws Exception;
}
