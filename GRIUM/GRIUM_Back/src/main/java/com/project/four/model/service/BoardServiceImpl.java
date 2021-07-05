package com.project.four.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.RipDto;
import com.project.four.model.repo.BoardRepository;
import com.project.four.util.Pagination;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepository brepo;

	@Override
	public int insert(BoardDto board) throws Exception {
		// TODO Auto-generated method stub
		return brepo.insert(board);
	}

	@Override
	public GoneDto checkchild(String gone_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checkchild(gone_id, user_id);
	}

	@Override
	public List<BoardDto> getallList(Pagination pagination) throws Exception {
		// TODO Auto-generated method stub
		return brepo.getallList(pagination);
	}

	@Override
    public int getcnt(int isOwner, String gone_id) throws Exception {
        // TODO Auto-generated method stub
        return brepo.getcnt(isOwner, gone_id);
    }

	@Override
	public int update(BoardDto board) throws Exception {
		// TODO Auto-generated method stub
		return brepo.update(board);
	}

	@Override
	public int delete(BoardDto board) throws Exception {
		// TODO Auto-generated method stub
		return brepo.delete(board);
	}

	@Override
	public BoardDto callmodi(int board_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.callmodi(board_id);
	}

	@Override
	public int checkAuth(int board_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checkAuth(board_id, user_id);
	}

	@Override
	public BoardDto callboard(int board_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.callboard(board_id);
	}

	@Override
	public int checkrip(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checkrip(rip);
	}

	@Override
	public int pressrip(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return brepo.pressrip(rip);
	}

	@Override
	public int updaterip(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return brepo.updaterip(rip);
	}

	@Override
	public int cancle(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return brepo.cancle(rip);
	}

	@Override
	public int checkalert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checkalert(alert);
	}

	@Override
	public int pressralert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return brepo.pressalert(alert);
	}

	@Override
	public int upalert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return brepo.upalert(alert);
	}

	@Override
	public int canalert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return brepo.canalert(alert);
	}

	@Override
	public int checktype(int board_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checktype(board_id, user_id);
	}

	@Override
	public int checkatype(int board_id, String user_id) throws Exception {
		// TODO Auto-generated method stub
		return brepo.checkatype(board_id, user_id);
	}

}
