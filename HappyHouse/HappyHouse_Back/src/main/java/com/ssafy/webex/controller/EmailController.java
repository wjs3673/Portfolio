package com.ssafy.webex.controller;

import java.util.Date;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	public JavaMailSender javaMailSender;

	@GetMapping(value = "/{email}")
	public ResponseEntity<String> email(@PathVariable String email) throws MessagingException {
		Random r = new Random();
		int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분 (난수)
		System.out.println(email + " " + dice);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,false);
		helper.setFrom("happyhouse");
		helper.setTo(email);
		helper.setSubject("Happy House 인증번호 요청");
		helper.setText("인증번호는 " + dice + " 입니다.");
		javaMailSender.send(message);
		
//		 MimeMessage message = javaMailSender.createMimeMessage();
//		    MimeMessageHelper helper;
//		    try {
//		      helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO,
//		        StandardCharsets.UTF_8.name());
//		      helper.setTo("lldkdlzb@gmail.com");
//		      helper.setText("test", true);
//		      helper.setSubject("test");
//		      
//		      javaMailSender.send(message);
//		    } catch (Exception e) {
//		    e.printStackTrace();
//		      System.out.println("email 에러");
//		    }
		
		try {
			System.out.println("이메일 전송 끝 완료");
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("이메일 전송 에러발생");
			return new ResponseEntity<String>("success", HttpStatus.NO_CONTENT);
		}
	}
}
