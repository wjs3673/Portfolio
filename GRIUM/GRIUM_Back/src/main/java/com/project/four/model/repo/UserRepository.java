package com.project.four.model.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.UserDto;
import com.project.four.util.MailUtils;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession session;
	
	@Autowired
    private JavaMailSender mailSender;

	public int insert(UserDto user) {
		int result = session.insert("ssafy.user.insert", user);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
	}

	public UserDto userLogin(String email, String password) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserDto dto;
		dto = session.selectOne("ssafy.user.login", map);
		return dto;
	}

	public int update(UserDto user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", user.getEmail());
		map.put("name", user.getName());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		int res = session.update("ssafy.user.update", map);

		return res;
	}
	
	public int checkOut(String passemail) {
	      String result = "default";
	      if(session.selectOne("ssafy.user.checkout", passemail) != null) return 0;  // 탈퇴한 회원인 경우
	      else return 1; // 탈퇴 회원이 아닌 경우
	}
	
	
	public int withdraw(UserDto user) {
		int result = session.insert("ssafy.user.withdraw", user);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
	}
	
	public UserDto overid(String id) throws Exception{
		UserDto dto;
		dto = session.selectOne("ssafy.user.overid", id);
		return dto;
	}
	
	public UserDto overemail(String email) throws Exception{
		UserDto dto;
		dto = session.selectOne("ssafy.user.overemail", email);
		return dto;
	}
	
	public UserDto findPassword(String email, String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("name", name);

        UserDto dto;
        dto = session.selectOne("ssafy.user.findpw", map);

        return dto;
	}
	
	public UserDto existGet(String email) {
		UserDto dto;
		dto = session.selectOne("ssafy.user.existGet", email);
		return dto;
	}
	
	public int newPassword(String email, String password){
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		int result = session.update("ssafy.user.newPassword", map);
		
		return result;
	}
	
	private String getAuthCode(int size) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0;

		while (buffer.length() < size) {
			num = random.nextInt(10);
			buffer.append(num);
		}
		return buffer.toString();
	}

	public String sendAuthMail(String email) throws Exception {
		String authKey = getAuthCode(6);

		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 인증 번호를 입력하시면 이메일 인증이 완료됩니다.</p>")
				.append("<p>인증번호 :: </p>" + authKey).toString());
		sendMail.setFrom("ssafy210215@gmail.com", "그리움 관리자");
		sendMail.setTo(email);
		sendMail.send();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("authKey", authKey);
		
		int result = session.insert("ssafy.user.loginsert", map);
		
		return authKey;
	}

	public int authemail(String email, String auth_num) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("auth_num", auth_num);
		int result = 0;
		if(session.selectOne("ssafy.user.authemail", map) != null) result = session.selectOne("ssafy.user.authemail", map);
		return result;
	}

	public int delauth(String email, String auth_num) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("auth_num", auth_num);
		int result = session.delete("ssafy.user.delauth", map);
		return result;
	}
}