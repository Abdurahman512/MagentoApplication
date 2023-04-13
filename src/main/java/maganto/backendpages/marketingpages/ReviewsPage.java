package maganto.backendpages.marketingpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ReviewsPage {
    //1.Marketing Manager can view All Reviews.
    //2.Marketing Manager can update existing Reviews.
    //3.Marketing Manager can view Pending Reviews.
    //4.Marketing Manager can update Pending Reviews.
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;


    @FindBy(xpath = "//div[@class='wrapper']//span[text()='Catalog']")
    public WebElement catalogLink;

    @FindBy(xpath = "//span[text()='Reviews and Ratings']")
      public WebElement reviewsRatingsLink;

    @FindBy(xpath = "//span[text()='Customer Reviews']")
   public WebElement customerReviewsLink;

    @FindBy(xpath = "//span[text()='All Reviews']")
    public WebElement allReviewsLink;

    @FindAll(@FindBy(xpath = "//table[@id=\"reviwGrid_table\"]//tbody//tr/td[2]"))
    public List<WebElement>  allReviewsList;

    @FindBy(xpath = "//table[@id=\"reviwGrid_table\"]//tbody//tr/td[1]")
   public WebElement theFirstInAllViewsList;


    @FindBy(xpath = "//span[text()=\"Pending Reviews\"]")
    public WebElement pendingViewsLink;


    @FindAll(@FindBy(xpath = "table[@id='reviwGrid_table']//tbody/tr"))
    List<WebElement>  pendingReviewsList;


    public ReviewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions=new Actions(driver);
    }
    //1.Marketing manager can view all Reviews
    public void viewAllReviews() {
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewsRatingsLink);
        actions.moveToElement(reviewsRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(allReviewsLink);
        actions.moveToElement(allReviewsLink).click().perform();



    }
    public boolean verifyViewAllReviws(){
        if(allReviewsList.size()>=1)
            return true;
        else
            return false;

    }


    //2.Marketing manager can update existing reviews
    public void updateExistingReviews(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewsRatingsLink);
        actions.moveToElement(reviewsRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(allReviewsLink);
        actions.moveToElement(allReviewsLink).click().perform();

    }


    //3.Marketing manager can view pending reviews
    public void viewPendingReviews(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewsRatingsLink);
        actions.moveToElement(reviewsRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(pendingViewsLink);
        actions.moveToElement(pendingViewsLink).click().perform();

    }
    public boolean verifViewPendingViews(){
        if(pendingReviewsList.size()>=1)
            return true;
        else
            return false;
    }


    // 4.Marketing manager can update pending reviews
    public void updatePendingReviews(){
        testUtility.waitForElementPresent(theFirstInAllViewsList);
        theFirstInAllViewsList.click();

    }













































    // if(allReviews.size>=1)
//    return true;




}
