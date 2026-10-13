package commons;

import factoryBrowsers.BROWSERS;
import factoryBrowsers.ChromeDriverManager;
import factoryBrowsers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.LoginPageObject;
import pageobjects.MainPageObject;
import utils.ConfigLoader;

import java.time.Duration;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPageObject loginPage;
    protected MainPageObject mainPage;

    @Parameters({"browser"})
    @BeforeClass
    protected void beforeClass(@Optional("chrome") String browserName){
        driver = getBrowserDriver(browserName);
        loginPage = PageGeneratorManager.getLoginPageObject(driver);
        mainPage = PageGeneratorManager.getMainPageObject(driver);
    }

    @BeforeMethod
    protected void beforeMethod() {
        driver.get(ConfigLoader.getInstance().getPropertyByName("url"));
    }

    @AfterClass
    protected void afterClass(){
        driver.quit();
    }

    private WebDriver getBrowserDriver(String browserName){
        BROWSERS browser = BROWSERS.valueOf(browserName.toUpperCase());
        switch (browser){
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
        }
        driver.get(ConfigLoader.getInstance().getPropertyByName("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    @DataProvider(name = "loginCsvData")
    public Object[][] loginCsvData() throws IOException {
        String filePath =
                "src/main/resources/testdata/login-test-data.csv";

        List<Object[]> testData = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length != 3) {
                    throw new IllegalArgumentException(
                            "Invalid CSV row: " + line);
                }

                testData.add(new Object[]{
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim()
                });
            }
        }

        return testData.toArray(new Object[0][]);
    }

}
