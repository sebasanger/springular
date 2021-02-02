package com.sanger.springular.dto;

import java.util.Set;

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
    private Long id;
    private String username;
    private String avatar;
    private String fullname;
    private String email;
    private Set<UserRole> roles;

}
