package com.coco.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.coco.domain.BoardAttachVO;
import com.coco.mapper.BoardAttachMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
 *  잘못 업로드 된 파일을 삭제하는 클래스
 *  tbl_attach에 저장된 파일 이름과 실제 업로드 폴더에 저장된 파일 이름을 비교하여,
 *  업로드 폴더에는 있지만, tbl_attach에는 없는 파일을 폴더에서도 삭제한다.
 * 
 * */

@Log4j
@Component
@RequiredArgsConstructor
public class FileCheckTask {

	private final BoardAttachMapper boardAttachMapper;
	
	//cron속성 s-m-h-d-M-week(-y)
	//매일 새벽 2시에 실행
	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception {
		log.warn("File Check Task Run!!!");
		log.warn("=================================");
		
		//어제 날짜로 데이터베이스에 저장된 파일 목록을 가져온다.
		List<BoardAttachVO> dbFileList = boardAttachMapper.getOldFiles();
		
		//실제로 파일이 저장되는 디렉터리 안에 파일 목록과 DB에 저장된 파일 목록을 비교한다.
		List<Path> fileListPaths 
			= dbFileList.stream()
			.map(boardVO -> Paths.get("C:\\work\\springex\\uploadFolder"
					, boardVO.getUploadPath()
					,boardVO.getUuid() + "_"+boardVO.getFileName()))
			.collect(Collectors.toList());
		
		//썸네일을 가진 파일
		dbFileList.stream()
			.filter(boardVO -> boardVO.isFileType() == true)
			.map(boardVO -> Paths.get("C:\\work\\springex\\uploadFolder"
					,boardVO.getUploadPath(),
					"s_"+boardVO.getUuid() +"_"+ boardVO.getFileName()))
			.forEach(path -> fileListPaths.add(path));
		
		log.warn("===========================================================");
		
		fileListPaths.forEach(path -> log.warn(path));

		log.info("fileListPath : " + fileListPaths);
		log.info("dbFileList : " +dbFileList );
		
		//어제 날짜 디렉터리에 저장된 파일
		File targetDir = Paths.get("C:\\work\\springex\\uploadFolder", getFolderYesterDay()).toFile();
		
		log.info("targetDir : " + targetDir);
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.info("removeFiles : " + removeFiles);
		
		log.warn("--------------------------------------------------------------");
		
		for(File file : removeFiles) {
			log.warn("file.getAbsolutePath : " + file.getAbsolutePath());
			file.delete();
		}
		
		
	}
	
	private String getFolderYesterDay() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DATE, -1);
		
		String str = simpleDateFormat.format(calendar.getTime());
		log.info("str : " + str );
		log.info("simpleDataFormat : " + simpleDateFormat);
		log.info("calendar : " + calendar);
		
		String result = str.replace("-", File.separator);
		log.info("result : " + result);
		return result;
	}
}
