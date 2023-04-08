package testng;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.storepages.StoreDashboardPage;
import maganto.backendpages.storepages.StoreOrdersPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
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
    }
    @Test()
    public void createOrderTest(){
        storeDashboardPage.clickOnOrdersLink();
        storeOrdersPage.createNewOrder();
        Assert.assertTrue(storeDashboardPage.orderSuccessfullyCreated());
    }
    @Test
    public void updateOrderTest(){}
    @Test
    public void cancelOrderTest(){}
    @AfterClass
    public void tearDown() {
        closeBrowser();
    }

}
