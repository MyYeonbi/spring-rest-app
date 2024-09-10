package org.project.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Password is mandatory")
    private String password;  // 비밀번호 필드 추가

    // password 필드는 DTO에서 제외하여 안전하게 데이터를 전송하기위함
    // 근데 쉬운방안으로 그냥 password 넣고 이걸 암호화/복호화 하는 방안 채택
}
