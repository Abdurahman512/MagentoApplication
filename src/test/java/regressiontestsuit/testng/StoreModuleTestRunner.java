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
import org.testng.annotations.*;

@Listeners(TestResultListener.class)
public class StoreModuleTestRunner extends TestBase {
    StoreOrdersPage storeOrdersPage;
    StoreDashboardPage storeDashboardPage;
    StoreProductPage storeProductPage;
    StoreViewPage storeViewPage;
    StoreWebsitePage storeWebsitePage;
    StorePage storePage;
    StoreProductCategoriesPage storeProductCategoriesPage;
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
        backEndLogin=new BackEndLogin(driver);
        backEndLogin.storePageLogin();
        storeProductCategoriesPage=new StoreProductCategoriesPage(driver);
    }
    @Test
    @Ignore
    public void createOrderTest(){
        storeOrdersPage.createNewOrderMethod();
        Assert.assertTrue(storeDashboardPage.orderSuccessfullyCreated());
    }
    @Test(dependsOnMethods = {"createOrderTest"})
    @Ignore
    public void updateOrderTest(){
        storeOrdersPage.updateOrder();
        Assert.assertTrue(storeDashboardPage.orderSuccessfullyCreated());
    }
    @Test(dependsOnMethods = {"updateOrderTest"})
    @Ignore
    public void cancelOrders(){
        storeOrdersPage.cancelOrder();
        Assert.assertTrue(storeOrdersPage.deleteOrderSuccessfully());
    }

    @Test(priority = 1)
    public void addProductCatalog(){
        storeProductCategoriesPage.addProductCatalog();
        Assert.assertTrue(storeProductCategoriesPage.verifyAddedProductCatalog());
    }

    @Test(dependsOnMethods = "addProductCatalog",priority = 2)
    public void updateProductCatalog(){
        storeProductCategoriesPage.updateProductCatalog();
        Assert.assertTrue(storeProductCategoriesPage.verifyUpdatedProductCatalog());
    }

    @Test(dependsOnMethods = "updateProductCatalog",priority = 3)
    public void deleteProductCatalog(){
        storeProductCategoriesPage.deleteProductCatalog();
        Assert.assertTrue(storeProductCategoriesPage.verifyDeletedProductCatalog());
    }



    @AfterClass
    public void tearDown() {
        closeBrowser();
    }

}
