package com.ott.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ott.project.form.LoginForm;
import com.ott.project.form.MemberForm;
import com.ott.project.repository.MemberRepository;
import com.ott.project.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    private final MemberRepository memberRepository;

    @PostMapping("/login")
    public ResponseEntity<String>login(LoginForm loginForm){
        return memberService.login(loginForm.getName(), loginForm.getPassword())
        .map(member -> ResponseEntity.ok("Login Seccessful"))
        .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String>logout(){
        return ResponseEntity.ok("Logout Seccesseful");
    }
    
    

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody MemberForm memberForm) {
        log.info("Register request received: {}", memberForm);  // 요청 로그 남기기
        System.out.println("구동 전");
        try {
            memberService.register(memberForm);
            log.info("회원가입 성공: {}", memberForm.getName());  // 성공 로그 남기기
            return ResponseEntity.ok("Registration successful");

        } catch (IllegalArgumentException e) {
            log.warn("회원가입 실패: {}", e.getMessage());  // 경고 로그 남기기
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            log.error("회원가입 실패: {}", e.getMessage(), e);  // 에러 로그 남기기
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

     //check 0906 중복 검사
    @GetMapping("/check-username")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkName(@RequestParam String name) {
        boolean isTaken = memberRepository.findByName(name).isPresent();
        Map<String, Object> response = new HashMap<>();
        response.put("available", !isTaken);
        return ResponseEntity.ok(response);
    }
    

}
