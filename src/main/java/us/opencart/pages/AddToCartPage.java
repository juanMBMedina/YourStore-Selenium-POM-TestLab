package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartPage extends WishListPage {


    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getItemActionsInTable(String itemName) {
        return getByOf(itemName, 4);
    }

}
