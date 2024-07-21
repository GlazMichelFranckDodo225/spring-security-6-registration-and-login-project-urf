package com.dgmf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Acts also as Spring MVC Model Object
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
