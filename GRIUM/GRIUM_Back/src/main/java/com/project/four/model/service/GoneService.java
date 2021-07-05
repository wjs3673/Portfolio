package com.project.four.model.service;

import java.util.List;

import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;
import com.project.four.model.dto.UserDto;

public interface GoneService {
	
	List<GoneDto> getList() throws Exception; // 고인 리스트 가져오기

	GoneDto getgone(String gone_id) throws Exception; // 고인 정보 가져오기
	
//	FamilyDto getfamily(String gone_id) throws Exception; // 고인 가족 정보 가져오기
	
	ProcedureDto getprocedure(String gone_id) throws Exception; // 장례 진행 정보 가져오기
	
	CondolanceDto getcondolance(String gone_id) throws Exception; // 부의함 정보 가져오기
	
	FuneralDto getfuneral(String gone_id) throws Exception; // 장례식 정보 가져오기
	
	List<GalleryDto> getgallery(String gone_id) throws Exception; // 고인 관련 사진 가져오기
	
	UserDto getuser(String user_id) throws Exception; // 상주 정보 가져오기
}
