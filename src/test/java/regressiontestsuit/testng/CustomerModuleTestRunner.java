package regressiontestsuit.testng;

import maganto.backendpages.BackEndLogin;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import maganto.backendpages.customerpages.*;
import org.testng.Assert;
import org.testng.annotations.*;


@Listeners(TestResultListener.class)
public class CustomerModuleTestRunner extends TestBase {
    TestUtility utility;
    BackEndLogin login;
    final static String configFile = "config.properties";
    CustomerPage customerPage;

    CustomerGroupsPage customerGroupsPage;
    CustomerDashboardPage customerDashboardPage;
    AddAddressesPage addAddressesPage;

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        utility = new TestUtility(driver);
        context.setAttribute("driver", driver);
        customerDashboardPage = new CustomerDashboardPage(driver);
        login = new BackEndLogin(driver);
        login.customerPageLogin();
        customerPage = new CustomerPage(driver);
    }

    @Test(groups = "regression test", description = "Customer Manager can add a new customer ")
    public void addNewCustomer() {
        login.VerifyLoginSuccessfully();
        customerPage.addNewCustomerMethod();
        Assert.assertTrue(customerPage.verifyNewCustomerAdded());

    }

    @Test(groups = "regression test", description = "Customer Manager can update an existing customer ", dependsOnMethods = "addNewCustomer",priority = 1)
    public void updateCustomer() {
        customerPage.updateCustomer();
        Assert.assertTrue(customerPage.verifyUpdateCustomer());
    }

    @Test(groups = "regression test", description = "Customer Manager can delete an existing customer", dependsOnMethods = "updateCustomer")
    public void deleteExistingCustomer() {
        customerDashboardPage.clickOnManageCustomers();
        customerPage.deleteCustomer();
        Assert.assertTrue(customerPage.verifyDeleteCustomer());
    }

    @Test(groups = "regression test", description = "Customer Manager can filter customers by email",dependsOnMethods = "addNewCustomer",priority = 2)
    public void filterCustomersByEmail() {
        customerDashboardPage.filterCustomersByEmail();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByEmail());
    }

    @Test(groups = "regression test", description = "Customer Manager can filter customers by group",dependsOnMethods = "addNewCustomer",priority = 3)
    public void filterCustomersByGroup() {
        customerDashboardPage.filterCustomersByGroup();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
    }

    @Test(dataProvider = "filterData", groups = "regression test",
            description = "Customer Manager can filter by Country,State,Website",dependsOnMethods = "addNewCustomer",priority = 4)
    public void filterCustomerByCountryStateWebsite(String state) {
        customerDashboardPage.filterCustomersByCountryStateWebsite(state);
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }


    @DataProvider
    public Object[][] filterData() {
        Object[][] data = new Object[][]{
                {"New York"}
        };
        return data;
    }
}