package com.sanger.springular.controllers;

import javax.validation.Valid;

import com.sanger.springular.dto.auth.ChangeUserPassword;
import com.sanger.springular.dto.user.CheckEmailIsValidDto;
import com.sanger.springular.dto.user.CreateUserDto;
import com.sanger.springular.dto.user.GetUserDetailsDto;
import com.sanger.springular.dto.user.GetUsersDto;
import com.sanger.springular.dto.user.UpdateAcountDto;
import com.sanger.springular.dto.user.UpdateUserDto;
import com.sanger.springular.dto.user.UserDtoConverter;
import com.sanger.springular.error.exceptions.UserNotFoundException;
import com.sanger.springular.model.UserEntity;
import com.sanger.springular.services.UserEntityService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserEntityService userEntityService;
	private final UserDtoConverter userDtoConverter;

	@GetMapping("")
	public ResponseEntity<?> listUsers(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(defaultValue = "") String filter) {
		Page<UserEntity> result = userEntityService.filterUser(filter, pageable);

		if (result.isEmpty()) {
			throw new UserNotFoundException();
		} else {
			Page<GetUsersDto> dtoList = result.map(userDtoConverter::convertUserEntityToGetUserDto);

			return ResponseEntity.ok().body(dtoList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetUserDetailsDto> findUserById(@PathVariable Long id) {
		UserEntity result = userEntityService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		GetUserDetailsDto user = userDtoConverter.convertUserEntityToGetUserDetailsDto(result);
		return ResponseEntity.ok().body(user);

	}

	@PostMapping("")
	public ResponseEntity<GetUsersDto> newUser(@Valid @RequestBody CreateUserDto newUser) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.newUser(newUser)));
	}

	@PutMapping("")
	public ResponseEntity<GetUsersDto> updateUser(@Valid @RequestBody UpdateUserDto user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.updateUser(user)));

	}

	@PutMapping("/update-acount")
	public ResponseEntity<GetUsersDto> updateAcount(@Valid @RequestBody UpdateAcountDto user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.updateAcount(user)));

	}

	@PutMapping("/changePassword")
	public ResponseEntity<GetUsersDto> updateUser(@Valid @RequestBody ChangeUserPassword user) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.updatePassword(user)));

	}

	/**
	 * Borra un usuario en base a su id
	 * 
	 * @param id
	 * @return Código 204 sin contenido
	 * @throws UserNotFoundException
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarUsuario(@PathVariable Long id) {

		UserEntity user = userEntityService.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		userEntityService.delete(user);
		return ResponseEntity.noContent().build();

	}

	/**
	 * cambiar la imagen del usuario
	 * 
	 * @param nuevo
	 * @return 201 y el producto insertado
	 */
	@PutMapping(value = "/upload/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void nuevoProducto(@RequestPart("file") MultipartFile file, @RequestPart("username") String username) {
		userEntityService.uploadAvatar(file, username);
	}

	@PostMapping("/checkEmailIsValid")
	public boolean checkEmailIsValid(@Valid @RequestBody CheckEmailIsValidDto checkEmailIsValidDto) {
		return userEntityService.checkEmailIsValid(checkEmailIsValidDto);
	}

}
