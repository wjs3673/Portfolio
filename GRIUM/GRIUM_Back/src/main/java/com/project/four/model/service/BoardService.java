package com.project.four.model.service;

import java.util.List;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.RipDto;
import com.project.four.util.Pagination;

public interface BoardService {

	int insert(BoardDto board) throws Exception;

	GoneDto checkchild(String gone_id, String user_id) throws Exception;

	List<BoardDto> getallList(Pagination pagination) throws Exception;

	int getcnt(int isOwner, String gone_id) throws Exception;

	int update(BoardDto board) throws Exception;

	int delete(BoardDto board) throws Exception;

	BoardDto callmodi(int board_id) throws Exception;

	int checkAuth(int board_id, String user_id) throws Exception;

	BoardDto callboard(int board_id) throws Exception;

	int checkrip(RipDto rip) throws Exception;

	int pressrip(RipDto rip) throws Exception;

	int updaterip(RipDto rip) throws Exception;

	int cancle(RipDto rip) throws Exception;

	int checkalert(AlertDto alert) throws Exception;

	int pressralert(AlertDto alert) throws Exception;

	int upalert(AlertDto alert) throws Exception;

	int canalert(AlertDto alert) throws Exception;

	int checktype(int board_id, String user_id) throws Exception;

	int checkatype(int board_id, String user_id) throws Exception;

}
