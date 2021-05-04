package com.odod.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.odod.domain.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class UserRequestDto {

  @NotBlank(message = "아이디를 작성해주세요.")
  @Pattern(regexp = "^(?=.*[a-zA-Z0-9]).{6,12}$", message = "아이디는 영문/숫자 조합 6자리 ~ 12자리")
  private String userId;

  @NotBlank(message = "비밀번호를 작성해주세요.")
  @Pattern(regexp = "^(?=.*[a-zA-Z0-9`~!@#$%^&*()\\-_+=\\\\]).{8,15}$",
      message = "비밀번호는 영문/숫자/특수문자 조합 8자리~15자리")
  private String password;

  @NotBlank(message = "이메일을 작성해주세요.")
  @Email(message = "이메일의 양식을 지켜주세요.")
  private String email;

  @NotBlank(message = "이름을 작성해주세요.")
  @Size(max = 10, message = "이름은 10자 내외로 작성해주세요.")
  private String name;

  public User toEntity() {
    return User.builder().userId(this.getUserId())
        .password(this.getPassword()).name(this.getName())
        .email(this.getEmail()).useYn('N').build();
  }
}

