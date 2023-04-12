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
    }

    @Test(groups = "regression test", description = "Customer Manager can add a new customer ")
    public void addNewCustomer() {
        login.VerifyLoginSuccessfully();
        customerPage.addNewCustomerMethod();
        Assert.assertTrue(customerPage.verifyNewCustomerAdded());

    }

    @Test(groups = "regression test", description = "Customer Manager can update an existing customer ", dependsOnMethods = "addNewCustomer")
    public void updateCustomer() {
        customerPage.updateCustomer();
        Assert.assertTrue(customerPage.verifyUpdateCustomer());
    }

    @Test(groups = "regression test", description = "Customer Manager can delete an existing customer", dependsOnMethods = "addNewCustomer")
    public void deleteExistingCustomer() {
        customerDashboardPage.clickOnManageCustomers();
        customerPage.deleteCustomer();
        Assert.assertTrue(customerPage.verifyDeleteCustomer());
    }

    @Test(groups = "regression test", description = "Customer Manager can filter customers by email")
    public void filterCustomersByEmail() {
        customerDashboardPage.filterCustomersByEmail();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByEmail());
    }

    @Test(groups = "regression test", description = "Customer Manager can filter customers by group")
    public void filterCustomersByGroup() {
        customerDashboardPage.filterCustomersByGroup();
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
    }

    @Test(dataProvider = "filterData", groups = "regression test",
            description = "Customer Manager can filter by Country,State,Website")
    public void filterCustomerByCountryStateWebsite(String state) {
        customerDashboardPage.filterCustomersByCountryStateWebsite(state);
        Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
    }

    @Test
    public void RestPassword() {
        customerPage.restPassword();
        Assert.assertTrue(customerPage.verifyRestPassword());
    }

    @Test
    public void addNewAddress() {
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