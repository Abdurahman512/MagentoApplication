package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductReviewsPage {

    WebDriver driver;
    TestUtility utility;

    public ProductReviewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utility = new TestUtility(driver);

    }


    //my product reviews
    @FindBy(xpath = "//strong[contains(text(),'My Product Reviews')]")
    WebElement myProductReviews;
    @FindBy(xpath = "//p[contains(text(),'You have submitted no reviews.')]")
    WebElement noReviewText;

    public void ProductReviews(WebDriver driver) {
        utility.waitForElementPresent(myProductReviews);
        myProductReviews.click();
    }


    public boolean verifyProductReviews() {

        utility.waitForElementPresent(noReviewText);
        if(noReviewText.isDisplayed())
            return true;
        else return false;


}








}
