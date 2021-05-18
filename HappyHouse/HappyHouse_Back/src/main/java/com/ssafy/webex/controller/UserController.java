package com.ssafy.webex.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.webex.model.dto.MemberDto;
import com.ssafy.webex.model.dto.NoticeDto;
import com.ssafy.webex.model.service.JwtService;
import com.ssafy.webex.model.service.MemberService;

/**
 * Servlet implementation class UserController
 */
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MemberService memberService;

	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("컨트롤러" + memberDto);
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			System.out.println(loginUser);
			if (loginUser != null) {
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				System.out.println("로그인토큰정보" + token);
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginUser.getId());
				resultMap.put("user-name", loginUser.getName());
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println("User Info Start >>>>>>");
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		System.out.println("User Info End >>>>>>");
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberdto) {
		System.out.println("User Join Start >>>>");
		System.out.println(memberdto);
		try {
			memberService.join(memberdto);
			System.out.println("User Join End >>>>");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(value = "/{no}")
	public ResponseEntity<String> modifyInfo(@RequestBody MemberDto memberDto) {
		System.out.println(memberDto);
		try {
			memberService.modifyInfo(memberDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("회원정보수정 error");
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		System.out.println(">>>>> 회원정보삭제 시작");
		try {
			memberService.delete(id);
			System.out.println("회원정보삭제 끝 완료");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 삭제 에러발생");
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
}