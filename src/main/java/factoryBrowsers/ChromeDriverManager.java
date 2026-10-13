package factoryBrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements  BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions options = new ChromeOptions();

        // Bỏ qua lỗi chứng chỉ HTTPS trong môi trường test
        options.setAcceptInsecureCerts(true);
        return new ChromeDriver(options);
    }
}
