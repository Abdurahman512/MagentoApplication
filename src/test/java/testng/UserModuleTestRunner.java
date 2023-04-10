package testng;
import maganto.backendpages.storepages.StoreViewPage;
import maganto.frontendpages.*;
import maganto.frontendpages.AccountInfoPage;
import maganto.frontendpages.OrdersPage;
import maganto.frontendpages.ShoppingCartPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
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
    StoreViewPage storeViewPage;
    OrdersPage ordersPage;



    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "frontendurl"));
        accountInfoPage = new AccountInfoPage(driver);
        shoppingCartPage=new ShoppingCartPage(driver);
        productReviewsPage=new ProductReviewsPage(driver);
        newsLetterSubscriptionsPage=new NewsLetterSubscriptionsPage(driver);
       storeViewPage=new StoreViewPage(driver);
        utility = new TestUtility(driver);
        ordersPage = new OrdersPage(driver);
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
    @Ignore
    public void addProductToShoppingCart(){
        shoppingCartPage.addProductsToShoppingCart();
        Assert.assertTrue(shoppingCartPage.verifyAddedToShoppingCartSuccessfully());
    }
    @Test(dependsOnMethods = {"addProductToShoppingCart"})
    @Ignore
    public void updateExistingShoppingCart(){
        shoppingCartPage.updateShoppingCart("5");
        Assert.assertTrue(shoppingCartPage.verifyUpdateSuccessfully("5"));

    }
    @Test(dependsOnMethods = {"updateExistingShoppingCart"})
    @Ignore
    public void checkOutOrderTest(){
        shoppingCartPage.checkOutOrder(utility.generateStreetAddress(),
                utility.generateCityName(), utility.generateZipCode(), utility.generateTelephoneNumber());
        Assert.assertTrue(shoppingCartPage.checkOutOrderSuccessfully());

    }

    @Test(dependsOnMethods = {"createAccount"})
    @Ignore
    public void viewOrders() {
        ordersPage.ViewMyOrders();
        Assert.assertTrue(ordersPage.MyOrdersPage());
    }

    @Test(dependsOnMethods = {"createAccount"})
    @Ignore
    public void viewDownloadOrders() {
        ordersPage.viewDownloadOrders();
        Assert.assertTrue(ordersPage.DownloadOrdersPage());
    }
    @Test(dependsOnMethods = {"createAccount"})
    public void viewNewsLetterSubscription(){
        newsLetterSubscriptionsPage.viewNewsLetterSubscription();
        Assert.assertTrue((newsLetterSubscriptionsPage.verifyViewNewsletterContent()));

    }
    @Test(dependsOnMethods = {"createAccount"})
    public void changePassword(){
        accountInfoPage.changePassword();
        Assert.assertTrue((accountInfoPage.VerifyChangePassword()));

    }
    @Test(dependsOnMethods = {"createAccount"})
        public void ProductReviews(){
        productReviewsPage.ProductReviews();
        Assert.assertTrue(productReviewsPage.verifyProductReviews());



}

    @Test(dependsOnMethods = {"storePageLogin"})
    public void createStoreView(){
        storeViewPage.createStoreView();
        Assert.assertTrue(storeViewPage.verifyStoreViewSaved());
        
    }

    @AfterClass
    public void tearDown(){
        closeBrowser();
    }

}
