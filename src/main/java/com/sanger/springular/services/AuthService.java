package com.sanger.springular.services;

import java.time.Instant;

import com.sanger.springular.dto.auth.AuthenticationResponse;
import com.sanger.springular.dto.auth.LoginRequestDto;
import com.sanger.springular.dto.auth.RefreshTokenRequestDto;
import com.sanger.springular.jwt.JwtProvider;
import com.sanger.springular.model.UserEntity;
import com.sanger.springular.repository.UserEntityRepository;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

        private final RefreshTokenService refreshTokenService;
        private final UserEntityRepository userRepository;
        private final AuthenticationManager authenticationManager;

        private final JwtProvider jwtProvider;

        @Transactional(readOnly = true)
        public UserEntity getCurrentUser() {
                org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                                .getContext().getAuthentication().getPrincipal();
                return userRepository.findByUsername(principal.getUsername()).orElseThrow(
                                () -> new UsernameNotFoundException("User not found - " + principal.getUsername()));
        }

        public AuthenticationResponse refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
                refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
                UserEntity user = userRepository.findByEmail(refreshTokenRequest.getEmail())
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                String token = jwtProvider.generateTokenWithEmail(user);
                return AuthenticationResponse.builder().authenticationToken(token)
                                .refreshToken(refreshTokenRequest.getRefreshToken())
                                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtDurationToken()))
                                .email(refreshTokenRequest.getEmail()).build();
        }

        public AuthenticationResponse login(LoginRequestDto loginRequest) {
                Authentication authenticate = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                                                loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                String token = jwtProvider.generateToken(authenticate);
                return AuthenticationResponse.builder().authenticationToken(token)
                                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtDurationToken()))
                                .email(loginRequest.getEmail()).build();
        }

        public boolean isLoggedIn() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
        }
}