package com.sanger.springular.dto.user;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sanger.springular.model.UserRole;

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
public class UpdateUserDto {
    @NotNull
    private Long id;
    private String avatar;
    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    private Set<UserRole> roles;

}
