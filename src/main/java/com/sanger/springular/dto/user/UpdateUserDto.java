package com.sanger.springular.dto.user;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sanger.springular.model.UserRole;

import org.hibernate.validator.constraints.Length;

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
    @Length(min = 5, max = 50)
    private String fullName;
    @NotBlank
    @Email()
    private String email;
    @NotEmpty()
    private Set<UserRole> roles;

}
