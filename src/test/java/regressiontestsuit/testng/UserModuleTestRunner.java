package regressiontestsuit.testng;

import maganto.frontendpages.*;
import maganto.utility.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestResultListener.class)
public class UserModuleTestRunner extends TestBase {

    TestUtility utility;
    NewsLetterSubscriptionsPage newsLetterSubscriptionsPage;
    ProductReviewsPage productReviewsPage;
    AccountInfoPage accountInfoPage;
    ShoppingCartPage shoppingCartPage;
    WishListPage wishListPage;
    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "frontendurl"));
        accountInfoPage = new AccountInfoPage(driver);
        shoppingCartPage=new ShoppingCartPage(driver);
        productReviewsPage=new ProductReviewsPage(driver);
        newsLetterSubscriptionsPage=new NewsLetterSubscriptionsPage(driver);

        utility = new TestUtility(driver);
        wishListPage=new WishListPage(driver);
        context.setAttribute("driver",driver);
    }

    @Test
    public void createAccount() {
        accountInfoPage.userCreateAccount(utility.generateFirstName(), utility.generateLastName(), utility.generateEmailAddress(), ApplicationConfig.readFromConfigProperties(configFile, "password"));
        Assert.assertTrue(accountInfoPage.isAccountCreated());

    }
    @Test (dependsOnMethods ={"createAccount"})
    @Ignore
    public void editAccountInfo(){
        accountInfoPage.editAccount(utility.generateFirstName(),ApplicationConfig.readFromConfigProperties(configFile,"password"));
        Assert.assertTrue(accountInfoPage.isAccountEdited());
    }
    @Test (dependsOnMethods ={"createAccount"})
    @Ignore
    public void viewAccountInfo(){
        accountInfoPage.viewAccount();
        Assert.assertTrue(accountInfoPage.isAccountViewed());
    }

    @Test(dependsOnMethods = {"createAccount"})
    public void changePasswordTest(){
        accountInfoPage.changePassword(ApplicationConfig.readFromConfigProperties(configFile,"password"),
                ApplicationConfig.readFromConfigProperties(configFile,"newPassword"),
                ApplicationConfig.readFromConfigProperties(configFile,"ConfirmNewPassword"));
       Assert.assertTrue(accountInfoPage.VerifyChangePassword());
    }

    @Test(dependsOnMethods ={"createAccount"})
    public void addProductToShoppingCart(){
        shoppingCartPage.addProductsToShoppingCart();
        Assert.assertTrue(shoppingCartPage.verifyAddedToShoppingCartSuccessfully());
    }
    @Test(dependsOnMethods = {"addProductToShoppingCart"})
    public void updateExistingShoppingCart(){
        shoppingCartPage.updateShoppingCart("5");
        Assert.assertTrue(shoppingCartPage.verifyUpdateSuccessfully("5"));

    }
    @Test(dependsOnMethods = {"updateExistingShoppingCart"})
    public void checkOutOrderTest(){
        shoppingCartPage.checkOutOrder(utility.generateStreetAddress(),
                utility.generateCityName(), utility.generateZipCode(), utility.generateTelephoneNumber());
        Assert.assertTrue(shoppingCartPage.checkOutOrderSuccessfully());

    }


    @Test (dependsOnMethods ={"createAccount"})
    public void viewWishList(){
        wishListPage.viewMyWishList();
        Assert.assertTrue(wishListPage.isMyWishListAbleToView());

    }
    @Test(dependsOnMethods = {"createAccount"})
    public void viewNewsLetterSubscription(){
        newsLetterSubscriptionsPage.viewNewsLetterSubscription();
        Assert.assertTrue((newsLetterSubscriptionsPage.verifyViewNewsletterContent()));


    }
@Test(dependsOnMethods = {"createAccount"})
public void ProductReviews(){
        productReviewsPage.ProductReviews();
        Assert.assertTrue(productReviewsPage.verifyProductReviews());
}
    @AfterClass
    public void tearDown(){
        closeBrowser();
    }
    }


