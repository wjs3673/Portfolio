package com.ssafy.webex.model.repo;

import java.util.List;

import com.ssafy.webex.model.dto.HouseInfoDto;
import com.ssafy.webex.model.dto.SidoGugunCodeDto;

public interface HouseMapRepository {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getHouseInDong(String dong) throws Exception;
//	SELECT * FROM houseinfo WHERE code = #{dong} ORDER BY AptName
}