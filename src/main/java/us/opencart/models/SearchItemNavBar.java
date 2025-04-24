package us.opencart.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchItemNavBar {
    private String category;
    private String subcategory;
    private String itemName;

}
