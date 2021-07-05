package com.project.four.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FamilyDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.FuneralListDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;
import com.project.four.model.repo.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository arepo;

	@Override
	public GoneDto overid(String line) throws Exception {
		// TODO Auto-generated method stub
		return arepo.overid(line);
	}

	@Override
	public List<FuneralListDto> getfuneral(String region) throws Exception {
		// TODO Auto-generated method stub
		return arepo.getfuneral(region);
	}

	@Override
	public int registfuneral(FuneralDto fu_dto) throws Exception {
		// TODO Auto-generated method stub
		return arepo.registfuneral(fu_dto);
	}

	@Override
	public int registpro(ProcedureDto p_dto) throws Exception {
		// TODO Auto-generated method stub
		return arepo.registpro(p_dto);
	}

	@Override
	public int registfamily(FamilyDto f_dto) throws Exception {
		// TODO Auto-generated method stub
		return arepo.registfamily(f_dto);
	}

	@Override
	public int registcon(CondolanceDto c_dto) throws Exception {
		// TODO Auto-generated method stub
		return arepo.registcon(c_dto);
	}

	@Override
	public int registgone(GoneDto g_dto) throws Exception {
		// TODO Auto-generated method stub
		return arepo.registgone(g_dto);
	}

}
