package com.project.four.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.project.four.model.dto.AlertDto;
import com.project.four.model.dto.GaddressDto;
import com.project.four.model.dto.GalleryDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.RipDto;
import com.project.four.model.repo.GalleryRepository;
import com.project.four.util.Pagination;

@Service
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	GalleryRepository grepo;

	@Override
	@Transactional
	public void upload(GalleryDto gallary) {
		// TODO Auto-generated method stub
		grepo.upload(gallary);
		System.out.println(gallary.getPhoto_id());
		grepo.registfile(gallary);
	}

	@Override
	public String find_id(String email) {
		// TODO Auto-generated method stub
		String result = grepo.find_id(email);
		System.out.println("serviceImpl : reuslt : " + result);
		return result;
	}

	@Override
	public GoneDto find_gone(String gone_id, String user_id) {
		// TODO Auto-generated method stub
		return grepo.find_gone(gone_id, user_id);
	}

	@Override
    public int get_total(int isOwner, String gone_id) {
        // TODO Auto-generated method stub
        return grepo.get_total(isOwner, gone_id);
    }

	@Override
	public List<GalleryDto> get_allList(Pagination pagination) {
		// TODO Auto-generated method stub
		return grepo.get_allList(pagination);
	}

	@Override
	public List<GaddressDto> list_id(int photo_id) {
		// TODO Auto-generated method stub
		return grepo.list_id(photo_id);
	}

	@Override
	public int delete(int photo_id) {
		// TODO Auto-generated method stub
		return grepo.delete(photo_id);
	}

	@Override
	public GalleryDto list_one(int photo_id) {
		// TODO Auto-generated method stub
		return grepo.list_one(photo_id);
	}

	@Override
	public int checkrip(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return grepo.checkrip(rip);
	}

	@Override
	public int cancle(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return grepo.cancle(rip);
	}

	@Override
	public int pressrip(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return grepo.pressrip(rip);
	}

	@Override
	public int updaterip(RipDto rip) throws Exception {
		// TODO Auto-generated method stub
		return grepo.updaterip(rip);
	}

	@Override
	public int checkalert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return grepo.checkalert(alert);
	}

	@Override
	public int pressralert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return grepo.pressalert(alert);
	}

	@Override
	public int upalert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return grepo.upalert(alert);
	}

	@Override
	public int canalert(AlertDto alert) throws Exception {
		// TODO Auto-generated method stub
		return grepo.canalert(alert);
	}

	// flag
	@Override
	public void alterzero(String gone_id, int photo_id) {
		// TODO Auto-generated method stub
		grepo.alterzero(gone_id, photo_id);
	}

	@Override
	public void alterone(String gone_id, int photo_id) {
		// TODO Auto-generated method stub
		grepo.alterone(gone_id, photo_id);
	}

	@Override
	public void ripone(String user_id, int photo_id) {
		// TODO Auto-generated method stub
		grepo.ripone(user_id, photo_id);

	}

	@Override
	public void ripzero(String user_id, int photo_id) {
		// TODO Auto-generated method stub
		grepo.ripzero(user_id, photo_id);

	}

	//삭제
	@Override
	public int areuAuth(String gone_id, String user_id) {
		// TODO Auto-generated method stub
		return grepo.areuAuth(gone_id, user_id);
	}
}
