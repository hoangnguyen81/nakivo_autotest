package commons;

import locators.LoginLocator;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPageObject;
import pageobjects.MainPageObject;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPageObject(WebDriver driver){

        return new LoginPageObject(driver);
    }

    public static MainPageObject getMainPageObject(WebDriver driver) {
        return new MainPageObject(driver);
    }
}
