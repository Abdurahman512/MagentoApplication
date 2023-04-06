package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsLetterSubscriptionsPage {

    WebDriver driver;
    TestUtility utility;

    //Newsletter Subscriptions
    @FindBy(xpath = "//amyProductReviews[text()='Newsletter Subscriptions']")
    WebElement newsletterLink;
    @FindBy(xpath="//label[contains(text(),'General Subscription']")
    WebElement generalSubscriptionText;


}
