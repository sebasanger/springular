package com.sanger.springular.dto.user;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sanger.springular.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

	@NotBlank
	private String username;

	private String avatar;

	@NotBlank
	private String fullname;

	@NotBlank
	@Email(message = "Must be a valid email")
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String password2;

	private Set<UserRole> roles;

	private String urlRedirect;

}
