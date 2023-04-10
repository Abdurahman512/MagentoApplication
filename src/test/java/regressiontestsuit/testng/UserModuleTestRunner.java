package regressiontestsuit.testng;
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
    WishListPage wishListPage;
    OrdersPage ordersPage;
    AddressBookPage addressBookPage;



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
        ordersPage = new OrdersPage(driver);
        addressBookPage=new AddressBookPage(driver);
        ordersPage = new OrdersPage(driver);
        context.setAttribute("driver",driver);
    }
    @Test
    public void createAccount() {
        accountInfoPage.userCreateAccount(utility.generateFirstName(), utility.generateLastName(), utility.generateEmailAddress(), ApplicationConfig.readFromConfigProperties(configFile, "password"));
        Assert.assertTrue(accountInfoPage.isAccountCreated());

    }
    @Test(dependsOnMethods = {"createAccount"},priority = 1)
    public void editAccountInfo(){
        accountInfoPage.editAccount(utility.generateFirstName(),ApplicationConfig.readFromConfigProperties(configFile,"password"));
        Assert.assertTrue(accountInfoPage.isAccountEdited());
    }
    @Test(dependsOnMethods = {"createAccount"},priority = 2)
    public void viewAccountInfo(){
        accountInfoPage.viewAccount();
        Assert.assertTrue(accountInfoPage.isAccountViewed());
    }
    @Test(dependsOnMethods ={"createAccount"},priority = 8)
    public void addProductToShoppingCart(){
        shoppingCartPage.addProductsToShoppingCart();
        Assert.assertTrue(shoppingCartPage.verifyAddedToShoppingCartSuccessfully());
    }
    @Test(dependsOnMethods = {"addProductToShoppingCart"})
    public void updateExistingShoppingCart(){
        shoppingCartPage.updateShoppingCart();
        Assert.assertTrue(shoppingCartPage.verifyUpdateSuccessfully());

    }
    @Test(dependsOnMethods = {"updateExistingShoppingCart"})
    public void checkOutOrderTest(){
        shoppingCartPage.checkOutOrder(utility.generateStreetAddress(),
                utility.generateCityName(), utility.generateZipCode(), utility.generateTelephoneNumber());
        Assert.assertTrue(shoppingCartPage.checkOutOrderSuccessfully());

    }
    @Test(dependsOnMethods = {"createAccount"},priority = 3)
    public void viewOrders() {
        ordersPage.ViewMyOrders();
        Assert.assertTrue(ordersPage.MyOrdersPage());
    }

    @Test(dependsOnMethods = {"createAccount"},priority = 4)
    public void viewDownloadOrders() {
        ordersPage.viewDownloadOrders();
        Assert.assertTrue(ordersPage.DownloadOrdersPage());
    }
    @Test(dependsOnMethods = {"createAccount"},priority = 5)
    public void viewNewsLetterSubscription(){
        newsLetterSubscriptionsPage.viewNewsLetterSubscription();
        Assert.assertTrue((newsLetterSubscriptionsPage.verifyViewNewsletterContent()));

    }
    @Test(dependsOnMethods = {"createAccount"},priority = 7)
    public void changePassword(){
        accountInfoPage.changePassword();
        Assert.assertTrue((accountInfoPage.VerifyChangePassword()));

    }
    @Test(dependsOnMethods = {"createAccount"},priority = 6)
     public void ProductReviews(){
        productReviewsPage.ProductReviews();
        Assert.assertTrue(productReviewsPage.verifyProductReviews());
}

    @AfterClass
    public void tearDown(){
        closeBrowser();
    }

}
