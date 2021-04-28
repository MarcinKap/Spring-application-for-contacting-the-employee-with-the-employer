package com.example.Project_Spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRest {

    @Nullable
    private String email;
    @Nullable
    private String name;
    @Nullable
    private String lastName;
    @Nullable
    private String role;

    private String token;

    private Long id;

}
