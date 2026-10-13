package pageobjects;

import commons.BasePage;
import locators.LoginLocator;
import locators.MainPageLocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    protected WebDriver driver;
    private static LoginPageObject instance;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isUserNameInputVisible() {
        waitElementVisible(driver, LoginLocator.INPUT_USER_NAME);
        return isElementDisplayed(driver, LoginLocator.INPUT_USER_NAME);
    }

    public boolean isPasswordInputVisible() {
        waitElementVisible(driver, LoginLocator.INPUT_PASS);
        return isElementDisplayed(driver, LoginLocator.INPUT_PASS);
    }

    public boolean isLoginButtonVisible() {
        waitElementVisible(driver, LoginLocator.BTN_LOGIN);
        return isElementDisplayed(driver, LoginLocator.BTN_LOGIN);
    }

    public void verifyUserNameInputIsVisible(){
        verifyTrue(isUserNameInputVisible());
    }

    public void loginWithAccount(String username, String password){
        waitElementVisible(driver, LoginLocator.INPUT_USER_NAME);
        sendKeyToElement(driver, LoginLocator.INPUT_USER_NAME, username);

        waitElementVisible(driver, LoginLocator.INPUT_PASS);
        sendKeyToElement(driver, LoginLocator.INPUT_PASS, password);

        waitElementClickable(driver, LoginLocator.BTN_LOGIN);
        clickToElements(driver, LoginLocator.BTN_LOGIN);
    }

    public void inputLoginInfo(String username, String password){
        waitElementVisible(driver, LoginLocator.INPUT_USER_NAME);
        sendKeyToElement(driver, LoginLocator.INPUT_USER_NAME, username);

        waitElementVisible(driver, LoginLocator.INPUT_PASS);
        sendKeyToElement(driver, LoginLocator.INPUT_PASS, password);
    }

    public boolean isLogoutButtonVisible() {
        waitElementVisible(driver, MainPageLocator.BTN_LOGOUT_MENU);
        return isElementDisplayed(driver, MainPageLocator.BTN_LOGOUT_MENU);
    }

    public void verifyIncorrectCredentialsMsg(String expectedErrorMsg){
        waitElementVisible(driver, LoginLocator.LBL_INCORRECT_MSG);
        String errorMsgActual = getElementText(driver, LoginLocator.LBL_INCORRECT_MSG);
        verifyEqual(errorMsgActual, expectedErrorMsg);
    }

    public void verifyUserNameRequiredFeildMessage(String expectedMessage){
        waitElementVisible(driver, LoginLocator.LBL_USER_NAME_REQUIRED_FEILD_MSG);
        String actualMessage = getElementText(driver, LoginLocator.LBL_USER_NAME_REQUIRED_FEILD_MSG);
        verifyEqual(actualMessage, expectedMessage);
    }

    public void verifyPasswordRequiredFeildMessage(String expectedMessage){
        waitElementVisible(driver, LoginLocator.LBL_PASSWORD_REQUIRED_FEILD_MSG);
        String actualMessage = getElementText(driver, LoginLocator.LBL_PASSWORD_REQUIRED_FEILD_MSG);
        verifyEqual(actualMessage, expectedMessage);
    }


    public void verifyPasswordInputIsVisible() {
        verifyTrue(isPasswordInputVisible());
    }

    public void verifyLoginInputIsVisible() {
        verifyTrue(isLoginButtonVisible());
    }

    public void hoverToUserNameInput() {
        waitElementVisible(driver, LoginLocator.INPUT_USER_NAME);
        hoverToElement(driver, LoginLocator.INPUT_USER_NAME);
    }

    public void hoverToPasswordInput() {
        waitElementVisible(driver, LoginLocator.INPUT_PASS);
        hoverToElement(driver, LoginLocator.INPUT_PASS);
    }

    public void hoverToIconPassworInput() {
        waitElementVisible(driver, LoginLocator.ICON_FIELD_PASS);
        hoverToElement(driver, LoginLocator.ICON_FIELD_PASS);
    }

    public void pressTabKey() {
        pressKey(driver, Keys.TAB);
    }

    public boolean isForgotYourPasswordLinnkVisible() {
        waitElementVisible(driver, LoginLocator.LINK_FORGOT_YOUR_PASS);
        return isElementDisplayed(driver, LoginLocator.LINK_FORGOT_YOUR_PASS);
    }

    public void verifyForgotYourPasswordIsVisible() {
        verifyTrue(isForgotYourPasswordLinnkVisible());
    }

    public void clickToForgotYourPasswordLink() {
        waitElementVisible(driver, LoginLocator.LINK_FORGOT_YOUR_PASS);
        clickToElements(driver, LoginLocator.LINK_FORGOT_YOUR_PASS);
    }

    public boolean isLoginButtonDisable() {
        return isElementDisabled(driver, LoginLocator.INPUT_USER_NAME);
    }

    public void verifyLoginButtonIsDisable() {
        verifyTrue(isLoginButtonDisable());
    }
}
