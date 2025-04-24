package us.opencart.builders;

import com.github.javafaker.Faker;
import us.opencart.models.RegisterUser;

import java.util.Map;

public class RegisterUserBuilder {

    private static final String EMAIL_DOMAIN = "@opencart.com";

    private RegisterUserBuilder() {
    }

    public static RegisterUser registerAnUser(boolean privacyStatus, boolean subscribeStatus) {
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = (firstName + "." + lastName + EMAIL_DOMAIN).toLowerCase();
        String telephone = faker.number().digits(10);
        String password = faker.internet().password(8, 16);

        return buildUser(firstName, lastName, email, telephone, password, password, privacyStatus, subscribeStatus);
    }

    public static RegisterUser registerAnUser(Map<String, String> data) {
        return buildUser(
                data.get("firstName"),
                data.get("lastName"),
                data.get("email"),
                data.get("telephone"),
                data.get("password"),
                data.get("passwordConfirm"),
                Boolean.parseBoolean(data.get("privacy")),
                Boolean.parseBoolean(data.get("subscribe"))
        );
    }

    private static RegisterUser buildUser(
            String firstName,
            String lastName,
            String email,
            String telephone,
            String password,
            String passwordConfirm,
            boolean privacy,
            boolean subscribeStatus
    ) {
        return RegisterUser.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .telephone(telephone)
                .password(password)
                .passwordConfirm(passwordConfirm)
                .privacy(privacy)
                .subscribe(subscribeStatus)
                .build();
    }
}
