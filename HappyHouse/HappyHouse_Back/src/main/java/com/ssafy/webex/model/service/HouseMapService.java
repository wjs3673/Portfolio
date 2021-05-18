package com.ssafy.webex.model.service;

import java.util.List;

import com.ssafy.webex.model.dto.HouseInfoDto;
import com.ssafy.webex.model.dto.SidoGugunCodeDto;

public interface HouseMapService {
    
    List<SidoGugunCodeDto> getSido() throws Exception;
    List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
    List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
    List<HouseInfoDto> getHouseInDong(String dong) throws Exception;
//    List<HouseDealDto> getAptInfoInAptName(String aptName) throws Exception;
//    List<HouseLocationDto> getLocationInDong(String dong) throws Exception;
}