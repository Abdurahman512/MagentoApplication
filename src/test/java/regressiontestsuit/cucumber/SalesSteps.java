package regressiontestsuit.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.salespages.OrdersPage;
import maganto.backendpages.salespages.SalesDashboardPage;
import maganto.backendpages.salespages.SalesShipmentsPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestUtility;

public class SalesSteps extends TestBase {
    TestUtility utility;
    OrdersPage ordersPage;
    SalesDashboardPage dashboardPage;
    BackEndLogin login;
    SalesShipmentsPage salesShipmentsPage;

    final static String configFile = "config.properties";
    @Before ("@SalesModuleTests")
    public void setUp(){
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        ordersPage = new OrdersPage(driver);
        salesShipmentsPage=new SalesShipmentsPage(driver);
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
        ordersPage.createOrder("TestData/TestData-M.xlsx","Sales",1,0);
    }

    @Then("a new order should be created")
    public void aNewOrderShouldBeCreated() {
        ordersPage.isOrderCreated();
    }

    //UpdateShipments

    @Given("Sales manager is on the dashboard page and clicks on shipmentsOption")
    public void salesManagerIsOnTheDashboardPageAndClicksOnShipmentsOption() {

        salesShipmentsPage.clickOnShipmentsOption();
    }

    @When("Sales Manager click view icon and fill out {string} information and click on submit comment button")
    public void salesManagerClickViewIconAndFillOutInformationAndClickOnSubmitCommentButton(String arg0) {
        salesShipmentsPage = new SalesShipmentsPage(driver);
        salesShipmentsPage.updateShipmentsHistory(arg0);
    }

    @And("Sales Manager edit shipping and tracking information and fill out {string} and click on add button")
    public void salesManagerEditShippingAndTrackingInformationAndFillOutAndClickOnAddButton(String arg0) {
        salesShipmentsPage.updateTrackingInformation(arg0);
    }

    @Then("the shipments update successfully")
    public void theShipmentsUpdateSuccessfully() {
        salesShipmentsPage.verifyUpdateShipmentsHistorySuccessfully();
        salesShipmentsPage.verifyUpdateShipmentsTrackingInformationSuccessfully();
    }


    @After ("@SalesModuleTests")
    public void tearDown(){
        closeBrowser();
    }


}
