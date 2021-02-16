package com.sanger.springular.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sanger.springular.dto.GetUserDto;
import com.sanger.springular.dto.UserDtoConverter;
import com.sanger.springular.dto.ValidateUserDto;
import com.sanger.springular.dto.ValidateUserTokenDto;
import com.sanger.springular.jwt.JwtProvider;
import com.sanger.springular.jwt.model.JwtUserResponse;
import com.sanger.springular.jwt.model.LoginRequest;
import com.sanger.springular.model.UserEntity;
import com.sanger.springular.model.UserRole;
import com.sanger.springular.services.VerificationTokenService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserDtoConverter converter;
    private final VerificationTokenService verificationTokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        UserEntity user = (UserEntity) auth.getPrincipal();

        String jwtToken = jwtProvider.generateToken(auth);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(covertUserEntityAndTokenToJwtUserResponse(user, jwtToken));
    }

    private JwtUserResponse covertUserEntityAndTokenToJwtUserResponse(UserEntity user, String jwtToken) {
        return JwtUserResponse.jwtUserResponseBuilder().fullName(user.getFullName()).email(user.getEmail())
                .username(user.getUsername()).avatar(user.getAvatar())
                .roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet())).token(jwtToken)
                .build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public GetUserDto me(@AuthenticationPrincipal UserEntity user) {
        return converter.convertUserEntityToGetUserDto(user);
    }

    @GetMapping("/validate-token")
    public boolean validateUserToken(@Valid ValidateUserTokenDto userToken) {
        return jwtProvider.validateToken(userToken.getToken());
    }

    @PutMapping("/validate-acount")
    public ResponseEntity<String> updateUser(@Valid @RequestBody ValidateUserDto validation) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(verificationTokenService.validateVerificationToken(validation));

    }

}
