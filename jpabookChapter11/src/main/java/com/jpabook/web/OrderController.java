package com.jpabook.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpabook.domain.Item;
import com.jpabook.domain.Member;
import com.jpabook.service.ItemService;
import com.jpabook.service.MemberService;
import com.jpabook.service.OrderService;

@Controller
public class OrderController {
	@Autowired OrderService orderService;
	@Autowired MemberService memberService;
	@Autowired ItemService itemService;
	
	@RequestMapping(value = "/order" , method = RequestMethod.GET)
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
			@RequestParam("count") int count) {
		orderService.order(memberId, itemId, count);
		
		return "redirect:/orders";
	}
	

}
