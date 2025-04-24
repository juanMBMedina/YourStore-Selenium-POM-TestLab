package us.opencart.constants;

import java.util.Map;

public class RegisterPageConstants {

    private RegisterPageConstants() {
    }

    public static final String SUCCESS_REGISTER = "Congratulations! Your new account has been successfully created!";
    public static final String USER_EXIST = "Warning: E-Mail Address is already registered!";
    public static final String WITHOUT_PRIVACY = "Warning: You must agree to the Privacy Policy!";
    public static final Map<String, String> WITHOUT_PARAMS = Map.ofEntries(
            Map.entry("firstName", "First Name must be between 1 and 32 characters!"),
            Map.entry("lastName", "Last Name must be between 1 and 32 characters!"),
            Map.entry("email", "E-Mail Address does not appear to be valid!"),
            Map.entry("telephone", "Telephone must be between 3 and 32 characters!"),
            Map.entry("password", "Password must be between 4 and 20 characters!"),
            Map.entry("passwordConfirm", "Password confirmation does not match password!")
    );

}
