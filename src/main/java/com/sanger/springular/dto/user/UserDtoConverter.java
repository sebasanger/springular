package com.sanger.springular.dto.user;

import java.util.stream.Collectors;

import com.sanger.springular.model.UserEntity;
import com.sanger.springular.model.UserRole;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {

	private final PasswordEncoder passwordEncoder;

	public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
		return GetUserDto.builder().username(user.getUsername()).avatar(user.getAvatar()).fullName(user.getFullName())
				.email(user.getEmail()).roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
				.build();
	}

	public GetUserDetailsDto convertUserEntityToGetUserDetailsDto(UserEntity user) {
		return GetUserDetailsDto.builder().username(user.getUsername()).avatar(user.getAvatar())
				.fullName(user.getFullName()).email(user.getEmail()).enabled(user.isEnabled())
				.createdAt(user.getCreatedAt()).lastPasswordChangeAt(user.getLastPasswordChangeAt())
				.roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet())).build();
	}

	public UserEntity convertCreateUserDtoToUserEntity(CreateUserDto newUser) {
		return UserEntity.builder().username(newUser.getUsername())
				.password(passwordEncoder.encode(newUser.getPassword())).avatar(newUser.getAvatar())
				.fullName(newUser.getFullname()).email(newUser.getEmail()).roles(newUser.getRoles()).enabled(false)
				.build();
	}

	public UserEntity convertUpdateUserDtoToUserEntity(UpdateUserDto user) {
		return UserEntity.builder().id(user.getId()).username(user.getUsername()).avatar(user.getAvatar())
				.fullName(user.getFullname()).email(user.getEmail()).roles(user.getRoles()).build();
	}

}
