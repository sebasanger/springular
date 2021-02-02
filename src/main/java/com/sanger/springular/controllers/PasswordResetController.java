package com.sanger.springular.controllers;

import com.sanger.springular.dto.CreateResetPasswordTokenDto;
import com.sanger.springular.dto.ResetUserPasswordDto;
import com.sanger.springular.services.ResetPasswordTokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/change-password/{token}")
    public ResponseEntity<String> changePassword(@RequestBody ResetUserPasswordDto resetPasswordTokenDto,
            @PathVariable String token) {
        resetPasswordTokenService.validateVerificationToken(resetPasswordTokenDto, token);
        return ResponseEntity.noContent().build();

    }
}
