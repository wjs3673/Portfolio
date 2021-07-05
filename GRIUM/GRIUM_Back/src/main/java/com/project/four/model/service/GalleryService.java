package com.project.four.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.RipDto;
import com.project.four.util.Pagination;

public interface GalleryService {
	
	void upload(GalleryDto gallary);
	String find_id(String email);
	GoneDto find_gone(String gone_id, String user_id);
	int get_total(int isOwner, String gone_id);
	List<GalleryDto> get_allList(Pagination pagination);
	List<GaddressDto> list_id(int photo_id);
	int delete(int photo_id);
	GalleryDto list_one(int photo_id);
	
	
	//애도합니다
	int checkrip(RipDto rip) throws Exception;
	int cancle(RipDto rip) throws Exception;
	int pressrip(RipDto rip) throws Exception;
	int updaterip(RipDto rip) throws Exception;
	
	//신고
	int checkalert(AlertDto alert) throws Exception;
	
	int pressralert(AlertDto alert) throws Exception;

	int upalert(AlertDto alert) throws Exception;

	int canalert(AlertDto alert) throws Exception;
	
	void alterzero(String gone_id, int photo_id);
	
	void alterone(String gone_id, int photo_id);
	
	void ripone(String user_id, int photo_id);
	void ripzero(String user_id, int photo_id);
	//삭제할꺼야
	int areuAuth(String gone_id, String user_id);
	
	
}
