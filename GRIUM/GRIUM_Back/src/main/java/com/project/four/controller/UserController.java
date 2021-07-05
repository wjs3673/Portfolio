package com.project.four.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.four.model.dto.UserDto;
import com.project.four.model.service.JwtService;
import com.project.four.model.service.UserService;
import com.project.four.util.AES256Util;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JwtService jwtservice;

	@Autowired
	private UserService userservice;

	@Autowired
	private AES256Util util;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/checkmail")
	public boolean checkmail(@RequestBody UserDto user) {
		boolean isTrue = false;

		String mail = user.getEmail();
		try {
			String authKey = userservice.sendAuthMail(mail);
			System.out.println("====================================> " + authKey);
			
			if(!authKey.equals(null)) {
				isTrue = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isTrue;
	}
	
	@GetMapping("/authemail")
	public boolean authemail(@RequestParam String email, @RequestParam String auth_num) {
		boolean isTrue = false;
		
		try {
			int result = userservice.authemail(email, auth_num);
			
			if(result == 1) {
				System.out.println("====================================> 성공");
				int res = userservice.delauth(email, auth_num);
				isTrue = true;
			} else {
				System.out.println("====================================> 실패");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isTrue;
	}

	@PostMapping("/signup")
	@ResponseBody
	public boolean signup(@RequestBody UserDto user)
			throws Exception {
		System.out.println("====================================> User Signup");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		// userid 셋팅
		StringBuffer made = new StringBuffer();

		for (int i = 0; i < 6; i++) {
			char a = (char) ((Math.random() * 26) + 97); // 소문자
			int ann = (int) (Math.random() * 9) + 1; // 숫자
			made.append(a);
			made.append(ann);
		}

		char b = (char) ((Math.random() * 26) + 97);
		made.append(b);
		System.out.println(made);
		String line = made.toString();

		// 중복확인을 해줄꺼야
		UserDto overlap_id = userservice.overid(line);
		String find_email = util.encrypt(user.getEmail());
		UserDto overlap_eamil = userservice.overemail(find_email);

		if (overlap_id != null || overlap_eamil != null) {
			resultMap.put("message", "회원가입 실패");
			System.out.println("회원가입 실패");
			return false;
		}

		// 중복이 없다면 userid 셋팅끝!
		user.setUser_id(line); // userid 셋팅끝!

		String email = user.getEmail();
		String password = user.getPassword();
		String name = user.getName();
		String phone = user.getPhone();

		try {
			email = util.encrypt(email);
			password = util.encrypt(password);
			name = util.encrypt(name);
			phone = util.encrypt(phone);

		} catch (UnsupportedEncodingException | GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		user.setPhone(phone);

		System.out.println(user);
		try {
			int result = userservice.signup(user);

			if (result > 0) {
				System.out.println("회원가입 성공");
				return true;
			} else {
				resultMap.put("message", "회원가입 실패");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("회원가입 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			return false;
		}
	}

	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		String email = user.getEmail();
		String password = user.getPassword();

		String passemail = null;
		String passpw = null;

		try {
			System.out.println("====================================> 암호화");
			passemail = util.encrypt(email);
			passpw = util.encrypt(password);

		} catch (Exception e) {
			logger.error("암호화 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		try {
			System.out.println("====================================> 탈퇴여부 확인");
			int result = userservice.checkOut(passemail);

			if (result == 1) {
				System.out.println("====================================> 로그인 입장");
				UserDto loginUser = userservice.userLogin(passemail, passpw);
				System.out.println(user);

				if (loginUser != null) {
					System.out.println("성공" + loginUser.getName());
					String token = jwtservice.create(loginUser);
					logger.trace("로그인 토큰정보 : {}", token);

					resultMap.put("auth-token", token);
					resultMap.put("userid", loginUser.getUser_id());
					resultMap.put("email", util.decrypt(loginUser.getEmail()));
					resultMap.put("password", util.decrypt(loginUser.getPassword()));
					resultMap.put("name", util.decrypt(loginUser.getName()));
					resultMap.put("phone", util.decrypt(loginUser.getPhone()));

					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
					status = HttpStatus.ACCEPTED;
				}
			} else {
				resultMap.put("message", "존재하지 않는 회원입니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/edit")
	public boolean edit(@RequestBody UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println("edit>>>" + user);
		try {
			String email = user.getEmail();
			String password = user.getPassword();
			String name = user.getName();
			String phone = user.getPhone();

			user.setEmail(util.encrypt(email));
			user.setPassword(util.encrypt(password));
			user.setName(util.encrypt(name));
			user.setPhone(util.encrypt(phone));

			int result = userservice.edit(user);

			if (result > 0) {
				return true;
			} else {
				resultMap.put("message", "수정 실패");
				return false;
			}
		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			return false;
		}
	}

	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtservice.get(req.getHeader("auth-token")));
		try {
			resultMap.putAll(jwtservice.get(req.getHeader("auth-token")));
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/withdraw")
	@ResponseBody
	public boolean withdraw(@RequestBody UserDto user) {
		System.out.println("====================================> User Withdraw");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(user);
		try {
			String email = user.getEmail();
			String password = user.getPassword();
			String name = user.getName();
			String phone = user.getPhone();
			user.setEmail(util.encrypt(email));
			user.setPassword(util.encrypt(password));
			user.setName(util.encrypt(name));
			user.setPhone(util.encrypt(phone));
			
			int result = userservice.withdraw(user);

			if (result > 0) {
				System.out.println("탈퇴 완료!!! 넘어가는 중");
				return true;
			} else {
				resultMap.put("message", "회원탈퇴 실패");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("회원탈퇴 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			return false;
		}
	}

	@PostMapping("/findpw")
	public ResponseEntity<Map<String, Object>> findpw(@RequestBody UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		String email = user.getEmail();
		String name = user.getName();

		String passemail = null;
		String passname = null;

		try {
			System.out.println("====================================> 암호화");
			passemail = util.encrypt(email);
			passname = util.encrypt(name);

		} catch (Exception e) {
			logger.error("암호화 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		try {
			System.out.println("====================================> 비밀번호 찾기");
			UserDto findpw = userservice.findPassword(passemail, passname);
			//KY  아이디랑 이메일을 통해서 비밀번호로 DTO 가져왔고
			// 가져온애로 getout테이블에 있는지 확인해보고 난수생성한 애로 pw 해야지
			if (findpw != null) { //user테이블에 있는지 확인한번하고

				// KY 아이디랑 이메일을 통해서 비밀번호로 DTO 가져왔고
				// 가져온애로 getout테이블에 있는지 확인해보고 난수생성한 애로 pw 해야지
				UserDto noGetout = userservice.existGet(passemail); // getout에 있는지 확인
				if (noGetout != null) {
					resultMap.put("message", "탈퇴한 사용자 입니다.");
					status = HttpStatus.ACCEPTED;
				}else {
					// null값이라면 탈퇴한회원이아니란거니까 비밀번호 난수생성해드릴꼐영				
					// set tempPassword
					StringBuffer made = new StringBuffer();
					
					for (int i = 0; i < 5; i++) {
						char a = (char) ((Math.random() * 26) + 97); // 소문자
						int ann = (int) (Math.random() * 9) + 1; // 숫자
						made.append(a);
						made.append(ann);
					}
					
					String line = made.toString();
					System.out.println("안녕하세요 저는 생성된 임시 비밀번호에요 : " + line);
					String newPw = util.encrypt(line); // 새로생성한 암호 암호화해서 세팅
					System.out.println("안녕하세요 저는 암호화된 비밀번호예요 : "+ newPw);
					int result = userservice.newPassword(passemail, newPw);
					if (result > 0) {
						System.out.println("성공 : " + result);
					} else {
						System.out.println("실패했어 으흐흐흐흑");
					}
					
					resultMap.put("password", line);
					status = HttpStatus.ACCEPTED;

				}
			 } else {
				resultMap.put("message", "존재하지 않는 사용자 입니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("비밀번호 찾기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}