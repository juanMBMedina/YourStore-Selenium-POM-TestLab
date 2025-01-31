package pages;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static utils.WebDriverManager.getDriver;

@AllArgsConstructor
public class LoginPage{
    private WebDriver driver;

    private static final By USER_NAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By SUBMIT = By.xpath("//button[@type='submit']");
    private static final By SUCCESS_ALERT = By.id("flash");
    private static final By SECURE_MESSAGE = By.cssSelector("h4.subheader");


    public void loginWithCredentials(String userName,String password){
        driver.findElement(USER_NAME).sendKeys(userName);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(SUBMIT).click();
    }

    public String getAlertMessage(){
        return driver.findElement(SUCCESS_ALERT).getText();
    }
    public String getSecureMessage(){
        return driver.findElement(SECURE_MESSAGE).getText();
    }



}
