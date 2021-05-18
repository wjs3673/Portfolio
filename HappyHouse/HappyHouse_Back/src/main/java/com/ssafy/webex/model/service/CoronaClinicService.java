package com.ssafy.webex.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.webex.model.dto.CoronaClinicDto;
import com.ssafy.webex.util.PageNavigation;

public interface CoronaClinicService {
	public List<CoronaClinicDto> searchAll(Map<String, String> map) throws Exception;
	public PageNavigation makePageNavigation(Map<String, String> map)
			throws Exception;
}
