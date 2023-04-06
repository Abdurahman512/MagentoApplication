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

import java.util.concurrent.Callable;


@Listeners(TestResultListener.class)
public class CustomerModuleTestRunner extends TestBase {
    TestUtility utility;
    BackEndLogin login;
    final static String configFile = "config.properties";
  CustomerPage customerPage;
  CustomerDashboardPage customerDashboardPage;
    @BeforeClass
    public void setUp(ITestContext context) {
        browserSetUp(ApplicationConfig.readFromConfigProperties(configFile, "backendurl"));
        utility = new TestUtility(driver);
        context.setAttribute("driver",driver);
        customerDashboardPage = new CustomerDashboardPage(driver);
        login = new BackEndLogin(driver);
        login.customerPageLogin();
    }
    @Test(groups = "regression test", description = "Customer Manager can add a new customer ")
    public void addNewCustomer() {
        login.VerifyLoginSuccessfully();
        utility.sleep(3);
        customerPage.addNewCustomer();
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

    @Test(groups = "regression test",description ="Customer Maneger can Export customer" )
    public void ExportCustomers() {
        login.VerifyLoginSuccessfully();
        customerPage = new CustomerPage(driver);
        customerPage.ExportCustomer();
        Assert.assertTrue(customerPage.verifyClickExportButton());
    }
    @Test(groups = "filterData",description ="Customer Manager can filter customers by Assign e Customer group" )
    public void assignCustomer() {
        login.VerifyLoginSuccessfully();
        utility.sleep(3);
        customerPage=new CustomerPage(driver);
        customerPage.AssignCustomer();
        Assert.assertTrue(customerPage.verifyAssigncustomer());


    }
    @Test(groups = "regression test",description = "Customer Manager can filter customers by email")
    public void filterCustomersByEmail(){
        customerDashboardPage.filterCustomersByEmail();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByEmail());
    }
    @Test(groups = "regression test",description = "Customer Manager can filter customers by group")
    public void filterCustomersByGroup(){
        customerDashboardPage.filterCustomersByGroup();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
    }
    @Test(dataProvider = "filterData",groups = "regression test",
            description = "Customer Manager can filter by Country,State,Website")
    public void filterCustomerByCountryStateWebsite(String state){
        customerDashboardPage.filterCustomersByCountryStateWebsite(state);
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());

    }



    @AfterClass
    public void tearDown() {
        closeBrowser();
    }


    @DataProvider
    public Object[][] filterData(){
        Object[][] data=new Object[][]{
                {"New York"}
        };
        return data;
    }


}
