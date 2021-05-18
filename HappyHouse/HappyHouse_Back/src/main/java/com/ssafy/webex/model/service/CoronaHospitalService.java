package com.ssafy.webex.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.CoronaHospitalDto;
import com.ssafy.webex.util.PageNavigation;

public interface CoronaHospitalService {
	public List<CoronaHospitalDto> searchAll(Map<String, String> map) throws Exception;
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	public List<CoronaHospitalDto> selectAll(int pageNum);
	public int getCount();
}