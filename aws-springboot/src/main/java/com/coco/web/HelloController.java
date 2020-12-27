package com.coco.web;

import com.coco.web.dto.HelloResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDTO (@RequestParam("name") String name,
                                      @RequestParam("amount")int amount) {
        log.info("name : " + name);
        log.info("amount : " + amount);
        return new HelloResponseDto(name, amount);
    }
}
