package builders;

import com.github.javafaker.Faker;
import models.CreateAnUserBack;

public class CreateAnUser {
    private static final String EMAIL_ADDRESS = "@pruebas.com";
    public static CreateAnUserBack createAnUser(){
        Faker faker= new Faker();
        return CreateAnUserBack.builder()
                .name(String.valueOf(faker.name()))
                .username(String.valueOf(faker.funnyName()))
                .email(faker.leagueOfLegends().masteries().concat(EMAIL_ADDRESS))
                .build();
    }

}
