package com.sanger.springular.dto.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GetUsersDto {
	private Long id;
	private String username;
	private String avatar;
	private String fullName;
	private String email;
	private Set<String> roles;

}