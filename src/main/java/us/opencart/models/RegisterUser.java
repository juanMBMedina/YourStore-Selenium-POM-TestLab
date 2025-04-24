package us.opencart.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUser {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private String passwordConfirm;
    private Boolean subscribe;
    private Boolean privacy;

    @Override
    public String toString() {
        return "RegisterUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", subscribe=" + subscribe +
                ", privacy=" + privacy +
                '}';
    }
}
