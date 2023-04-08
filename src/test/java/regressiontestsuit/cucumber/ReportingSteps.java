package regressiontestsuit.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.reportingpages.ReportingDashboardPage;
import maganto.backendpages.reportingpages.SalesOrdersPage;
import maganto.backendpages.salespages.OrdersPage;
import maganto.backendpages.salespages.SalesDashboardPage;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestUtility;

public class ReportingSteps extends TestBase {
    TestUtility utility;
    ReportingDashboardPage dashboardPage;
    SalesOrdersPage salesOrdersPage;
    BackEndLogin login;
    final static String configFile = "config.properties";
    @Before ("@ReportingModuleTests")
    public void setUp(){
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        dashboardPage= new ReportingDashboardPage(driver);
        salesOrdersPage =new SalesOrdersPage(driver);
        utility = new TestUtility(driver);
        login = new BackEndLogin(driver);
        login.reportingPageLogin();
    }

    @Given("reporting manager is on the admin page")
    public void reportingManagerIsOnTheAdminPage() {
        dashboardPage.openOrdersPage();
    }

    @When("reporting manager fills out report starting ending dates")
    public void reportingManagerFillsOutReportStartingEndingDates() {
        salesOrdersPage.viewTotalOrdersReport("TastData/TestData-M.xlsx","Reporting",1,0,1,1);

    }

    @Then("total ordered report should display")
    public void totalOrderedReportShouldDisplay() {
        salesOrdersPage.isOrdersReportShowed();
    }

    @After  ("@ReportingModuleTests")
    public void tearDown(){
        closeBrowser();
    }
}
