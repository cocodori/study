package com.coco.board.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseEntity {
    LocalDateTime regDate;

    LocalDateTime modDate;
}
