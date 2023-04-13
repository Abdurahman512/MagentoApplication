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
    CustomerDashboardPage customerDashboardPage;
    AddAddressesPage addAddressesPage;
    CustomerGroupsPage customerGroupsPage;

    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        utility = new TestUtility(driver);
        context.setAttribute("driver", driver);
        customerDashboardPage = new CustomerDashboardPage(driver);
        login = new BackEndLogin(driver);
        login.customerPageLogin();
        customerPage = new CustomerPage(driver);
        addAddressesPage = new AddAddressesPage(driver);
        customerGroupsPage=new CustomerGroupsPage(driver);
    }

    @Test(groups = "regression test", description = "Customer Manager can add a new customer ")
    public void addNewCustomer() {
        login.VerifyLoginSuccessfully();
        customerPage.addNewCustomerMethod();
        Assert.assertTrue(customerPage.verifyNewCustomerAdded());

    }

    @Test(dependsOnMethods = {"addNewCustomer"}, groups = "regression test", description = "Customer Manager can update an existing customer ")
    public void updateCustomer() {
        customerPage.updateCustomer();
        Assert.assertTrue(customerPage.verifyUpdateCustomer());
    }

    @Test(dependsOnMethods = {"updateCustomer"}, groups = "regression test", description = "Customer Manager can delete an existing customer")
    public void deleteExistingCustomer() {
        customerDashboardPage.clickOnManageCustomers();
        customerPage.deleteCustomer();
        Assert.assertTrue(customerPage.verifyDeleteCustomer());
    }

    @Test(priority = 2, groups = "regression test", description = "Customer Manager can filter customers by email")
    public void filterCustomersByEmail() {
        customerDashboardPage.filterCustomersByEmail();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByEmail());
    }

    @Test(priority = 3,groups = "regression test")
    public void filterCustomersByGroup() {
        customerDashboardPage.filterCustomersByGroup();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
    }

    @Test(priority = 4,dataProvider = "filterData", groups = "regression test")
    public void filterCustomerByCountryStateWebsite(String state) {
        customerDashboardPage.filterCustomersByCountryStateWebsite(state);
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
    }

    @Test(priority = 5, groups = "regression test",description ="Customer Manager can Export customer")
    public void ExportCustomers() {
        customerPage.ExportCustomer();
        Assert.assertTrue(customerPage.verifyClickExportButton());
    }
    @Test(priority = 6,description ="Customer Manager can filter customers by Assign e Customer group")
    public void assignCustomer() {
        customerPage.AssignCustomer();
        Assert.assertTrue(customerPage.verifyAssigncustomer());
    }

    @Test(priority = 7)
    public void addNewCustomerGroups(){
        customerGroupsPage.addNewCustomerGroups();
        customerGroupsPage.verifyNewCustomerGroupAdded();
    }

    @Test(dependsOnMethods = {"addNewCustomerGroups"})
    public void updateCustomerGroup(){
        customerGroupsPage.updateCustomerGroup();
        customerGroupsPage.verifyUpdateCustomerGroup();
    }

    @Test(dependsOnMethods = {"updateCustomerGroup"})
    public void deleteCustomerGroup(){
        customerGroupsPage.deleteCustomerGroup();
        customerGroupsPage.verifyDeleteCustomerGroup();
    }
    @Test(priority = 8)
    public void restPassword(){
        customerPage.restPassword();
        Assert.assertTrue(customerPage.verifyRestPassword());
    }
    @Test(priority = 9)
    public void addNewAddress(){
        addAddressesPage.addNewAddress();
        Assert.assertTrue(addAddressesPage.addAddressVerify());
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