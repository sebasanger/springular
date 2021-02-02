package com.sanger.springular.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
public class CreateResetPasswordTokenDto {

    @NotBlank
    private String urlRedirect;

    @NotBlank
    @Email
    private String email;
}
