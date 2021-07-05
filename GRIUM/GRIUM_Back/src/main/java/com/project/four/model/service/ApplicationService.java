package com.project.four.model.service;

import java.util.List;

import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FamilyDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.FuneralListDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;

public interface ApplicationService {

	GoneDto overid(String line) throws Exception;

	List<FuneralListDto> getfuneral(String region) throws Exception;

	int registfuneral(FuneralDto fu_dto) throws Exception;

	int registpro(ProcedureDto p_dto) throws Exception;

	int registfamily(FamilyDto f_dto) throws Exception;

	int registcon(CondolanceDto c_dto) throws Exception;

	int registgone(GoneDto g_dto) throws Exception;

}
