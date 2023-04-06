package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsLetterSubscriptionsPage {

    WebDriver driver;


    TestUtility utility;

    //Newsletter Subscriptions
    @FindBy(xpath = "//amyProductReviews[text()='Newsletter Subscriptions']")
    WebElement newsletterLink;

    @FindBy(xpath="//label[contains(text(),'General Subscription']")
    WebElement generalSubscriptionText;

    public NewsLetterSubscriptionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility= new TestUtility(driver);
    }

public void NewsLetterSubscriptionsPage(WebDriver driver){
        utility.waitForElementPresent(newsletterLink);
        newsletterLink.click();

}
    public boolean verifyViewNewsletterContent(){
        utility.waitForElementPresent(generalSubscriptionText);
   if(generalSubscriptionText.isDisplayed())
        return true;
   else return false;
    }

}
