package com.project.four.model.service;

import java.util.List;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.ChatDto;

public interface ChatService {

	int insert(ChatDto chat) throws Exception; // 한마디 등록

	List<ChatDto> getList(String gone_id) throws Exception; // 한마디 리스트 가져오기

	int delete(ChatDto chat) throws Exception; // 한마디 삭제

	int insertAlert(AlertDto alert) throws Exception; // 한마디 신고

	AlertDto checkUserid(String id) throws Exception; // 한마디 신고한 사람인지 체크

	int alertCancel(AlertDto alert) throws Exception; // 한마디 신고 취소
	
	int reAlert(AlertDto alert) throws Exception; // 한마디 신고한 사람의 재신고

}
