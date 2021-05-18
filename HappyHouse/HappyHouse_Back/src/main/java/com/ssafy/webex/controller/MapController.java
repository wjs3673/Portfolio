package com.ssafy.webex.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.webex.model.dto.HouseDealDto;
import com.ssafy.webex.model.dto.HouseInfoDto;
import com.ssafy.webex.model.dto.ResidenceBuyDto;
import com.ssafy.webex.model.dto.ResidenceRentDto;
import com.ssafy.webex.model.dto.SidoGugunCodeDto;
import com.ssafy.webex.model.service.HouseDealService;
import com.ssafy.webex.model.service.HouseMapService;
import com.ssafy.webex.model.service.ResidenceDealService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/map")
public class MapController {

    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private HouseMapService houseMapService;
    
    @Autowired
    private HouseDealService houseDealService;
    
    @Autowired
    private ResidenceDealService residenceDealService;

    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
    @GetMapping("/sido")
    public ResponseEntity<List<SidoGugunCodeDto>> selectSidocode() throws Exception {
        logger.debug("retrieveBoard - 호출");
        return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
    }
    
    @GetMapping("/gugun/{sido_code}")
    public ResponseEntity<List<SidoGugunCodeDto>> selectGuguncode(@PathVariable String sido_code) throws Exception {
        return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido_code), HttpStatus.OK);
    }
    
    @GetMapping("/dong/{gugun_code}")
    public ResponseEntity<List<HouseInfoDto>> selectDongcode(@PathVariable String gugun_code) throws Exception {
        return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(gugun_code), HttpStatus.OK);
    }
    
    @GetMapping("/apt/{dong_name}")
    public ResponseEntity<List<HouseInfoDto>> selectHouseinfo(@PathVariable String dong_name) throws Exception {
    	List<HouseInfoDto> list = houseMapService.getHouseInDong(dong_name);
//    	for(HouseInfoDto h: list)
//    		System.out.println(h);
        return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getHouseInDong(dong_name), HttpStatus.OK);
    }

    @GetMapping("/residence/{dong_name}")
    public ResponseEntity<List<ResidenceRentDto>> selectResidenceinfo(@PathVariable String dong_name) throws Exception {
        return new ResponseEntity<List<ResidenceRentDto>>(residenceDealService.getResidenceInDong(dong_name), HttpStatus.OK);
    }

    @GetMapping("/apt/deal/{aptName}")
    public ResponseEntity<List<HouseDealDto>> selectAptDealinfo(@PathVariable String aptName) throws Exception {
        return new ResponseEntity<List<HouseDealDto>>(houseDealService.getAptDealName(aptName), HttpStatus.OK);
    }
    
    @GetMapping("/residence/deal/{buildingName}")
    public ResponseEntity<List<ResidenceBuyDto>> selectResidenceDealinfo(@PathVariable String buildingName) throws Exception {
    	System.out.println(">>>"+residenceDealService.getResidenceDealName(buildingName));
        return new ResponseEntity<List<ResidenceBuyDto>>(residenceDealService.getResidenceDealName(buildingName), HttpStatus.OK);
    }
}