package com.project.four.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.BoardDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.repo.AlertRepository;

@Service
public class AlertServiceImpl implements AlertService{

	@Autowired
	AlertRepository arepo;

	@Override
	public List<AlertDto> getalarm(String gone_id) throws Exception {
		// TODO Auto-generated method stub
		return arepo.getalarm(gone_id);
	}

	@Override
	public List<String> getgone(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return arepo.getgone(user_id);
	}

	@Override
	public BoardDto getaboard(int board_id) throws Exception {
		// TODO Auto-generated method stub
		return arepo.getabaord(board_id);
	}

	@Override
	public GalleryDto getagallary(int photo_id) throws Exception {
		// TODO Auto-generated method stub
		return arepo.getagallary(photo_id);
	}

	@Override
	public List<GaddressDto> getgglist(int photo_id) throws Exception {
		// TODO Auto-generated method stub
		return arepo.getgglist(photo_id);
	}

	@Override
	public String getname(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return arepo.getname(user_id);
	}
	
}
