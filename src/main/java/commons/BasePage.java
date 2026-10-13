package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private By getByLocator(String locatorType){
        By by = null;
        if(locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")){
            by = By.id(locatorType.substring(3));
        }else if(locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")){
            by = By.className(locatorType.substring(6));
        }else if(locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")){
            by = By.name(locatorType.substring(5));
        }else if(locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")){
            by = By.cssSelector(locatorType.substring(4));
        }else if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")){
            by = By.xpath(locatorType.substring(6));
        }else {
            throw new RuntimeException("Locator type is not supported!");
        }
        return by;
    }

    private String getDynamicLocator(String locator, String... dynamicValue){
        locator = String.format(locator,(Object[])dynamicValue);
        return locator;
    }

    protected WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getListWebElements(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getListWebElements(WebDriver driver, String locator, String...dynamicValue){
        return driver.findElements(getByLocator(getDynamicLocator(locator, dynamicValue)));
    }

    protected void clickToElements(WebDriver driver, String locator){
        waitElementClickable(driver, locator);
        getWebElement(driver, locator).click();
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String value){
        getWebElement(driver, locator).sendKeys(value);
    }

    protected String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    protected void pressKey(WebDriver driver, Keys key){
        Actions actions = new Actions(driver);
        actions.keyDown(key).perform();
    }

    protected void hoverToElement(WebDriver driver, String locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver,locator)).perform();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator){
        boolean status = true;
        overrideImplicitWait(driver, GlobalConstants.TEMP_TIMEOUT);
        try{
            WebElement element = getWebElement(driver, locator);
            if(element.isDisplayed()){
                return status;
            }
        }catch (Exception e){
            status = false;
        }
        overrideImplicitWait(driver, GlobalConstants.TEMP_TIMEOUT);
        return status;
    }

    protected boolean isElementDisabled(WebDriver driver, String locator) {
        boolean status = false;

        overrideImplicitWait(driver, GlobalConstants.TEMP_TIMEOUT);

        try {
            WebElement element = getWebElement(driver, locator);
            status = !element.isEnabled();
        } catch (Exception e) {
            status = false;
        }

        overrideImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);

        return status;
    }

    private void overrideImplicitWait(WebDriver driver, long timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    protected void waitElementVisible(WebDriver driver, String locator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitElementVisible(WebDriver driver, String locator, String... dynamicValue){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, dynamicValue))));
    }

    protected void waitElementClickable(WebDriver driver, String locator){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    protected void verifyTrue(boolean condition){
        Assert.assertTrue(condition);
    }

    protected void verifyFalse(boolean condition){
        Assert.assertFalse(condition);
    }

    protected void verifyEqual(Object actual, Object expected){
        Assert.assertEquals(actual, expected);
    }

    protected void sleepInSecond(long timeout){
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
