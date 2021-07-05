package com.project.four.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.ChatDto;
import com.project.four.model.repo.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatRepository crepo;

	@Override
	public int insert(ChatDto chat) throws Exception {
		// TODO Auto-generated method stub
		return crepo.insert(chat);
	}

	@Override
	public List<ChatDto> getList(String gone_id) throws Exception {
		// TODO Auto-generated method stub
		return crepo.getList(gone_id);
	}

	@Override
	public int delete(ChatDto chat) throws Exception {
		// TODO Auto-generated method stub
		return crepo.delete(chat);
	}

	@Override
	public int insertAlert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return crepo.insertAlert(alert);
	}

	@Override
	public AlertDto checkUserid(String id) throws Exception {
		// TODO Auto-generated method stub
		return crepo.checkUserid(id);
	}

	@Override
	public int alertCancel(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return crepo.alertCancel(alert);
	}
	
	@Override
	public int reAlert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return crepo.reAlert(alert);
	}

}
