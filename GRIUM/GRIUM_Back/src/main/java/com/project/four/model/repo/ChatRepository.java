package com.project.four.model.repo;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.ChatDto;

@Repository
public class ChatRepository {

	@Autowired
	private SqlSession session;

	public int insert(ChatDto chat) throws Exception {
		int result = session.insert("ssafy.chat.insert", chat);
		return result;
	}

	public List<ChatDto> getList(String gone_id) throws Exception {
		List<ChatDto> chatList = new ArrayList<>();
		chatList = session.selectList("ssafy.chat.getList", gone_id);
		return chatList;

	}

	public int delete(ChatDto chat) throws Exception {
		int result = session.update("ssafy.chat.delete", chat);
		return result;
	}

	public int insertAlert(AlertDto alert) throws Exception {
		int result = session.insert("ssafy.chat.alert", alert);
		return result;
	}

	public AlertDto checkUserid(String id) throws Exception {
		AlertDto dto;
		dto = session.selectOne("ssafy.chat.checkUserid", id);
		return dto;
	}

	public int alertCancel(AlertDto alert) throws Exception {
		int result = session.update("ssafy.chat.alertCancel", alert);
		return result;
	}
	
	public int reAlert(AlertDto alert) throws Exception {
		int result = session.update("ssafy.chat.reAlert", alert);
		return result;
	}

}
