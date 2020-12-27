package com.coco.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
		
		String uploadFolderPath = "/home/hoon/learning2020/SpringEx/uploadFolder";

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
	@PreAuthorize("isAuthenticated()")
	@ResponseBody 	
	@PostMapping(value = "/uploadAjaxPost")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost( MultipartFile[] uploadFile) {
		log.info("Upload Ajax Action");
		
		List<AttachFileDTO> attachFileList = new ArrayList<>();
		
		String uploadFolder = "/home/hoon/learning2020/SpringEx/uploadFolder";
		
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
	

	/*
	 * getFile()은 문자열로 파일의 경로가 포함된 fileName을 파라미터로 받아서 byte[]로 이미지 파일 데이터를 전송한다.
	 * byte[]로 이미지 파일 데이터를 전송할 때 신경 쓸 점은 MIME타입이 파일 종류에 따라 달라진다는 점이다.
	 * Files객체가 가진 static메서드인 probeContentType(Path path)를 이용해서
	 * 적합한 MIME타입 데이터를 HTTP헤더 메세지에 포함시킬 수 있다.
	 *	/disaply?fileName=/2020/09/02/aaa.png 이런 식으로 테스트 해볼 수 있다.
	 * */
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("fileName : " + fileName);
		
		File file = new File("/home/hoon/learning2020/SpringEx/uploadFolder" + fileName);
		
		log.info("file : " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders httpHeader = new HttpHeaders();
			
			httpHeader.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
					httpHeader, HttpStatus.OK);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return result;
	}
	
	//첨부 파일 다운로드
	/*
	 * MIME타입은 다운로드 할 수 있는 'application/octet-stream'으로 지정한다.
	 * 다운로드 시 저장되는 이름은 'Content-Dispostion'을 이용해서 지정한다.
	 * 파일 이름에 대한 문자열 처리는 파일 이름이 한글인 경우 저장할 때 깨지는 문제를 막기 위해서
	 * 브라우저에서 '/download?fileName=xxxx'와 같이 호출하면 다운로드 되는 것을 확인할 수 있다.
	 *  
	 * */
	@ResponseBody
	@GetMapping(value = "/download")
	public ResponseEntity<Resource> downloadFile(String fileName) {
		log.info("download File : " + fileName);
		Resource resource = new FileSystemResource("/home/hoon/learning2020/SpringEx/uploadFolder/" + fileName);
		
		log.info("resource : " + resource);
		
		//그런 파일 없어요.
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String resourceName = resource.getFilename();
		
		log.info("resourceName : " + resourceName);
		
		//UUID 지우기
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
		
		log.info("resourceOriginalName : " + resourceOriginalName);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		log.info("httpHeaders : " + httpHeaders);
		
		try {
			httpHeaders.add("Content-Disposition", "attachment; filename="+new String(resourceOriginalName.getBytes("UTF-8"),
					"ISO-8859-1"));
			
			log.info("httpHeaders : " + httpHeaders);
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage());
		}
		
		return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
	}
	
	/*
	 * 브라우저에 전송하는 파일 이름과 종류를 파라미터로 받는다.
	 * 브라우저에서 전송 받는 파일 이름은 '경로+UUID+_+파일 이름'으로 구성되어 있다.
	 * 일반 파일의 경우 파일만 삭제한다.
	 * 이미지 파일의 경우 섬네일까지 삭제한다.
	 * 
	 * */
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("---------------'/deleteFile'-----------------");
		
		
		log.info("deleteFile : " + fileName);
		log.info("type : " + type); 
		
		File file;
		
		try {
			file = new File("/home/hoon/learning2020/SpringEx/uploadFolder/"+URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			
			//이미지 파일일 경우 섬네일도 함께 삭제
			if(type.equals("image")) {
				String largefileName = file.getAbsolutePath().replace("s_","");
				log.info("largefileName : " + largefileName);
				
				file = new File(largefileName);
				
				file.delete();
			}
			
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>("deleted", HttpStatus.OK);
		

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
