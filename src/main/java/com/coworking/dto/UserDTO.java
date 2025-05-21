package com.coworking.dto;

import com.coworking.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private User.UserRole role;
}
