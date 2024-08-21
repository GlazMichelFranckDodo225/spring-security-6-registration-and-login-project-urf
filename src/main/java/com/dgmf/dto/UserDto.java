package com.dgmf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Acts also as Spring MVC Model Object
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email Should Not Be Empty")
    @Email
    private String email;
    @NotEmpty(message = "Password Should Not Be Empty")
    private String password;
}
