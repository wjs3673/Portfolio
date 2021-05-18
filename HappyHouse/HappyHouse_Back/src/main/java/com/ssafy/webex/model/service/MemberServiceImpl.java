package com.ssafy.webex.model.service;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webex.model.dto.MemberDto;
import com.ssafy.webex.model.repo.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
//	@Override
//	public MemberDto login(MemberDto memberDto) throws Exception {
//		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
//	}
	
	@Autowired
	private SqlSession sqlSession;
	
	//public MemberDto login(Map<String, String> map) throws Exception {
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		// id pw 가 없다는건 로그인이 안됬다는거
//		String userid = map.get("userid");
//		String userpwd = map.get("userpwd");
		String userid = memberDto.getId();
		String userpwd = memberDto.getPassword();
		
		System.out.println("서비스까진들어옴");
		System.out.println(memberDto);
		if(userid == null || userpwd == null)
			throw new Exception();
		//return sqlSession.getMapper(MemberRepository.class).login(map);
		return sqlSession.getMapper(MemberRepository.class).login(memberDto);
	}
	
	@Override
	public void join(MemberDto memberDto) throws Exception {
		if (memberDto.getId() == null || memberDto.getPassword() == null || memberDto.getName() == null || memberDto.getAddr() == null || memberDto.getPhone() == null) {
			throw new Exception();
		}
		sqlSession.getMapper(MemberRepository.class).join(memberDto);
	}
	
	@Override
	public void modifyInfo(MemberDto memberDto) throws SQLException {
		sqlSession.getMapper(MemberRepository.class).modifyInfo(memberDto);
	}
	
	@Override
	public MemberDto getInfo(String userid) throws SQLException {
		return sqlSession.getMapper(MemberRepository.class).getInfo(userid);
	}
	
	@Override
	public void delete(String userid) throws SQLException {
		sqlSession.getMapper(MemberRepository.class).delete(userid);
	}
//	@Override
//	public MemberDto getPwd(String id, String name, String phone) throws SQLException {
//		return memberDao.getPwd(id, name, phone);
//	}
}
