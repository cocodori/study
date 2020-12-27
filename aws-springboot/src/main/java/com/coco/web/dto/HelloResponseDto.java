package com.coco.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //선언한 모든 final필드가 포함된 생성자를 생성한다.
public class HelloResponseDto {

    private final String name;
    private final int amount;
}

