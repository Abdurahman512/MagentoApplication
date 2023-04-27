package maganto.backendpages.marketingpages;

import maganto.backendpages.BackEndLogin;
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
    //4.Marketing Manager can update existing Pending Reviews.
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    BackEndLogin backEndLogin;


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


    @FindBy(xpath = "//span[text()=\"Pending Reviews\"]")
    public WebElement pendingReviewsLink;

    @FindBy(xpath = "//table[@id='reviwGrid_table']//tbody/tr/td[contains(text(),'171')]")
    public WebElement reviewsToUpdate;

    @FindBy(xpath = "//input[@id='nickname']")
    public WebElement nickNameField;

    @FindBy(xpath = "//span[text()='Save Review']")
    public WebElement saveReviewButton;
    @FindBy(xpath = "//span[text()='The review has been saved.']")
    public WebElement saveUpdateReviewsSuccessMessage;



    @FindBy(xpath = "//input[@id='title']")
    public WebElement summaryofReviewsField;

    @FindBy(xpath = "(//span[text()='Save Review'])[1]")
    public WebElement pendingReviewSaveButton;

    @FindBy(xpath = "//span[text()='The review has been saved.']")
    public WebElement pendingReviewSuccessMessage;





    @FindAll(@FindBy(xpath = "//table[@id='reviwGrid_table']//tbody/tr"))
    List<WebElement>  pendingReviewsList;


    public ReviewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions=new Actions(driver);
        backEndLogin=new BackEndLogin(driver);
    }
    //1.Marketing manager can view all Reviews

    public void clickAllReviewsLink(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewsRatingsLink);
        actions.moveToElement(reviewsRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(allReviewsLink);
        actions.moveToElement(allReviewsLink).click().perform();

    }

    public boolean verifyViewAllReviews(){
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
        int i=(int)Math.random()*20;
        allReviewsList.get(i).click();
        testUtility.waitForElementPresent(nickNameField);
        nickNameField.clear();
        nickNameField.sendKeys(testUtility.generateFirstName());
        testUtility.waitForElementPresent(saveReviewButton);
        saveReviewButton.click();


    }
    public boolean verifyUpdateReviews(){
        testUtility.waitForElementPresent(saveUpdateReviewsSuccessMessage);
        if(saveUpdateReviewsSuccessMessage.isDisplayed())
            return true;
        else return false;
    }


    //3.Marketing manager can view pending reviews
    public void viewPendingReviews(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewsRatingsLink);
        actions.moveToElement(reviewsRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(pendingReviewsLink);
        actions.moveToElement(pendingReviewsLink).click().perform();

    }
    public boolean verifViewPendingViews(){
        if(pendingReviewsList.size()>=1)
            return true;
        else
            return false;
    }


    // 4.Marketing manager can update pending reviews
    public void updatePendingReviews(){
        testUtility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().perform();
        testUtility.waitForElementPresent(reviewsRatingsLink);
        actions.moveToElement(reviewsRatingsLink).click().perform();
        testUtility.waitForElementPresent(customerReviewsLink);
        actions.moveToElement(customerReviewsLink).click().perform();
        testUtility.waitForElementPresent(pendingReviewsLink);
        pendingReviewsLink.click();
        testUtility.sleep(3);
        int i=(int)Math.random()*20;
        pendingReviewsList.get(i).click();
        testUtility.waitForElementPresent(summaryofReviewsField);
        summaryofReviewsField.clear();
        summaryofReviewsField.sendKeys(testUtility.generateFirstName());
        testUtility.waitForElementPresent(pendingReviewSaveButton);
        pendingReviewSaveButton.click();


    }
    public boolean verifyUpdatePendinReviews(){
        testUtility.waitForElementPresent(pendingReviewSuccessMessage);

        testUtility.sleep(3);
        if(pendingReviewSuccessMessage.isDisplayed())
            return true;
        else return false;
    }













































    // if(allReviews.size>=1)
//    return true;





}
