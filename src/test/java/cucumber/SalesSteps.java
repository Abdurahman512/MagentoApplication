package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.salespages.OrdersPage;
import maganto.backendpages.salespages.SalesDashboardPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestUtility;

public class SalesSteps extends TestBase {
    TestUtility utility;
    OrdersPage ordersPage;
    SalesDashboardPage dashboardPage;
    BackEndLogin login;
    final static String configFile = "config.properties";
    @Before ("@SalesModuleTest")
    public void setUp(){
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        ordersPage = new OrdersPage(driver);
        dashboardPage=new SalesDashboardPage(driver);
        utility = new TestUtility(driver);
        login = new BackEndLogin(driver);
        login.salesPageLogin();
    }

    @Given("sales manager is on the admin page")
    public void salesManagerIsOnTheAdminPage() {
        dashboardPage.openOrdersPage();
    }

    @When("the sales manager fills out a new order form")
    public void theSalesManagerFillsOutANewOrderForm() {
        ordersPage.createOrder("TastData/CategoryTestData.xlsx","Sheet2",1,0);
    }

    @Then("a new order should be created")
    public void aNewOrderShouldBeCreated() {
        ordersPage.isOrderCreated();

    }

    @After
    public void tearDown(){
        closeBrowser();

    }
}
