package locators;

public class LoginLocator {
    public static String INPUT_USER_NAME = "xpath=//input[@name = 'username']";
    public static String INPUT_PASS = "xpath=//div[@title = 'Password']//input";
    public static String ICON_FIELD_PASS = "xpath=//i[contains(@class, 'icon-field-password')]";
    public static String BTN_LOGIN = "xpath=//div[@title = 'Log In']//button";
    public static String LBL_INCORRECT_MSG = "xpath=//div[@id = 'notificationMessage-1033']//div[contains(@class, 'notification-message-content')]";
    public static String LBL_USER_NAME_REQUIRED_FEILD_MSG = "xpath=//div[@id = 'msg-error-username']//li";
    public static String LBL_PASSWORD_REQUIRED_FEILD_MSG = "xpath=//div[@id = 'msg-error-password']//li";
    public static String LINK_FORGOT_YOUR_PASS = "xpath=//a[contains(@class, 'forgot-password')]";
    public static String INPUT_USERNAME_EMAIL_FORGOT_PASSWORD = "xpath=//input[@name = 'email']";
}
