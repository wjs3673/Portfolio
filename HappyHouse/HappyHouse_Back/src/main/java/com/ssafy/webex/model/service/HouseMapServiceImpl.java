package com.ssafy.webex.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webex.model.dto.HouseInfoDto;
import com.ssafy.webex.model.dto.SidoGugunCodeDto;
import com.ssafy.webex.model.repo.HouseMapRepository;
@Service
public class HouseMapServiceImpl implements HouseMapService {
	@Autowired
	private SqlSession sqlSession;
    
    @Override
    public List<SidoGugunCodeDto> getSido() throws Exception {
        return sqlSession.getMapper(HouseMapRepository.class).getSido();
    }

    @Override
    public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
        return sqlSession.getMapper(HouseMapRepository.class).getGugunInSido(sido);
    }

    @Override
    public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
    	return sqlSession.getMapper(HouseMapRepository.class).getDongInGugun(gugun);
    }

    @Override
    public List<HouseInfoDto> getHouseInDong(String dong) throws Exception {
    	return sqlSession.getMapper(HouseMapRepository.class).getHouseInDong(dong);
    }
//
//    @Override
//    public List<HouseDealDto> getAptInfoInAptName(String aptName) throws Exception {
//        return HouseMapDaoImpl.getHouseMapDao().getAptInfoInAptName(aptName);
//    }
//    
//    @Override
//    public List<HouseLocationDto> getLocationInDong(String dong) throws Exception {
//        return HouseMapDaoImpl.getHouseMapDao().getLocationInDong(dong);
//    }

}