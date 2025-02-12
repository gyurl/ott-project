package com.ott.project.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

        @NotBlank(message = "이름을 입력하세요")
        private String name;

        @NotBlank(message = "닉네임을 입력하세요")
        private String nickname;

        @NotBlank(message = "비밀번호를 입력하세요")
        private String password;

        @NotBlank(message = "이메일을 입력하세요")
        private String email;

        @NotBlank(message = "휴대폰 번호를 입력하세요")
        private String telephone;
}
