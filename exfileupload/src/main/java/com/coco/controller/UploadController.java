package com.coco.controller;

import java.io.File;

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
	
	@PostMapping("/uploadAjaxPost")
	@ResponseBody
	public void uploadAjaxPost( MultipartFile[] uploadFile) {
		log.info("Upload Ajax Action");
		String uploadFolder = "C:\\work\\springex\\uploadFolder";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("===================================");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
	}
	

}
