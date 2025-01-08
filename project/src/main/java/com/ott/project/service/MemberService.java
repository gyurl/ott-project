package com.ott.project.service;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ott.project.domain.Member;
import com.ott.project.form.MemberForm;
import com.ott.project.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(MemberForm memberForm) {
        System.out.println("Register method called");

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setEmail(memberForm.getEmail());
        member.setNickname(memberForm.getNickname());
        member.setTelephone(memberForm.getTelephone());
        member.setPassword(passwordEncoder.encode(memberForm.getPassword()));

        memberRepository.save(member);
    }

    public Optional<Member>login(String name, String password){
        Optional<Member> member = memberRepository.findByName(name);
        if(member.isPresent() && passwordEncoder.matches(password, member.get().getPassword())){
            return member;
        }
        return Optional.empty();
    }

}
