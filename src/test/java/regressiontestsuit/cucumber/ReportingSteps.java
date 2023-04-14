package regressiontestsuit.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.reportingpages.InvoicedVsPaidReportPage;
import maganto.backendpages.reportingpages.ReportingDashboardPage;
import maganto.backendpages.reportingpages.SalesOrdersPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestUtility;
import maganto.backendpages.reportingpages.*;
import maganto.utility.*;
import io.cucumber.java.Scenario;

public class ReportingSteps extends TestBase {
    TestUtility utility;
    ReportingDashboardPage dashboardPage;
    SalesOrdersPage salesOrdersPage;
    BackEndLogin login;
    InvoicedVsPaidReportPage invoicedVsPaidReportPage;
    CustomersByOrdersTotal customersByOrdersTotal;
    CustomersByNumberOfOrders customersByNumberOfOrders;
    final static String configFile = "config.properties";

    @Before ("@ReportingModuleTests")
    public void setUp(){
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        dashboardPage= new ReportingDashboardPage(driver);
        salesOrdersPage =new SalesOrdersPage(driver);
        invoicedVsPaidReportPage=new InvoicedVsPaidReportPage(driver);
        customersByOrdersTotal=new CustomersByOrdersTotal(driver);
        customersByNumberOfOrders=new CustomersByNumberOfOrders(driver);
        utility = new TestUtility(driver);
        login = new BackEndLogin(driver);
        login.reportingPageLogin();
    }

    //reporting manager can can see sales-total ordered report
    @Given("reporting manager is on the admin page")
    public void reportingManagerIsOnTheAdminPage() {
        dashboardPage.openOrdersPage();
    }

    @When("reporting manager fills out report date {string} and{string}")
    public void reportingManagerFillsOutReportAnd(String arg0, String arg1) {
        salesOrdersPage.viewTotalOrdersReport(arg0, arg1);
    }

    @Then("total ordered report should display")
    public void totalOrderedReportShouldDisplay() {
        salesOrdersPage.isOrdersReportShowed();
    }


    //See Sales-Total Invoiced vs Paid Report
    @Given("Reporting manager is on the dashboard page and clicks on Invoiced Option")
    public void reportingManagerIsOnTheDashboardPageAndClicksOnInvoicedOption() {
        dashboardPage.ClickOnInvoicedOption();

    }

    @When("Reporting Manager Navigate to Total Invoiced vs Paid Report page and select period and date {string} {string} and click show Report button")
    public void reportingManagerNavigateToTotalInvoicedVsPaidReportPageAndSelectPeriodAndDateAndClickShowReportButton(String arg0, String arg1) {
        invoicedVsPaidReportPage = new InvoicedVsPaidReportPage(driver);
        invoicedVsPaidReportPage.viewSalesInvoicedVsPaidReport(arg0, arg1);
    }

   @Then("Total Invoiced Vs Paid report view successfully")
    public void totalInvoicedVsPaidReportViewSuccessfully() {
        invoicedVsPaidReportPage.verifyViewSalesInvoicedVsPaidReportSuccessfully();
   }

    // see customers by orders total
    @Given("Reporting manager is on the dashboard page and clicks on customer by order total link")
    public void reportingManagerIsOnTheDashboardPageAndClicksOnCustomerByOrderTotalLink() {
        dashboardPage.ClickOnCustomersByOrdersTotalLink();
    }

    @When("Reporting manager enter {string}{string} and click refresh button")
    public void reportingManagerEnterAndClickRefreshButton(String arg0, String arg1) {
        customersByOrdersTotal = new CustomersByOrdersTotal(driver);
        customersByOrdersTotal.customerByOrdersTotalMethod(arg0, arg1);

    }

    @Then("verifymanager can see customers by orders total")
    public void verifymanagerCanSeeCustomersByOrdersTotal() {
        customersByOrdersTotal = new CustomersByOrdersTotal(driver);
        org.testng.Assert.assertTrue(customersByOrdersTotal.verifyManagerCanSeeCustomersByOrdersTotal());
    }

    @Given("Reporting manager is on the dashboard page and clicks on customer by number of orders link")
    public void reportingManagerIsOnTheDashboardPageAndClicksOnCustomerByNumberOfOrdersLink() {
        dashboardPage.ClickOnCustomersByNumberOfOrdersLink();
    }

    @When("Reporting manager enter {string}{string} and click on refresh button")
    public void reportingManagerEnterAndClickOnRefreshButton(String arg0, String arg1) {
        customersByNumberOfOrders = new CustomersByNumberOfOrders(driver);
        customersByNumberOfOrders.customerByNumberOfOrdersMethod(arg0, arg1);
    }

    @Then("verifymanager can see customers by number of orders")
    public void verifymanagerCanSeeCustomersByNumberOfOrders() {
        customersByNumberOfOrders = new CustomersByNumberOfOrders(driver);
        org.testng.Assert.assertTrue(customersByNumberOfOrders.verifyManagerCanSeeCustomersByNumberOfOrders());
    }




    @After("@ReportingModuleTest")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenShotUtility screenShotUtility = new ScreenShotUtility();
            screenShotUtility.takeScreenshot("image", "failedTest", driver);
        }
        closeBrowser();
    }



}
