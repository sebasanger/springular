package com.sanger.springular.services;

import java.util.Optional;

import com.sanger.springular.controllers.FilesController;
import com.sanger.springular.dto.auth.ChangeUserPassword;
import com.sanger.springular.dto.user.CreateUserDto;
import com.sanger.springular.dto.user.UpdateAcountDto;
import com.sanger.springular.dto.user.UpdateUserDto;
import com.sanger.springular.dto.user.UserDtoConverter;
import com.sanger.springular.error.exceptions.PasswordNotMismatch;
import com.sanger.springular.error.exceptions.UserNotFoundException;
import com.sanger.springular.model.UserEntity;
import com.sanger.springular.repository.UserEntityRepository;
import com.sanger.springular.services.base.BaseService;
import com.sanger.springular.utils.upload.StorageService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> {

	private final UserDtoConverter userDtoConverter;

	private final PasswordEncoder passwordEncoder;

	private final VerificationTokenService verificationTokenService;

	private final StorageService storageService;

	/**
	 * Nos permite buscar un usuario por su nombre de usuario
	 * 
	 * @param username
	 * @return
	 */
	public Optional<UserEntity> findUserByUsername(String username) {
		return this.repository.findByUsername(username);
	}

	public Page<UserEntity> filterUser(String filter, Pageable pageable) {
		return this.repository.findByUsernameIgnoreCaseContainingOrFullNameIgnoreCaseContaining(filter, filter,
				pageable);
	}

	/**
	 * Nos permite crear un nuevo UserEntity con rol USER
	 * 
	 * @param newUser
	 * @return
	 */
	public UserEntity newUser(CreateUserDto newUser) {

		UserEntity userEntity = userDtoConverter.convertCreateUserDtoToUserEntity(newUser);
		UserEntity userSaved = save(userEntity);
		verificationTokenService.sendEmailVerification(userSaved, newUser.getUrlRedirect());

		return userSaved;

	}

	public UserEntity updateUser(UpdateUserDto updateUserDto) {

		try {
			UserEntity userEntity = findById(updateUserDto.getId())
					.orElseThrow(() -> new UserNotFoundException(updateUserDto.getId()));

			userEntity.setFullName(updateUserDto.getFullName());
			userEntity.setAvatar(updateUserDto.getEmail());
			userEntity.setEmail(updateUserDto.getEmail());
			userEntity.setRoles(updateUserDto.getRoles());
			return update(userEntity);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
		}

	}

	public UserEntity updateAcount(UpdateAcountDto updateAcountDto) {

		try {
			UserEntity userEntity = findById(updateAcountDto.getId()).orElseThrow(() -> new UserNotFoundException());

			userEntity.setEmail(updateAcountDto.getEmail());
			userEntity.setUsername(updateAcountDto.getEmail());
			userEntity.setAvatar(updateAcountDto.getAvatar());
			return update(userEntity);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	public UserEntity updatePassword(ChangeUserPassword user) throws UserNotFoundException {
		UserEntity userEntity = findById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getId()));

		if (!passwordEncoder.matches(user.getOldPassword(), userEntity.getPassword())) {
			throw new PasswordNotMismatch(true);
		} else if (!user.getPassword().equals(user.getPassword2())) {
			throw new PasswordNotMismatch();
		} else {
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			return update(userEntity);
		}

	}

	public boolean changeUserStatus(Long id) throws UserNotFoundException {
		UserEntity userEntity = findById(id).orElseThrow(() -> new UserNotFoundException(id));

		userEntity.setEnabled(!userEntity.isEnabled());
		this.repository.save(userEntity);

		return userEntity.isEnabled();
	}

	public UserEntity uploadAvatar(MultipartFile file, String username) {
		String urlImage = null;

		if (!file.isEmpty()) {
			String image = storageService.store(file);
			urlImage = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", image, null).build()
					.toUriString();
		}

		UserEntity user = this.repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());

		user.setAvatar(urlImage);

		this.save(user);

		return user;

	}

}
