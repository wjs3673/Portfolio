package com.project.four.model.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;

@Repository
public class AlertRepository {

	@Autowired
	private SqlSession session;

	public List<AlertDto> getalarm(String gone_id) throws Exception{
		List<AlertDto> list = null;
		if(session.selectList("ssafy.alert.getalarm", gone_id) != null) list = session.selectList("ssafy.alert.getalarm", gone_id);
		return list;
	}

	public List<String> getgone(String user_id) throws Exception{
		List<String> list = null;
		if(session.selectList("ssafy.alert.getgone", user_id) != null) list = session.selectList("ssafy.alert.getgone", user_id);
		return list;
	}

	public BoardDto getabaord(int board_id) throws Exception{
		BoardDto dto = null;
		dto = session.selectOne("ssafy.alert.getabaord", board_id);
		return dto;
	}

	public GalleryDto getagallary(int photo_id) throws Exception{
		GalleryDto dto = null;
		dto = session.selectOne("ssafy.alert.getagallary", photo_id);
		return dto;
	}

	public List<GaddressDto> getgglist(int photo_id) throws Exception{
		List<GaddressDto> list = null;
		if(session.selectList("ssafy.alert.getgglist", photo_id) != null) list = session.selectList("ssafy.alert.getgglist", photo_id);
		return list;
	}

	public String getname(String user_id) throws Exception{
		String name = null;
		if(session.selectOne("ssafy.alert.getname", user_id) != null) name = session.selectOne("ssafy.alert.getname", user_id);
		return name;
	}
	
}
