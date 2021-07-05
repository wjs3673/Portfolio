package com.project.four.model.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.RipDto;
import com.project.four.util.Pagination;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession session;

	public int insert(BoardDto board) throws Exception {
		int result = session.insert("ssafy.board.insert", board);
		return result;
	}

	public GoneDto checkchild(String gone_id, String user_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gone_id", gone_id);
		map.put("user_id", user_id);
		GoneDto dto = null;
		dto = session.selectOne("ssafy.board.checkchild", map);

		return dto;
	}

	public List<BoardDto> getallList(Pagination pagination) throws Exception {
		List<BoardDto> list = null;
		if (session.selectList("ssafy.board.listall", pagination) != null)
			list = session.selectList("ssafy.board.listall", pagination);
		return list;
	}

	public int getcnt(int isOwner, String gone_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isOwner", isOwner);
		map.put("gone_id", gone_id);
		int result = session.selectOne("ssafy.board.getCnt", map);
		return result;
	}

	public int update(BoardDto board) throws Exception {
		int result = session.update("ssafy.board.upBoard", board);
		return result;
	}

	public int delete(BoardDto board) throws Exception {
		int result = session.update("ssafy.board.delBoard", board);
		return result;
	}

	public BoardDto callmodi(int board_id) throws Exception {
		BoardDto dto = null;
		dto = session.selectOne("ssafy.board.callboard", board_id);
		return dto;
	}

	public int checkAuth(int board_id, String user_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("user_id", user_id);
		int result = session.selectOne("ssafy.board.checkAuth", map);
		return result;
	}

	public BoardDto callboard(int board_id) {
		BoardDto dto = null;
		dto = session.selectOne("ssafy.board.callboard", board_id);
		return dto;
	}

	public int checkrip(RipDto rip) throws Exception {
		int result = 2;
		if (session.selectOne("ssafy.rip.checkrip", rip) != null)
			result = session.selectOne("ssafy.rip.checkrip", rip);
		return result;
	}

	public int pressrip(RipDto rip) throws Exception {
		int result = session.insert("ssafy.rip.pressrip", rip);
		result = session.update("ssafy.rip.plusCount", rip);
		return result;
	}

	public int updaterip(RipDto rip) throws Exception {
		int result = session.update("ssafy.rip.updaterip", rip);
		result = session.update("ssafy.rip.plusCount", rip);
		return result;
	}

	public int cancle(RipDto rip) throws Exception {
		int result = session.update("ssafy.rip.cancle", rip);
		result = session.update("ssafy.rip.setCount", rip);
		return result;
	}

	public int checkalert(AlertDto alert) throws Exception {
		int result = 2;
		if (session.selectOne("ssafy.alert.checkalert", alert) != null)
			result = session.selectOne("ssafy.alert.checkalert", alert);
		return result;
	}

	public int pressalert(AlertDto alert) throws Exception {
		int result = session.insert("ssafy.alert.pressalert", alert);
		return result;
	}

	public int upalert(AlertDto alert) throws Exception {
		int result = session.update("ssafy.alert.upalert", alert);
		return result;
	}

	public int canalert(AlertDto alert) throws Exception {
		int result = session.update("ssafy.alert.canalert", alert);
		return result;
	}

	public int checktype(int board_id, String user_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("user_id", user_id);
		int result = 0;
		if (session.selectOne("ssafy.alert.checktype", map) != null)
			result = session.selectOne("ssafy.alert.checktype", map);
		return result;
	}

	public int checkatype(int board_id, String user_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("user_id", user_id);
		int result = 0;
		if (session.selectOne("ssafy.alert.checkatype", map) != null)
			result = session.selectOne("ssafy.alert.checktype", map);
		return result;
	}
}
