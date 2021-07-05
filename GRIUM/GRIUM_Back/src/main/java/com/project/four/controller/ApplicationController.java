package com.project.four.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.four.model.dto.CondolanceDto;
import com.project.four.model.dto.FamilyDto;
import com.project.four.model.dto.FuneralDto;
import com.project.four.model.dto.FuneralListDto;
import com.project.four.model.dto.GoneDto;
import com.project.four.model.dto.ProcedureDto;
import com.project.four.model.service.ApplicationService;
import com.project.four.util.AES256Util;
import com.project.four.util.S3Util;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value = "/appli")
public class ApplicationController {

	public static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	
	@Autowired
	private AES256Util util;
	
	@Autowired
	private S3Util s3util;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket; // 버킷이름

	@Value("${cloud.aws.s3.bucket.url}")
	private String defaultUrl; // 버킷 주소
	
	@Autowired
	private ApplicationService appliservice;
	
	@ApiOperation(value="Application regist", notes="장례 신청")
	@PostMapping("/regist")
	public ResponseEntity<Map<String, Object>> regist(@RequestPart MultipartFile pfile, @RequestPart GoneDto g_dto, @RequestPart FuneralDto fu_dto, @RequestPart ProcedureDto p_dto, @RequestPart CondolanceDto c_dto) {
		// @RequestPart FamilyDto f_dto,
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		boolean isOk = true;
		
		try {
			logger.info("====================================> gone_id 생성");
			while(isOk) {
				StringBuffer make_id = new StringBuffer();

				for (int i = 0; i < 6; i++) {
					char a = (char) ((Math.random() * 26) + 97); // 소문자
					int ann = (int) (Math.random() * 9) + 1; // 숫자
					make_id.append(a);
					make_id.append(ann);
				}

				char b = (char) ((Math.random() * 26) + 97);
				make_id.append(b);
				logger.info("====================================> " + make_id);
				String line = make_id.toString();
				// 중복 확인
				GoneDto overlap_id = appliservice.overid(line);
				
				if (overlap_id == null) {
					logger.info("====================================> gone_id 셋팅");
					isOk = false;
					g_dto.setGone_id(line);
				}
			}
			
			logger.info("====================================> 장례 신청/고인 정보 입력"); // GONE_TB
//			PHOTO_PATH VARCHAR(100) NOT NULL :: aws path
//			SERVER_PHOTO VARCHAR(50) NOT NULL :: UUID 이름
//			ORIGIN_PHOTO VARCHAR(50) NOT NULL :: 사진 원래 이름
			
			String originName = pfile.getOriginalFilename();

			String ext = originName.substring(originName.lastIndexOf('.')); // 파일형식
			String saveFileName = UUID.randomUUID().toString() + ext; // 파일이름 암호화한거야
			String path = System.getProperty("user.dir"); // 현재 backend위치
			File file = new File(path, saveFileName); // 여기까지 convert
			
			String line = "goin/";
			saveFileName = line + saveFileName;

			try {
				pfile.transferTo(file);
				s3util.setS3Client().putObject(new PutObjectRequest(bucket, saveFileName, file)
						.withCannedAcl(CannedAccessControlList.PublicRead));
				String url = s3util.setS3Client().getUrl(bucket, saveFileName).toString();
				logger.info("====================================> 저장경로 : " + url);
				file.delete();

				g_dto.setPhoto_path(url);
				g_dto.setServer_photo(saveFileName);
				g_dto.setOrigin_photo(originName);

			} catch (IllegalStateException | IOException e) {
				logger.error("사진 등록 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				e.printStackTrace();
			}
			
			int result_g = appliservice.registgone(g_dto);
			
			if (result_g == 1) {
				logger.info("====================================> 장례 신청/고인 정보 등록 성공");
				resultMap.put("message", "등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "등록에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
			
			
			logger.info("====================================> 장례 신청/장례 정보 입력"); // FUNERAL_TB, PROCEDURE_TB, FAMILY_TB
			System.out.println(fu_dto.getFuneral_nm());
			fu_dto.setGone_id(g_dto.getGone_id());
			int result_fu = appliservice.registfuneral(fu_dto);
			
			p_dto.setGone_id(g_dto.getGone_id());
			int result_p = appliservice.registpro(p_dto);
			
			if (result_fu == 1 && result_p == 1) {
				logger.info("====================================> 장례 신청/장례 정보 등록 성공");
				resultMap.put("message", "등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
//				logger.info("====================================> 실패" + result_fu + result_p + result_f);
				resultMap.put("message", "등록에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
			
			
			logger.info("====================================> 장례 신청/계좌 정보 입력"); // CONDOLANCE_TB
			c_dto.setGone_id(g_dto.getGone_id());
			c_dto.setAccount(util.encrypt(c_dto.getAccount()));
			c_dto.setHolder(util.encrypt(c_dto.getHolder()));
			int result_c = appliservice.registcon(c_dto);
			
			if (result_c == 1) {
				logger.info("====================================> 장례 신청/계좌 정보 등록 성공");
				//  String url = "http://localhost:8081/gone/" + g_dto.getGone_id() + "#/gonedetail?gone_id=" + g_dto.getGone_id();
				String url = "http://i4c104.p.ssafy.io/#/gonedetail?gone_id=" + g_dto.getGone_id();
				resultMap.put("url", url);
				resultMap.put("message", "등록에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "등록에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value="Get Funeral", notes="장례식장 받기")
	@GetMapping("/getfuneral")
	public ResponseEntity<Map<String, Object>> getfuneral(@RequestParam String region) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		List<FuneralListDto> list = null;
		
		try {
			logger.info("====================================> 장례식장 부르기");
			list = appliservice.getfuneral(region);
			
			if(list != null) {
				logger.info("====================================> 부르기 성공");
				resultMap.put("funeral_list", list);
				resultMap.put("message", "부르기에 성공하였습니다.");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "부르기에 실패하였습니다.");
				status = HttpStatus.ACCEPTED;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("장례식장 부르기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
