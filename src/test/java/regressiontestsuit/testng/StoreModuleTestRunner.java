package regressiontestsuit.testng;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.storepages.*;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestResultListener.class)
public class StoreModuleTestRunner extends TestBase {
    StoreOrdersPage storeOrdersPage;
    StoreDashboardPage storeDashboardPage;
    StoreProductPage storeProductPage;
    StoreViewPage storeViewPage;
    StoreWebsitePage storeWebsitePage;
    StorePage storePage;
    TestUtility utility;
    final static String configFile = "config.properties";
    BackEndLogin backEndLogin;

    @BeforeClass
    public void setUp(ITestContext context){
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        utility = new TestUtility(driver);
        context.setAttribute("driver",driver);
        storeOrdersPage=new StoreOrdersPage(driver);
        storeDashboardPage=new StoreDashboardPage(driver);
        storePage=new StorePage(driver);
        backEndLogin=new BackEndLogin(driver);
        backEndLogin.storePageLogin();
    }
    @Test(enabled = false)
    public void createOrderTest(){
        storeOrdersPage.createNewOrderMethod();
        Assert.assertTrue(storeDashboardPage.orderSuccessfullyCreated());
    }
    @Test(dependsOnMethods = {"createOrderTest"},enabled = false)
    public void updateOrderTest(){
        storeOrdersPage.updateOrder();
        Assert.assertTrue(storeDashboardPage.orderSuccessfullyCreated());
    }
    @Test(dependsOnMethods = {"updateOrderTest"},enabled = false)
    public void cancelOrders(){
        storeOrdersPage.cancelOrder();
        Assert.assertTrue(storeOrdersPage.deleteOrderSuccessfully());
    }

    @Test(priority = 1)
    public void createStoreTest(){
        storePage.createStore();
        Assert.assertTrue(storePage.addStoreSuccessfullyMessage());
    }

    @Test(priority = 2)
    public void editStoreTest(){
        storePage.editStore();
        Assert.assertTrue(storePage.editStoreSuccessfullyMessage());
    }

    @Test(priority = 3)
    public void deleteStoreTest(){
        storePage.deleteStore();
        Assert.assertTrue(storePage.deleteStoreSuccessfullyMessage());
    }
    @AfterClass
    public void tearDown() {
        closeBrowser();
    }

}
