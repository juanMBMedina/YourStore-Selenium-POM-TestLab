package models;

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
}
