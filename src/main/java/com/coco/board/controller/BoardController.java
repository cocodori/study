package com.coco.board.controller;

import com.coco.board.dto.BoardDTO;
import com.coco.board.dto.PageRequestDTO;
import com.coco.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Log4j2
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("----------list-----------");
        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("------------register-------------");
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO boardDto, RedirectAttributes redirect) {
        log.info(" boardDto : " + boardDto);

        //새로 추가된 엔티티 번호
        Long bno = boardService.register(boardDto);

        redirect.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void read(Long bno, Model model) {
        log.info("------------------read--------------------");
        log.info("bno : " + bno);

        BoardDTO post = boardService.getPost(bno);

        model.addAttribute("post", post);
    }

}
