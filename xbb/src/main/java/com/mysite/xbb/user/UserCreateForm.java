package com.mysite.xbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	@Size(min = 3, max = 25, message = "ID는 3자리 이상 25자리 이하여야 합니다.")
	@NotEmpty(message = "사용자ID는 필수항목입니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String password2;
	
	@NotEmpty(message = "이메일은 필수항목입니다.")
	@Email(message = "메일주소 규칙에 일치하지 않습니다.")
	private String email;
}
