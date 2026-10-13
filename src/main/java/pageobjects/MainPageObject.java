package pageobjects;

import commons.BasePage;
import locators.MainPageLocator;
import org.openqa.selenium.WebDriver;

public class MainPageObject extends BasePage {
    protected WebDriver driver;
    private static MainPageObject instance;

    public MainPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isLogoutButtonVisible() {
        waitElementVisible(driver, MainPageLocator.BTN_LOGOUT_MENU);
        return isElementDisplayed(driver, MainPageLocator.BTN_LOGOUT_MENU);
    }

    public void verifyLogoutButtonIsVisible() {
        verifyTrue(isLogoutButtonVisible());
    }

    public void logoutAccount(){
        clickToElements(driver, MainPageLocator.BTN_LOGOUT_MENU);
        waitElementVisible(driver, MainPageLocator.BTN_LOGOUT);
        clickToElements(driver, MainPageLocator.BTN_LOGOUT);
    }


}
