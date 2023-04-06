package testng;

import maganto.frontendpages.AccountInfoPage;
import maganto.frontendpages.WishListPage;
import maganto.utility.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestResultListener.class)
public class UserModuleTestRunner extends TestBase {

    TestUtility utility;
    AccountInfoPage accountInfoPage;

    WishListPage wishListPage;
    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "frontendurl"));
        accountInfoPage = new AccountInfoPage(driver);
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
    public void editAccountInfo(){
        accountInfoPage.editAccount(utility.generateFirstName(),ApplicationConfig.readFromConfigProperties(configFile,"password"));
        Assert.assertTrue(accountInfoPage.isAccountEdited());
    }
    @Test (dependsOnMethods ={"createAccount"})
    public void viewAccountInfo(){
        accountInfoPage.viewAccount();
        Assert.assertTrue(accountInfoPage.isAccountViewed());
    }

    @Test (dependsOnMethods ={"createAccount"})
    public void viewWishList(){
        wishListPage.viewMyWishList();
        Assert.assertTrue(wishListPage.isMyWishListAbleToView());
    }

    @AfterClass
    public void tearDown(){
        closeBrowser();
    }

}
