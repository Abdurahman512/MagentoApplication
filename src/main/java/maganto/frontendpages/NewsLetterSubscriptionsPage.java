package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsLetterSubscriptionsPage {

    WebDriver driver;

    public NewsLetterSubscriptionsPage(WebDriver driver, TestUtility utility, WebElement newsletterLink, WebElement generalSubscriptionText) {
        this.driver = driver;
        this.utility = utility;
        this.newsletterLink = newsletterLink;
        this.generalSubscriptionText = generalSubscriptionText;
    }

    TestUtility utility;

    //Newsletter Subscriptions
    @FindBy(xpath = "//amyProductReviews[text()='Newsletter Subscriptions']")
    WebElement newsletterLink;
    @FindBy(xpath="//label[contains(text(),'General Subscription']")
    WebElement generalSubscriptionText;


}
