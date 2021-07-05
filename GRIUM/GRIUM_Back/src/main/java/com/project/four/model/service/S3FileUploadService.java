package com.project.four.model.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3FileUploadService {

	// 버킷 이름 동적 할당
	@Value("${cloud.aws.s3.bucket}")
	private String bucket; //버킷이름

	// 버킷 주소 동적 할당
	@Value("${cloud.aws.s3.bucket.url}")
	private String defaultUrl; //버킷 주소

	//생성자 의존성 주입
	//s3 전송객체를 만들 때  필요한 클래스래. 이클래스 생성자를 통해서 의존성 주입을 받는데
	private final AmazonS3Client amazonS3Client;

	public S3FileUploadService(AmazonS3Client amazonS3Client) {
		this.amazonS3Client = amazonS3Client;
	}

	//매개변수로 multipartFile 인터페이스가 존재해 이건 여러개의 파일을 한번에 보는 용도지
	//multipartFile 인터페이스 업로드 한 파일 및 파일 데이터를 표현하기 위한 용도
	public String upload(MultipartFile uploadFile) throws IOException {
		//getOrigianlFilename()메소드 : 파일이름을 구할는거야 //보낸 파일 이름을 가져오는거야
		String origName = uploadFile.getOriginalFilename();
		String url;
		try {
			// 확장자를 찾기 위한 코드 //뒤에 .png, .jpg 이걸 찾는 코드야
			final String ext = origName.substring(origName.lastIndexOf('.'));
			// 파일이름 암호화 //파일이름을 uuid로 랜덤값 돌리고 +파일형식 더해준 이름이야
			final String saveFileName = getUuid() + ext;
			// 파일 객체 생성
			// System.getProperty => 시스템 환경에 관한 정보를 얻을 수 있다. (user.dir = 현재 작업 디렉토리를 의미함)
			//"user.dir"을 통해서 현재 작업의 디렉토리를 구할수있어! 
			//따라서 현재작업 디렉토리 + 임시파일명을 가지고 File 클래스를 만드는것이야 아 이경로로 파일을 만드는거야
			File file = new File(System.getProperty("user.dir") + saveFileName);
			// 파일 변환
			//파일을 트랜스펄 할꺼야 이 file을! 이 이름ㅇ르 가지고 있는 파일을 트랜스펄할꺼라는ㄱ지
			uploadFile.transferTo(file); 
			// S3 파일 업로드
			uploadOnS3(saveFileName, file);
			// 주소 할당
			url = defaultUrl + saveFileName;
			// 파일 삭제
			file.delete();
		} catch (StringIndexOutOfBoundsException e) {
			url = null;
		}
		return url;
	}

	private static String getUuid() { //이걸로 가져오는데 이거 구지 해야돼?
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	//이 메소드는 Aws S3에 전송하는 메소드야
	private void uploadOnS3(final String findName, final File file) {
		// AWS S3 전송 객체 생성
		final TransferManager transferManager = new TransferManager(this.amazonS3Client);
		// 요청 객체 생성
		final PutObjectRequest request = new PutObjectRequest(bucket, findName, file);
		// 업로드 시도
		final Upload upload = transferManager.upload(request);

		try {
			upload.waitForCompletion();
		} catch (AmazonClientException amazonClientException) {
			amazonClientException.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
