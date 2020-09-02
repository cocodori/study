package com.coco.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coco.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolderPath = "C:\\work\\springex\\uploadFolder";

		for (MultipartFile multipartFile : uploadFile) {
			log.info("----------------------------------------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());	//byte
			
			File saveFile = new File(uploadFolderPath, multipartFile.getOriginalFilename());
			
			try {
				//transferTo(java.io.File객체) MultipartFile에서 제공하는 파일을 제공하는 메서드
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.info(e.getMessage());
			} 
		}
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload AJAX");
	}
	
	/*
	 * Ajax통신은 REST방식을 이용하기 때문에
	 * @ResponseBody 어노테이션을 붙여줘야 한다.
	 * 
	 * 이 메서드는 업로드된 파일 정보를 리턴한다.
	 * 
	 * */
	@ResponseBody 	
	@PostMapping(value = "/uploadAjaxPost")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost( MultipartFile[] uploadFile) {
		log.info("Upload Ajax Action");
		
		List<AttachFileDTO> attachFileList = new ArrayList<>();
		
		String uploadFolder = "C:\\work\\springex\\uploadFolder";
		
		String uploadFolderPath = getFolder();
		log.info("uploadFolderPath : " + uploadFolderPath);
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);

		log.info(uploadPath);
		log.info(uploadPath.exists());
		
		//오늘 날짜 폴더가 없다면 생성
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("===================================");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			//원본 파일 명
			String uploadFileName = multipartFile.getOriginalFilename();

			//업로드될 파일 정보를 저장할 DTO
			AttachFileDTO attachFileDTO = new AttachFileDTO();
			
			//파일 이름 중복 방지를 위한 UUID
			UUID uuid = UUID.randomUUID();

			//원본 파일명을 저장
			attachFileDTO.setFileName(uploadFileName);
			
			//UUID값을 저장
			attachFileDTO.setUuid(uuid.toString());
			
			//업로드된 파일 경로를 저장
			attachFileDTO.setUploadPath(uploadFolderPath);

			//원본 파일명과 uuid를 결합
			uploadFileName = uuid.toString()+"_"+uploadFileName;

			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				//파일이 이미지 파일인지 체크
				if(checkImageType(saveFile)) { //이미지 타입이라면 섬네일 생성
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
					
					attachFileDTO.setImage(true);
				}
				
				//파일 정보를 리스트에 저장
				attachFileList.add(attachFileDTO);
				log.info("attachFileList : " + attachFileList);
				
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		} // for()
		
		return new ResponseEntity<>(attachFileList, HttpStatus.OK);
	}
	
	//오늘 날짜로 된 경로를 문자열로 생성하는 메서드
	private String getFolder() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		return str.replace("-", File.separator);
	}
	
	//업로드된 파일이 이미지 파일인지 확인하는 메서드
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
			
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		
		return false;
	}
}
