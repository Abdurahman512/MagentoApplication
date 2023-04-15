package maganto.backendpages.marketingpages;


import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class NewsletterPage {

        WebDriver driver;
        TestUtility testUtility;
        Actions actions;

        @FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[5]/a[1]/span[1]")
        WebElement newsletterLink;
        @FindBy(xpath = "//span[contains(text(),'Newsletter Subscribers')]")
        WebElement newsletterSubscribersLink;
        @FindBy(xpath="//body[1]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/h3[1]")
        WebElement newsletterSubscribersView;


        //Marketing Manager can view newsletter subscribers

        public NewsletterPage(WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(driver, this);
            testUtility = new TestUtility(driver);
            actions=new Actions(driver);
        }
        public void clickOnNewsletterLink(){
            testUtility.waitForElementPresent(newsletterLink);
            newsletterLink.click();
        }
        public void clickOnNewsLetterSubscribersLink(){
            testUtility.waitForElementPresent(newsletterSubscribersLink);
            newsletterSubscribersLink.click();

        }
        public boolean viewNewsLetterSubscribersSuccessfully(){
            return newsletterSubscribersView.isDisplayed();

        }
    }









