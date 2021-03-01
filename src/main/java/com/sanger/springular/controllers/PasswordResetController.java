package com.sanger.springular.controllers;

import javax.validation.Valid;

import com.sanger.springular.dto.auth.CreateResetPasswordTokenDto;
import com.sanger.springular.dto.auth.ResetUserPasswordDto;
import com.sanger.springular.services.ResetPasswordTokenService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reset-password")
@RestController
@RequiredArgsConstructor
public class PasswordResetController {
    private final ResetPasswordTokenService resetPasswordTokenService;

    @PostMapping("")
    public ResponseEntity<Void> createResetTokenPassword(
            @RequestBody CreateResetPasswordTokenDto resetPasswordTokenDto) {
        resetPasswordTokenService.sendEmailResetToken(resetPasswordTokenDto.getEmail(),
                resetPasswordTokenDto.getUrlRedirect());
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@Valid @RequestBody ResetUserPasswordDto resetPasswordTokenDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(resetPasswordTokenService.validateVerificationToken(resetPasswordTokenDto));

    }
}
