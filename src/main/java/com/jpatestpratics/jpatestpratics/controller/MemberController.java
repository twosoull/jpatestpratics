package com.jpatestpratics.jpatestpratics.controller;

import com.jpatestpratics.jpatestpratics.domain.Address;
import com.jpatestpratics.jpatestpratics.domain.Member;
import com.jpatestpratics.jpatestpratics.form.MemberForm;
import com.jpatestpratics.jpatestpratics.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;



    @GetMapping("/members/new")
    public String createForm(Model model){
      log.info("create Form");
      model.addAttribute("memberForm" , new MemberForm());

      return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "members/new";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
