package testng;

import maganto.backendpages.BackEndLogin;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestBase;
import maganto.utility.TestResultListener;
import maganto.utility.TestUtility;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
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
    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        utility = new TestUtility(driver);
        context.setAttribute("driver",driver);
        customerDashboardPage = new CustomerDashboardPage(driver);
        customerGroupsPage = new CustomerGroupsPage(driver);
        login = new BackEndLogin(driver);
        login.customerPageLogin();

    }
    @Test(groups = "regression test", description = "Customer Manager can add a new customer ")
    public void addNewCustomer() {
        login.VerifyLoginSuccessfully();
        utility.sleep(3);
        customerPage.addNewCustomerMethod();
        Assert.assertTrue(customerPage.verifyNewCustomerAdded());
      //  Assert.assertTrue(dataAccess.getNewlyAddedCustomer(customerPage.email(), connection));
    }
    @Test(groups = "regression test",description = "Customer Manager can update an existing customer ")
    public void updateCustomer() {
        customerPage.updateCustomer();
        Assert.assertTrue(customerPage.verifyUpdateCustomer());
    }
    @Test(groups = "regression test",description = "Customer Manager can delete an existing customer",dependsOnMethods = "updateCustomer")
    public void deleteExistingCustomer() {
        customerDashboardPage.clickOnManageCustomers();
        customerPage.deleteCustomer();
        Assert.assertTrue(customerPage.verifyDeleteCustomer());
    }


    @Test
    public void addNewCustomerGroups(){
        customerGroupsPage.addNewCustomerGroups();
        customerGroupsPage.verifyNewCustomerGroupAdded();

    }

    @Test(dependsOnMethods ={"addNewCustomerGroups"})
    public void updateCustomerGroup(){
        customerGroupsPage.updateCustomerGroup();
        customerGroupsPage.verifyUpdateCustomerGroup();
    }

    @Test(dependsOnMethods ={"addNewCustomerGroups","updateCustomerGroup"})
    public void deleteCustomerGroup(){
        customerGroupsPage.deleteCustomerGroup();
        customerGroupsPage.verifyDeleteCustomerGroup();
    }


    @AfterClass
    public void tearDown() {
        closeBrowser();
    }

}
