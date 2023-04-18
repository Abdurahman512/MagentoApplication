package regressiontestsuit.testng;

import maganto.backendpages.BackEndLogin;
import maganto.utility.*;
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
            customerGroupsPage = new CustomerGroupsPage(driver);
        }

        @BeforeMethod
        public void backToDashboard() {
            customerDashboardPage.clickOnMagentoLogoBackDashboard();
        }

        @Test(groups = "regression test", description = "Customer Manager can add a new customer ")
        public void addNewCustomer() {
            login.VerifyLoginSuccessfully();
            customerPage.addNewCustomerMethod();
            Assert.assertTrue(customerPage.verifyNewCustomerAdded());

        }

        @Test(groups = "regression test", description = "Customer Manager can add new customer groups.")
        public void addNewCustomerGroups() {
            customerDashboardPage.clickCustomerGroupsLink();
            customerGroupsPage.addNewCustomerGroups();
            customerGroupsPage.verifyNewCustomerGroupAdded();
        }

        @Test(groups = "regression test", description = "Customer Manager can  update existing customer groups.", dependsOnMethods = "addNewCustomerGroups")
        public void updateCustomerGroup() {
            customerDashboardPage.clickCustomerGroupsLink();
            customerGroupsPage.updateCustomerGroup();
            customerGroupsPage.verifyUpdateCustomerGroup();
        }

        @Test(groups = "regression test", description = "assign a customer to group"
        )
        public void assignCustomer() {
            customerPage.AssignCustomer();
            Assert.assertTrue(customerPage.verifyAssigncustomer());
        }

        @Test(groups = "regression test", description = "Customer Manager can Export customer")
        public void ExportCustomers() {
            customerPage.ExportCustomer();
            Assert.assertTrue(customerPage.verifyClickExportButton());
        }


        @Test(groups = "regression test", description = "Customer Manager can filter customers by email", dependsOnMethods = "updateCustomer", priority = 3)
        public void filterCustomersByEmail() {
            customerDashboardPage.filterCustomersByEmail();
            Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByEmail());
        }

        @Test(groups = "regression test", description = "Customer Manager can add a new address for a customer", dependsOnMethods = "addNewCustomer")
        public void addNewAddress() {
            addAddressesPage.addNewAddress();
            Assert.assertTrue(addAddressesPage.addAddressVerify());
        }

        @Test(groups = "regression test", description = "Customer Manager can update an existing customer ", dependsOnMethods = "assignCustomer")
        public void updateCustomer() {
            customerPage.updateCustomer();
            Assert.assertTrue(customerPage.verifyUpdateCustomer());
        }

        @Test(dataProvider = "filterData", groups = "regression test", description = "Customer Manager can filter customers by Country, State, and website. ", dependsOnMethods = "deleteExistingCustomer", priority = 2)
        public void filterCustomerByCountryStateWebsite(String state) {
            customerDashboardPage.filterCustomersByCountryStateWebsite(state);
            Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
        }

        @Test(groups = "regression test", description = "Customer Manager can filter customers by Group", dependsOnMethods = "filterCustomerByCountryStateWebsite")
        public void filterCustomersByGroup() {
            customerDashboardPage.filterCustomersByGroup();
            Assert.assertTrue(customerDashboardPage.verifyCustomerFilteredByGroup());
        }

        @Test(groups = "regression test", description = "Customer Manager can delete existing customer groups", dependsOnMethods = "updateCustomerGroup")
        public void deleteCustomerGroup() {
            customerGroupsPage.deleteCustomerGroup();
            customerGroupsPage.verifyDeleteCustomerGroup();
        }

        @Test(groups = "regression test", description = "Customer Manager can delete an existing customer", dependsOnMethods = "updateCustomer", priority = 1)
        public void deleteExistingCustomer() {
            customerDashboardPage.clickOnManageCustomers();
            customerPage.deleteCustomer();
            Assert.assertTrue(customerPage.verifyDeleteCustomer());
        }

        @Test(groups = "regression test", description = "Customer manager can reset customers password", dependsOnMethods = "updateCustomer", priority = 2)
        public void restPassword() {
            customerPage.restPassword();
            Assert.assertTrue(customerPage.verifyRestPassword());
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
    @DataProvider
    public Object[][] customerGroupInfo() {
        Object[][] data = new Object[][]{
                {new TestDataHolder(utility.generateCityName())}
        };
        return data;
    }

}

