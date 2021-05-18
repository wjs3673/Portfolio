package com.ssafy.webex;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.webex.model.service.JwtService;
import com.ssafy.webex.model.service.MemberService;

public class Snippet {
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MemberService memberService;
}

