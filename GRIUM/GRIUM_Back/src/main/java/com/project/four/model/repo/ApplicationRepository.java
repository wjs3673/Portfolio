package com.project.four.model.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FamilyDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.FuneralListDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;

@Repository
public class ApplicationRepository {

	@Autowired
	private SqlSession session;

	public GoneDto overid(String gone_id) throws Exception{
		GoneDto dto;
		dto = session.selectOne("ssafy.appli.overid", gone_id);
		return dto;
	}

	public List<FuneralListDto> getfuneral(String region) throws Exception{
		List<FuneralListDto> list = null;
		list = session.selectList("ssafy.appli.getfuneral", region);
		return list;
	}

	public int registfuneral(FuneralDto fu_dto) throws Exception{
		int result = session.insert("ssafy.appli.registfu", fu_dto);
		return result;
	}

	public int registpro(ProcedureDto p_dto) throws Exception{
		int result = session.insert("ssafy.appli.registpro", p_dto);
		return result;
	}

	public int registfamily(FamilyDto f_dto) throws Exception{
		int result = session.insert("ssafy.appli.registfamily", f_dto);
		return result;
	}

	public int registcon(CondolanceDto c_dto) throws Exception{
		int result = session.insert("ssafy.appli.registcon", c_dto);
		return result;
	}

	public int registgone(GoneDto g_dto) throws Exception{
		int result = session.insert("ssafy.appli.registgone", g_dto);
		return result;
	}
	
}
