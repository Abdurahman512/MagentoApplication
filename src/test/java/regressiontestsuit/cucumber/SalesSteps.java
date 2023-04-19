package regressiontestsuit.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.salespages.InvoicesPage;
import maganto.backendpages.salespages.OrdersPage;
import maganto.backendpages.salespages.SalesDashboardPage;
import maganto.backendpages.salespages.SalesShipmentsPage;
import maganto.backendpages.salespages.*;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestUtility;
import org.testng.Assert;

public class SalesSteps extends TestBase {
    TestUtility utility;
    OrdersPage ordersPage;
    SalesDashboardPage dashboardPage;
    BackEndLogin login;
    SalesShipmentsPage salesShipmentsPage;

    InvoicesPage invoicesPage;
    RefundsPage refundsPage;
    CouponsPage couponsPage;

    final static String configFile = "config.properties";
    @Before ("@SalesModuleTests")
    public void setUp(){
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        ordersPage = new OrdersPage(driver);
        salesShipmentsPage=new SalesShipmentsPage(driver);
        refundsPage=new RefundsPage(driver);
        couponsPage=new CouponsPage(driver);
        dashboardPage=new SalesDashboardPage(driver);
        utility = new TestUtility(driver);
        login = new BackEndLogin(driver);
        login.salesPageLogin();
        invoicesPage=new InvoicesPage(driver);
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
        salesShipmentsPage.verifyUpdateShipmentsTrackingInformationSuccessfully();}

    //Sales Manager should be able to view refunds in the Reports
    @When("Sales manager Click on Reports move to reports and move to Sales and select refunds fill in {string} and {string} field")
    public void salesManagerClickOnReportsMoveToReportsAndMoveToSalesAndSelectRefundsFillInAndField(String arg0, String arg1) {
        refundsPage.viewRefundsReports(arg0,arg1);

    }

    @Then("Total Refunded Report page should display with information")
    public void totalRefundedReportPageShouldDisplayWithInformation() {
        refundsPage.verifyMessageRefunds();
        Assert.assertTrue(refundsPage.verifyMessageRefunds());

    }
    //Sales Manager should be able to view coupons in the Reports
    @When("Sales manager Click on Reports move to reports and move to Sales and select coupons fill in {string}and{string}field")
    public void salesManagerClickOnReportsMoveToReportsAndMoveToSalesAndSelectCouponsFillInAndField(String arg0, String arg1) {
        couponsPage.couponsReports(arg0, arg1);
    }
    @Then("Total coupons Report page should display with information")
    public void totalCouponsReportPageShouldDisplayWithInformation() {
        couponsPage.verifyCouponsReports();
        Assert.assertTrue(couponsPage.verifyCouponsReports());
    }
    @Given("Sales Manager is on the Invoice Dashboard Page")
    public void salesManagerIsOnTheInvoiceDashboardPage() {
        dashboardPage.openInvoicePage();
    }

    @When("Sales Manager View the Invoice Comment Field")
    public void salesManagerViewTheInvoiceCommentField() {
        invoicesPage.viewInvoiceCommentHistory();
    }

    @Then("Sales Manager Can See The Invoice Comment History")
    public void salesManagerCanSeeTheInvoiceCommentHistory() {
        invoicesPage.verifyViewInvoiceCommentHistorySuccessfully();
    }
    @When("Sales Manager fills out {string}")
    public void salesManagerFillsOut(String arg0) {
        invoicesPage.addInvoiceComment(arg0);
    }
    @Then("The Comment List Should Be Updated")
    public void theCommentListShouldBeUpdated() {
        invoicesPage.verifyAddInvoiceCommentSuccessfully();
    }
    @After ("@SalesModuleTests")
    public void tearDown(){
        closeBrowser();
    }



}
