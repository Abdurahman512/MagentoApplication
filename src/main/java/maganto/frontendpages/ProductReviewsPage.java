package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductReviewsPage {

 //account link
 @FindBy(xpath = "//span[@class='label' and text()='Account']")
 WebElement accountLink;


   //my product reviews
    @FindBy(xpath ="//strong[contains(text(),'My Product Reviews')]")
    WebElement myProductReviews;

    //Newsletter Subscriptions
    @FindBy(xpath = "//amyProductReviews[text()='Newsletter Subscriptions']")
    WebElement newsletterLink;


    WebDriver driver;
    TestUtility utility;

 public void myProductReviews() {
  utility.waitForElementPresent(accountLink);
  accountLink.click();
utility.waitForElementPresent(myProductReviews);
  myProductReviews.click();
 }

 public void clickOnNewsletterLink() {
  utility.waitForElementPresent(newsletterLink);
  newsletterLink.click();
 }





}
