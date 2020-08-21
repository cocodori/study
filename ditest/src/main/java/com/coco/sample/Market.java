package com.coco.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@ToString
@RequiredArgsConstructor
public class Market {
	//필드 주입
	@Autowired
	private Meat meat;
	
	//set 메서드 주입
	@Setter(onMethod_ = {@Autowired})
	private Vegetable vegetable;
	
	//생성자 주입
	private final Snack snack;
}
