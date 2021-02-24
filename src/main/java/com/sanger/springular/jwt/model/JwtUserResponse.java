package com.sanger.springular.jwt.model;

import java.util.Set;

import com.sanger.springular.dto.user.GetUserDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDto {
    private String token;

    @Builder(builderMethodName = "jwtUserResponseBuilder")
    public JwtUserResponse(Long id, String username, String avatar, String fullName, String email, Set<String> roles,
            String token) {
        super(id, username, avatar, fullName, email, roles);
        this.token = token;
    }

}
