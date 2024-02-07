package com.jpatestpratics.jpatestpratics.service;

import com.jpatestpratics.jpatestpratics.domain.Member;
import com.jpatestpratics.jpatestpratics.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member){
        memberRepository.save(member);
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
