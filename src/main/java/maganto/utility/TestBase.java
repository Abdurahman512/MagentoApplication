package maganto.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.*;

public class TestBase {
    public WebDriver driver;

    public void browserSetUp(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        JenkinsBrowserSetup jenkinsBrowserSetup = new JenkinsBrowserSetup();
        boolean useHeadless = jenkinsBrowserSetup.setHeadlessModeIfLinux(chromeOptions);
        if (!useHeadless) {
            WebDriverManager.chromedriver().setup();
        }
        driver = new ChromeDriver(chromeOptions);//define a webdriver
        if (!useHeadless) {
            driver.manage().window().maximize();//maximize browser window
        }
        driver.get(url); //open the testing site
    }
    public void closeBrowser() {
        driver.quit();
    }
}
