package testcases.loginpage;

import commons.BaseTest;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void TC_01_Verify_Login_Page_Load_Success(){

        Allure.step("Verify User input is visible");
        loginPage.verifyUserNameInputIsVisible();

        Allure.step("Verify Password input is visible");
        loginPage.verifyPasswordInputIsVisible();

        Allure.step("Verify Login button is visible");
        loginPage.verifyLoginInputIsVisible();

        Allure.step("Verify Forgot your password link is displayed");
        loginPage.verifyForgotYourPasswordIsVisible();

        Allure.step("Click to Forgot Your Password link");
        loginPage.clickToForgotYourPasswordLink();

        Allure.step("Verify Login button is disable");
        loginPage.verifyLoginButtonIsDisable();
    }

    @Test
    public void TC_02_Verify_Login_Success(){
        String username = "hoangnguyen";
        String password = "Abcdef12345!";

        Allure.step("Login With Valid Credential");
        loginPage.loginWithAccount(username, password);

        Allure.step("Verify navigate to Main Page");
        mainPage.verifyLogoutButtonIsVisible();

        Allure.step("Logout Main Page");
        mainPage.logoutAccount();

        loginPage.verifyUserNameInputIsVisible();
    }

    @Test
    public void TC_03_Verify_Login_With_Empty_Username(){
        String username = "";
        String password = "123123";

        Allure.step("Login With Valid Credential");
        loginPage.inputLoginInfo(username, password);

        Allure.step("Hover to Username input");
        loginPage.hoverToUserNameInput();

        Allure.step("Verify required field message");
        loginPage.verifyUserNameRequiredFeildMessage("This field is required");
    }

//    @Test
//    public void TC_04_Verify_Login_With_Wrong_Password(){
//        String username = "hoangnguyen";
//        String invalidPassword = "123123";
//
//        Allure.step("Login With Valid Credential");
//        loginPage.loginWithValidCredentials(username, invalidPassword);
//
//        Allure.step("Verify error message");
//        loginPage.verifyIncorrectCredentialsMsg("Incorrect credentials.");
//    }
//
//    @Test
//    public void TC_05_Verify_Login_With_Empty_Password(){
//        String username = "hoangnguyen";
//        String password = "";
//
//        Allure.step("Login With Valid Credential");
//        loginPage.loginWithValidCredentials(username, password);
//
//        Allure.step("Verify error message");
//        loginPage.verifyIncorrectCredentialsMsg("Incorrect credentials.");
//
//        Allure.step("Hover to Username input");
//        loginPage.hoverToUserNameInput();
//
//        Allure.step("Verify required field message");
//        loginPage.verifyUserNameRequiredFeildMessage("Incorrect credentials.");
//
//        Allure.step("Hover to Password input");
//        loginPage.hoverToPasswordInput();
//
//        Allure.step("Verify required field message");
//        loginPage.verifyPasswordRequiredFeildMessage("Incorrect credentials.");
//    }
//
//
//    @Test
//    public void TC_06_Verify_Login_With_Invalid_Email_Format(){
//        String username = "hoangnguyen@.com";
//        String password = "Abcdef123!";
//
//        Allure.step("Login With Valid Credential");
//        loginPage.loginWithValidCredentials(username, password);
//
//        Allure.step("Verify error message");
//        loginPage.verifyIncorrectCredentialsMsg("Incorrect credentials.");
//
//        Allure.step("Hover to Username input");
//        loginPage.hoverToUserNameInput();
//
//        Allure.step("Verify required field message");
//        loginPage.verifyUserNameRequiredFeildMessage("Incorrect credentials.");
//
//        Allure.step("Hover to Password input");
//        loginPage.hoverToIconPassworInput();
//
//        Allure.step("Verify required field message");
//        loginPage.verifyPasswordRequiredFeildMessage("Incorrect credentials.");
//    }

    @Test(dataProvider = "loginCsvData")
    public void TC_04_06_Verify_Login_With_Csv_Data(
            String scenario,
            String username,
            String password) {

        Allure.step("Test scenario: " + scenario);

        switch (scenario) {
            case "Wrong Password":
                Allure.step("Input login info");
                loginPage.loginWithAccount(username, password);

                Allure.step("Verify incorrect credentials message");
                loginPage.verifyIncorrectCredentialsMsg(
                        "Incorrect credentials.");
                break;

            case "Empty Password":
                loginPage.loginWithAccount(username, password);

                Allure.step("Hover to Password input");
                loginPage.hoverToPasswordInput();

                Allure.step("Verify password required message");
                loginPage.verifyPasswordRequiredFeildMessage(
                        "This field is required");
                break;

            case "Invalid Email Format":
                loginPage.loginWithAccount(username, password);

                Allure.step("Verify incorrect credentials message");
                loginPage.verifyIncorrectCredentialsMsg(
                        "Incorrect credentials.");
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown scenario: " + scenario);
        }
    }

    @Test
    public void TC_07_Verify_Login_With_Excessively_Long_Email (){
        String username = "a".repeat(245) + "@example.com";
        String password = "Abcdef123!";

        Allure.step("Login With Valid Credential");
        loginPage.loginWithAccount(username, password);

        Allure.step("Verify error message");
        loginPage.verifyIncorrectCredentialsMsg("Incorrect credentials.");

        Allure.step("Hover to Username input");
        loginPage.hoverToUserNameInput();

        Allure.step("Verify required field message");
        loginPage.verifyUserNameRequiredFeildMessage("Incorrect credentials.");

        Allure.step("Hover to Password input");
        loginPage.hoverToIconPassworInput();

        Allure.step("Verify required field message");
        loginPage.verifyPasswordRequiredFeildMessage("Incorrect credentials.");
    }

    @Test
    public void TC_08_Verify_Login_With_Excessively_Long_Password() {
        String username = "hoangnguyen";
        String password = "a".repeat(101);

        Allure.step("Login With Valid Credential");
        loginPage.loginWithAccount(username, password);

        Allure.step("Verify error message");
        loginPage.verifyIncorrectCredentialsMsg("Incorrect credentials.");

        Allure.step("Hover to Username input");
        loginPage.hoverToUserNameInput();

        Allure.step("Verify required field message");
        loginPage.verifyUserNameRequiredFeildMessage("Incorrect credentials.");

        Allure.step("Hover to Password input");
        loginPage.hoverToIconPassworInput();

        Allure.step("Verify required field message");
        loginPage.verifyPasswordRequiredFeildMessage("Incorrect credentials.");
    }

}


