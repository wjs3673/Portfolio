package com.project.four.model.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;
import com.project.four.model.dto.UserDto;

@Repository
public class GoneRepository {
	
	@Autowired
	private SqlSession session;
	
	public List<GoneDto> getList() throws Exception {
		List<GoneDto> goneList = new ArrayList<>();
		goneList = session.selectList("ssafy.gone.getList");
		return goneList;
	}

	public GoneDto callgone(String gone_id) {
		GoneDto dto = null;
		dto = session.selectOne("ssafy.gone.callGone", gone_id);
		return dto;
	}
	
//	public FamilyDto callfamily(String gone_id) {
//		FamilyDto dto = null;
//		dto = session.selectOne("ssafy.gone.callFamily", gone_id);
//		return dto;
//	}
	
	public ProcedureDto callprocedure(String gone_id) {
		ProcedureDto dto = null;
		dto = session.selectOne("ssafy.gone.callProcedure", gone_id);
		return dto;
	}
	
	public CondolanceDto callcondolance(String gone_id) {
		CondolanceDto dto = null;
		dto = session.selectOne("ssafy.gone.callCondolance", gone_id);
		return dto;
	}
	
	public FuneralDto callfuneral(String gone_id) {
		FuneralDto dto = null;
		dto = session.selectOne("ssafy.gone.callFuneral", gone_id);
		return dto;
	}
	
	public List<GalleryDto> getgallery(String gone_id){
		return session.selectList("ssafy.gallery.callGallery", gone_id);
	}
	
	public UserDto calluser(String user_id) {
		UserDto dto = null;
		dto = session.selectOne("ssafy.gone.callUser", user_id);
		return dto;
	}
}
