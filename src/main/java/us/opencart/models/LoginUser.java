package us.opencart.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUser {
    private String email;
    private String password;

}
