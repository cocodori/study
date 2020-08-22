package com.coco.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coco.domain.SampleDTO;
import com.coco.domain.SampleDTOList;
import com.coco.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class,
//				new CustomDateEditor(dateFormat, false));
//	}

	@GetMapping("/get")
	public void basicGet() {
		log.info("Hello GET");
	}
	
	@GetMapping("/autoCollect")
	public void parameterAutoCollect(SampleDTO dto) {
		SampleDTO parameters = dto;
		log.info(parameters);
		log.info(parameters.getAge() == 20);
	}
	
	//http://localhost:8080/sample/paramList?alphabet=a&alphabet=b&alphabet=c&alphabet=b&alphabet=d
	@GetMapping("/paramList")
	public void parameterIsList(@RequestParam("alphabet")ArrayList<String> list) {
		log.info(list);
	}
	
	@GetMapping("/paramArr")
	public void parameterIsArray(@RequestParam("alphabet")String[] arr) {
		log.info(Arrays.toString(arr));
	}
	
	@GetMapping("/objectList")
	public void objectList(SampleDTOList list) {
		log.info(list);
	}
	
	@GetMapping("dateBind")
	public void dateBind(TodoDTO dto) {
		log.info(dto);
	}
	
	@GetMapping("/testModel")
	public void model(SampleDTO sampleDTO,Model model) {
		SampleDTO dto = new SampleDTO();
		dto.setName("cocokim");
		dto.setAge(123);
		
		model.addAttribute("dto", dto);
	}
	
	@GetMapping("/testModel2")
	public void model2(SampleDTO dto, @ModelAttribute("page")int page, @ModelAttribute("test")String test) {
		log.info(dto);
		log.info(page);
		log.info(test);
	}
	
	@GetMapping("/testRttr")
	public String rttrTest(RedirectAttributes rttr) {
		//일회용, 화면으로 전달
		rttr.addFlashAttribute("msg", "다시 시도하세요.");
		
		//바인딩한 이름으로 파라미터 전달
		rttr.addAttribute("id","cocolog");
		
		return "redirect:/sample/rttr";
	}
	
	@GetMapping("/rttr")
	public void rttrTest() {
	}
}
		