package maganto.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestBase {
    public WebDriver driver;

    public void browserSetUp(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        JenkinsBrowserSetup jenkinsBrowserSetup = new JenkinsBrowserSetup();
        boolean useHeadless = jenkinsBrowserSetup.setHeadlessModeIfLinux(chromeOptions);
        if (!useHeadless) {
            WebDriverManager.chromedriver().setup();
        }
        driver = new ChromeDriver(chromeOptions);
        if (!useHeadless) {
            driver.manage().window().maximize();
        }
        driver.get(url);
    }

    public void closeBrowser() {
        driver.quit();
    }
}
