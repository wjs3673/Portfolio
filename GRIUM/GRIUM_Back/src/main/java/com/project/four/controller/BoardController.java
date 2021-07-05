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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.RipDto;
import com.project.four.model.dto.UserDto;
import com.project.four.model.service.BoardService;
import com.project.four.util.AES256Util;
import com.project.four.util.Pagination;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value = "/board")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private AES256Util util;
	
	@Autowired
	private BoardService boardservice;
	
	@ApiOperation(value="Board Insert", notes="게시판 글 등록")
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> insert(@RequestBody BoardDto board) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		// 개인정보 암호화
		try {
			logger.info("====================================> 암호화");
			board.setWriter(util.encrypt(board.getWriter()));
//			board.setUser_id(util.encrypt(board.getUser_id()));
		} catch (Exception e) {
			logger.error("암호화 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		// DB글등록
		try {
			logger.info("====================================> 글 등록 시작");
			int result = boardservice.insert(board);
			
			if (result == 1) {
				logger.info("====================================> 글 등록 성공");
				resultMap.put("message", "글 등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 등록에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="Board initial list", notes="게시판 create 글 목록")
    @GetMapping("/makelist")
    public ResponseEntity<Map<String, Object>> makeList(@RequestParam String gone_id, @RequestParam String user_id) {
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();

    int page = 1;
    int range = (page / 10) + 1;
    int listCnt = 0;
    int isOwner = 0;
    List<BoardDto> list = null;
    
    // 상주 확인 => 상주는 1
    try {
        logger.info("====================================> 상주 확인");
        GoneDto gone = boardservice.checkchild(gone_id, user_id);
        if(gone != null) isOwner = 1;
        
        // 총 게시물 개수
        listCnt = boardservice.getcnt(isOwner, gone_id);
        
        // 페이지 처리
        logger.info("====================================> 페이징");
        Pagination pagination = new Pagination();
        pagination.pageInfo(page, range, listCnt, isOwner, gone_id);
        
        logger.info("====================================> 글 목록 받기");
        list = boardservice.getallList(pagination);
        
        // 복호화
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWriter(util.decrypt(list.get(i).getWriter()));
        }
        
        resultMap.put("pagination", pagination);
        resultMap.put("list", list);
        status = HttpStatus.ACCEPTED;
        
    } catch (Exception e) {
        // TODO Auto-generated catch block
        logger.error("초기 리스트업 실패 : {}", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        e.printStackTrace();
    }
    
    return new ResponseEntity<Map<String, Object>>(resultMap, status);
}

@ApiOperation(value="Board page list", notes="게시판 페이지 글 목록")
@GetMapping("/list/{page}")
public ResponseEntity<Map<String, Object>> allList(@PathVariable int page, @RequestParam String gone_id, @RequestParam String user_id) {
    HttpStatus status = null;
    Map<String, Object> resultMap = new HashMap<>();
    
    int range = (page / 10) + 1;
    int listCnt = 0;
    int isOwner = 0;
    List<BoardDto> list = null;
    
    // 상주 확인
    try {
        logger.info("====================================> 상주 확인");
        GoneDto gone = boardservice.checkchild(gone_id, user_id);
        if(gone != null) isOwner = 1;
        
        // 총 게시물 개수
        listCnt = boardservice.getcnt(isOwner, gone_id);
        
        // 페이지 처리
        logger.info("====================================> 페이징");
        Pagination pagination = new Pagination();
        pagination.pageInfo(page, range, listCnt, isOwner, gone_id);
        
        logger.info("====================================> 글 목록 받기");
        list = boardservice.getallList(pagination);
        
        // 복호화
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWriter(util.decrypt(list.get(i).getWriter()));
        }
        
        resultMap.put("pagination", pagination);
        resultMap.put("list", list);
        status = HttpStatus.ACCEPTED;
        
    } catch (Exception e) {
        // TODO Auto-generated catch block
        logger.error("리스트업 실패 : {}", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        e.printStackTrace();
    }
    
    return new ResponseEntity<Map<String, Object>>(resultMap, status);
}
	
	@ApiOperation(value="Call Modify", notes="게시판 수정 내용 불러오기")
	@GetMapping("/callmodi/{board_id}")
	public ResponseEntity<Map<String, Object>> callmodi(@PathVariable int board_id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			logger.info("====================================> 수정 내용 부르기");
			BoardDto dto = boardservice.callmodi(board_id);
			
			if(dto != null) {
				logger.info("====================================> 글 부르기 성공");
				// 복호화
				dto.setWriter(util.decrypt(dto.getWriter()));
				
				resultMap.put("board", dto);
				resultMap.put("message", "글 부르기에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 부르기에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 부르기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="Board Modify", notes="게시판 글 수정")
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody BoardDto board) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			logger.info("====================================> 내용 수정");
			int result = boardservice.update(board);
			
			if(result == 1) {
				System.out.println("====================================> 수정 성공");
				resultMap.put("message", "글 수정에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 수정에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="Board delete", notes="게시판 글 삭제")
	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> delete(@RequestBody BoardDto board) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			logger.info("====================================> 내용 삭제");
			int result = boardservice.delete(board);
			
			if(result == 1) {
				logger.info("====================================> 삭제 성공");
				resultMap.put("message", "글 삭제에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 삭제에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("글 삭제 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="Board Call", notes="게시판 글 하나 불러오기")
	@GetMapping("/getboard/{board_id}")
	public ResponseEntity<Map<String, Object>> getboard(@PathVariable int board_id, @RequestParam String user_id, @RequestParam String gone_id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		boolean isAuth = false;
		
		// 수정, 삭제 버튼에 대한 권한 체크
		try {
			logger.info("====================================> 버튼 권한 체크");
			int check = boardservice.checkAuth(board_id, user_id);
			System.out.println("checkou"+check);
			GoneDto gone = boardservice.checkchild(gone_id, user_id);
			System.out.println("chei"+gone);
			int type = boardservice.checktype(board_id, user_id);
			System.out.println("che2323"+type);
			resultMap.put("TYPE", type);
			int type2 = boardservice.checkatype(board_id, user_id);
			System.out.println("chei"+type2);
			resultMap.put("TYPE2", type2);
			
			if(check > 0 || gone != null) {
				logger.info("====================================> 작성자");
				isAuth = true;
				resultMap.put("isAuth", isAuth);
			} else {
				logger.info("====================================> 비 작성자");
				resultMap.put("isAuth", isAuth);
			}
			
			logger.info("====================================> 내용 부르기");
			BoardDto dto = boardservice.callboard(board_id);
			
			if(dto != null) {
				logger.info("====================================> 글 부르기 성공");
				// 복호화
				dto.setWriter(util.decrypt(dto.getWriter()));
				
				resultMap.put("board", dto);
				resultMap.put("message", "글 부르기에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "글 부르기에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("현재글 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="Board good", notes="게시판 글 추천")
	@PostMapping("/rip")
	public ResponseEntity<Map<String, Object>> rip(@RequestBody RipDto rip) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		int good = 0;
		
		try {
			int checkrip = boardservice.checkrip(rip);
			if(checkrip == 1) {
				logger.info("====================================> 좋아요 취소");
				good = boardservice.cancle(rip);
				resultMap.put("TYPE", 0);
				status = HttpStatus.ACCEPTED;
			} else if (checkrip == 2) {
				logger.info("====================================> 빈 좋아요 상태/최초");
				good = boardservice.pressrip(rip);
				resultMap.put("TYPE", 1);
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("====================================> 빈 좋아요 > 좋아요");
				good = boardservice.updaterip(rip);
				resultMap.put("TYPE", 1);
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("추천 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="Board report", notes="게시판 글 신고")
	@PostMapping("/alert")
	public ResponseEntity<Map<String, Object>> alert(@RequestBody AlertDto alert) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		int report = 0;
		
		try {
			int checkalert = boardservice.checkalert(alert);
			if(checkalert == 1) {
				logger.info("====================================> 신고 취소");
				report = boardservice.canalert(alert);
				resultMap.put("TYPE", 0);
				status = HttpStatus.ACCEPTED;
			} else if (checkalert == 2) {
				logger.info("====================================> 빈 신고 상태/최초");
				report = boardservice.pressralert(alert);
				resultMap.put("TYPE", 1);
				status = HttpStatus.ACCEPTED;
			} else {
				logger.info("====================================> 신고 취소 > 재신고");
				// Type이 0으로 돌아간 상태
				report = boardservice.upalert(alert);
				resultMap.put("TYPE", 1);
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("신고 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
