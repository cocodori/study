package com.coco.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

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
	 * */
	@PostMapping("/uploadAjaxPost")
	@ResponseBody 	
	public void uploadAjaxPost( MultipartFile[] uploadFile) {
		log.info("Upload Ajax Action");
		String uploadFolder = "C:\\work\\springex\\uploadFolder";
		
		File uploadPath = new File(uploadFolder, getFolder());

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

			//파일 이름 중복 방지를 위한 UUID
			UUID uuid = UUID.randomUUID();
			
			String uploadFileName = uuid.toString()+"_"+multipartFile.getOriginalFilename();

			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
	}
	
	//오늘 날짜로 된 경로를 문자열로 생성하는 메서드
	private String getFolder() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		return str.replace("-", File.separator);
	}
	

}
