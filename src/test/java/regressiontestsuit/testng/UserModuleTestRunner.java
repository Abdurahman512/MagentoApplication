package regressiontestsuit.testng;

import maganto.frontendpages.AccountInfoPage;
import maganto.frontendpages.AddressBookPage;
import maganto.frontendpages.ShoppingCartPage;
import maganto.frontendpages.WishListPage;
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
    AccountInfoPage accountInfoPage;
    ShoppingCartPage shoppingCartPage;
    WishListPage wishListPage;

    AddressBookPage addressBookPage;
    final static String configFile = "config.properties";

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "frontendurl"));
        accountInfoPage = new AccountInfoPage(driver);
        shoppingCartPage=new ShoppingCartPage(driver);
        utility = new TestUtility(driver);
        wishListPage=new WishListPage(driver);
        addressBookPage=new AddressBookPage(driver);
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


    @Test(dependsOnMethods ={"createAccount"})
    public void addAddressBook(){
        addressBookPage.addAddressBook(utility.generateFirstName(), utility.generateLastName(),
                utility.generateTelephoneNumber(), utility.generateStreetAddress(),
                utility.generateCityName(), utility.generateZipCode());
        Assert.assertTrue(addressBookPage.verifyAddedAddressBook());

    }
    @Test(dependsOnMethods ={"addAddressBook"})
    public void updateAddressBook(){
        addressBookPage.updateAddressBook("Conflict");
        Assert.assertTrue(addressBookPage.verifyUpdateSuccessfully());
    }

    @AfterClass
    public void tearDown(){
        closeBrowser();
    }

}
