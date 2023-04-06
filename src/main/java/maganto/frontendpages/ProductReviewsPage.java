package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductReviewsPage {

 WebDriver driver;
 TestUtility utility;

    public ProductReviewsPage(WebDriver driver, TestUtility utility, WebElement myProductReviews, WebElement noReviewText) {
        this.driver = driver;
        this.utility = utility;
        this.myProductReviews = myProductReviews;
        this.noReviewText = noReviewText;
    }

    //my product reviews
    @FindBy(xpath ="//strong[contains(text(),'My Product Reviews')]")
    WebElement myProductReviews;
    @FindBy(xpath="//p[contains(text(),'You have submitted no reviews.')]")
    WebElement noReviewText;













}
