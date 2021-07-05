package com.project.four.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.ChatDto;
import com.project.four.model.service.ChatService;
import com.project.four.util.AES256Util;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/chat")
public class ChatController {

	public static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	private AES256Util util;

	@Autowired
	private ChatService chatservice;

	// 한마디 등록
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody ChatDto chat, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		// 작성자 암호화
		try {
			logger.info("====================================> 작성자 암호화 시작");
			chat.setWriter(util.encrypt(chat.getWriter()));
		} catch (Exception e) {
			logger.error("암호화 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		// DB에 한마디 등록
		try {
			logger.info("====================================> 한마디 등록 시작");
			int result = chatservice.insert(chat);

			if (result == 1) {
				logger.info("====================================> 한마디 등록 성공");
				resultMap.put("message", "한마디 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "한마디 등록에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("한마디 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 한마디 리스트 가져오기
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> chatList(@RequestParam String gone_id) {
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			logger.info("====================================> 한마디 리스트 받기");
			List<ChatDto> chatList = chatservice.getList(gone_id);

			// 작성자 복호화
			logger.info("====================================> 작성자 복호화");
			for (ChatDto chat : chatList) {
				chat.setWriter(util.decrypt(chat.getWriter()));
			}

			resultMap.put("chatList", chatList);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("한마디 리스트업 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 한마디 삭제
	@PutMapping("/delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody ChatDto chat) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			logger.info("====================================> 한마디 삭제");
			int result = chatservice.delete(chat);

			if (result == 1) {
				logger.info("====================================> 삭제 성공");
				resultMap.put("message", "한마디 삭제에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "한마디 삭제에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("한마디 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 한마디 신고
	@PostMapping("/alert")
	public ResponseEntity<Map<String, Object>> alert(@RequestBody AlertDto alert, HttpServletResponse response,
			HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			// 한마디 신고자 확인
			logger.info("====================================> 한마디 신고자 확인");
			String id = alert.getUser_id();
			System.out.println(id);
			AlertDto check_user_id = chatservice.checkUserid(id);

			// 신고자가 일치하면 한마디 신고 취소
			if (check_user_id != null) {
				// 한마디 신고 취소
				if (check_user_id.getType() == 1) {
					logger.info("====================================> 한마디 신고 취소");
					int result = chatservice.alertCancel(check_user_id);

					if (result == 1) {
						logger.info("====================================> 한마디 신고 취소 성공");
						resultMap.put("message", "한마디 신고 취소에 성공하였습니다.");
						status = HttpStatus.ACCEPTED;
					} else {
						resultMap.put("message", "한마디 신고 취소에 실패하였습니다.");
						status = HttpStatus.ACCEPTED;
					}
					// 한마디 신고했던 사람이 다시 신고
				} else {
					logger.info("====================================> 다시 한마디 신고");
					int result = chatservice.reAlert(check_user_id);

					if (result == 1) {
						logger.info("====================================> 다시 한마디 신고 성공");
						resultMap.put("message", "다시 한마디 신고에 성공하였습니다.");
						status = HttpStatus.ACCEPTED;
					} else {
						resultMap.put("message", "다시 한마디 신고에 실패하였습니다.");
						status = HttpStatus.ACCEPTED;
					}
				}

				// 신고자가 일치하지 않는다면 한마디 신고
			} else {
				logger.info("====================================> 한마디 신고 시작");
				int result = chatservice.insertAlert(alert);

				if (result == 1) {
					logger.info("====================================> 한마디 신고 성공");
					resultMap.put("message", "한마디 신고에 성공하였습니다.");
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "한마디 신고에 실패하였습니다.");
					status = HttpStatus.ACCEPTED;
				}

			}
		} catch (Exception e) {
			logger.error("한마디 신고 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}