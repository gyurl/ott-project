package com.ott.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL AUTO
    @Column(name = "id")
    private Long id;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "이름을 입력하세요")
    private String name;
    
    @Column(name = "nickname", nullable = false)
    @NotBlank(message = "닉네임을 입력하세요")
    private String nickname;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    @Column(name = "telephone", nullable = false)
    @NotBlank(message = "휴대폰 번호를 입력하세요")
    private String telephone;
    
}
