package testng;

import maganto.frontendpages.AccountInfoPage;
import maganto.frontendpages.DashboardPage;
import maganto.frontendpages.NewsLetterSubscriptionsPage;
import maganto.frontendpages.ProductReviewsPage;
import maganto.utility.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestResultListener.class)
public class UserModuleTestRunner extends TestBase {

    TestUtility utility;
    AccountInfoPage accountInfoPage;
    NewsLetterSubscriptionsPage newsLetterSubscriptionsPage;
    ProductReviewsPage productReviewsPage;
    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "frontendurl"));
        accountInfoPage = new AccountInfoPage(driver);
        newsLetterSubscriptionsPage=new NewsLetterSubscriptionsPage(driver);
        productReviewsPage=new ProductReviewsPage(driver);
        utility = new TestUtility(driver);
        context.setAttribute("driver",driver);
    }

    @Test
    public void createAccount() {
        accountInfoPage.userCreateAccount(utility.generateFirstName(), utility.generateLastName(), utility.generateEmailAddress(), ApplicationConfig.readFromConfigProperties(configFile, "password"));
        Assert.assertTrue(accountInfoPage.isAccountCreated());

    }
    @Test (dependsOnMethods ={"createAccount"})
    public void editAccountInfo(){
        accountInfoPage.editAccount(utility.generateFirstName(),ApplicationConfig.readFromConfigProperties(configFile,"password"));
        Assert.assertTrue(accountInfoPage.isAccountEdited());
    }
    @Test (dependsOnMethods ={"createAccount"})
    public void viewAccountInfo(){
        accountInfoPage.viewAccount();
        Assert.assertTrue(accountInfoPage.isAccountViewed());
    }

    @Test(dependsOnMethods ={"createAccount"})
    public void viewNewsLetterSubscription(){
      newsLetterSubscriptionsPage.viewNewsLetterSubscription(driver);
        Assert.assertTrue(newsLetterSubscriptionsPage.verifyViewNewsletterContent());


    }
    @Test(dependsOnMethods ={"createAccount"})
    public void ProductReviews() {
        productReviewsPage.ProductReviews(driver);
        Assert.assertTrue(productReviewsPage.verifyProductReviews());
    }

        @AfterClass
        public void tearDown() {
            closeBrowser();
        }

    }
