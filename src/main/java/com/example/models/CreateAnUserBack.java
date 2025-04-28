package com.example.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateAnUserBack {
    private String name;
    private String username;
    private String email;

    @Override
    public String toString() {
        return "CreateAnUserBack{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
