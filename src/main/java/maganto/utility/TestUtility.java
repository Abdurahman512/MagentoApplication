package maganto.utility;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestUtility {
    private int timeout = Integer.parseInt(ApplicationConfig.readFromConfigProperties(
            "config.properties", "timeout"
    ));

    WebDriver driver;

    Faker faker = new Faker();

    public TestUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void javaScriptClick(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", element);
    }

    public int generateNumber() {
        int number = faker.code().hashCode();
        return number;
    }

    public String generateAdminName() {
        String adminName = faker.name().name();
        return adminName;
    }


    public String generateFirstName() {
        String firstName = faker.name().firstName();
        return firstName;
    }

    public String generateLastName() {
        String lastName = faker.name().lastName();
        return lastName;
    }

    public String generateEmailAddress() {
        String emailAddress = faker.internet().emailAddress();
        return emailAddress;
    }

    public String generateStreetAddress() {
        String streetAddress = faker.address().streetAddress();
        return streetAddress;
    }

    public String generateCityName() {
        String cityName = faker.address().cityName();
        return cityName;
    }

    public String generateCountryName() {
        String countryName = faker.address().country();
        return countryName;
    }

    public String generateStateName() {
        String stateName = faker.address().state();
        return stateName;
    }

    public String generateZipCode() {
        String zinCode = faker.address().zipCode();
        return zinCode;
    }

    public String generateTelephoneNumber() {
        String telephoneNumber = faker.phoneNumber().phoneNumber();
        return telephoneNumber;
    }

    public void waitForAlertPresent() {
        WebDriverWait wai = new WebDriverWait(driver, timeout);
        wai.until(ExpectedConditions.alertIsPresent());
    }

    public void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unhandledAlertException(WebElement element) {

        }


    }


