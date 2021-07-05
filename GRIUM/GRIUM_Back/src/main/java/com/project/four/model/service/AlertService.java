package com.project.four.model.service;

import java.util.List;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;

public interface AlertService {

	List<AlertDto> getalarm(String gone_id) throws Exception;

	List<String> getgone(String user_id) throws Exception;

	BoardDto getaboard(int board_id) throws Exception;

	GalleryDto getagallary(int photo_id) throws Exception;

	List<GaddressDto> getgglist(int photo_id) throws Exception;

	String getname(String user_id) throws Exception;

}
