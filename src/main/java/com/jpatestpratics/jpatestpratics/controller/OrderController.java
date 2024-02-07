package com.jpatestpratics.jpatestpratics.controller;

import com.jpatestpratics.jpatestpratics.domain.Item;
import com.jpatestpratics.jpatestpratics.domain.Member;
import com.jpatestpratics.jpatestpratics.service.ItemService;
import com.jpatestpratics.jpatestpratics.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        log.info("createform");
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }
}
