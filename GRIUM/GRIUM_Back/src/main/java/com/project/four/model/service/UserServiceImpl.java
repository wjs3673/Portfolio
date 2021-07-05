package com.project.four.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.UserDto;
import com.project.four.model.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository urepo;

	@Override
	public int signup(UserDto user) {
		// TODO Auto-generated method stub
		return urepo.insert(user);
	}

	@Override
	public UserDto userLogin(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		return urepo.userLogin(email, password);
	}

	@Override
	public int edit(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		return urepo.update(user);
	}

	@Override
	public int checkOut(String passemail) throws Exception {
		// TODO Auto-generated method stub
		return urepo.checkOut(passemail);
	}

	@Override
	public int withdraw(UserDto user) {
		// TODO Auto-generated method stub
		return urepo.withdraw(user);
	}

	@Override
	public UserDto overid(String line) throws Exception {
		// TODO Auto-generated method stub
		return urepo.overid(line);
	}

	@Override
	public UserDto overemail(String email) throws Exception {
		// TODO Auto-generated method stub
		return urepo.overemail(email);
	}

	@Override
	public UserDto findPassword(String email, String name) throws Exception {
		// TODO Auto-generated method stub
		return urepo.findPassword(email, name);
	}

	@Override
	public UserDto existGet(String email) throws Exception {
		// TODO Auto-generated method stub
		return urepo.existGet(email);
	}

	@Override
	public int newPassword(String email, String newPassword) throws Exception {
		// TODO Auto-generated method stub
		return urepo.newPassword(email, newPassword);
	}

	@Override
	public String sendAuthMail(String mail) throws Exception {
		// TODO Auto-generated method stub
		return urepo.sendAuthMail(mail);
	}

	@Override
	public int authemail(String email, String auth_num) throws Exception {
		// TODO Auto-generated method stub
		return urepo.authemail(email, auth_num);
	}

	@Override
	public int delauth(String email, String auth_num) throws Exception {
		// TODO Auto-generated method stub
		return urepo.delauth(email, auth_num);
	}
}
