package com.sanger.springular.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sanger.springular.dto.auth.ChangeUserPassword;
import com.sanger.springular.dto.user.CreateUserDto;
import com.sanger.springular.dto.user.GetUserDetailsDto;
import com.sanger.springular.dto.user.GetUserDto;
import com.sanger.springular.dto.user.UpdateUserDto;
import com.sanger.springular.dto.user.UserDtoConverter;
import com.sanger.springular.error.exceptions.UserNotFoundException;
import com.sanger.springular.model.UserEntity;
import com.sanger.springular.services.UserEntityService;
import com.sanger.springular.utils.pagination.PaginationLinksUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserEntityService userEntityService;
	private final UserDtoConverter userDtoConverter;
	private final PaginationLinksUtils paginationLinksUtils;

	@GetMapping("/")
	public ResponseEntity<?> listUsers(Pageable pageable, HttpServletRequest request) {
		Page<UserEntity> result = userEntityService.findAll(pageable);

		if (result.isEmpty()) {
			throw new UserNotFoundException();
		} else {

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

			return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(result, uriBuilder))
					.body(result);

		}
	}

	@GetMapping("")
	public ResponseEntity<?> filterUsers(@RequestParam String filter, Pageable pageable, HttpServletRequest request) {
		Page<UserEntity> result = userEntityService.filterUser(filter, pageable);
		if (result.isEmpty()) {
			throw new UserNotFoundException();
		} else {
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

			return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(result, uriBuilder))
					.body(result);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetUserDetailsDto> findUserById(@PathVariable Long id) {
		UserEntity result = userEntityService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		GetUserDetailsDto user = userDtoConverter.convertUserEntityToGetUserDetailsDto(result);
		return ResponseEntity.ok().body(user);

	}

	@PostMapping("/")
	public ResponseEntity<GetUserDto> newUser(@Valid @RequestBody CreateUserDto newUser) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.newUser(newUser)));
	}

	@PutMapping("/")
	public ResponseEntity<GetUserDto> updateUser(@Valid @RequestBody UpdateUserDto user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.updateUser(user)));

	}

	@PutMapping("/changePassword")
	public ResponseEntity<GetUserDto> updateUser(@Valid @RequestBody ChangeUserPassword user) {
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

}
