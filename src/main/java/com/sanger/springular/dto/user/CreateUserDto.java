package com.sanger.springular.dto.user;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sanger.springular.model.UserRole;

import org.hibernate.validator.constraints.Length;

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

	private String avatar;

	@NotBlank
	@Length(min = 5, max = 50, message = "minimo de caracteres 5 y maximo 50")
	private String fullName;

	@NotBlank
	@Email(message = "Tiene que ser un email valido")
	private String email;

	private Set<UserRole> roles;

	@NotBlank
	private String urlRedirect;

}
