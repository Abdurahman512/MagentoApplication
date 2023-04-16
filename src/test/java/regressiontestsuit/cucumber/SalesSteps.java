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

    //sales manager can create a new order
    @Given("sales manager is on the admin page")
    public void salesManagerIsOnTheAdminPage() {
        dashboardPage.openOrdersPage();
    }

    @When("the sales manager fills out a new order form {string}")
    public void theSalesManagerFillsOutANewOrderForm(String arg0) {
        ordersPage.createOrder(arg0);
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
    @When("Sales manager Click on Reports move to reports and move to Sales and select refunds")
    public void salesManagerClickOnReportsMoveToReportsAndMoveToSalesAndSelectRefunds() {

    }
    @And("fill in {string} and {string} field")
    public void fillInAndField(String arg0, String arg1) {
    }
    @Then("Total Refunded Report page should display with information")
    public void totalRefundedReportPageShouldDisplayWithInformation() {
    }


    @After ("@SalesModuleTests")
    public void tearDown(){
        closeBrowser();
    }



}
