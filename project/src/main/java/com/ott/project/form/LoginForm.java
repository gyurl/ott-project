package com.ott.project.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

        @NotBlank(message = "이름을 입력하세요")
        private String name;

        @NotBlank(message = "비밀번호를 입력하세요")
        private String password;

      
}
