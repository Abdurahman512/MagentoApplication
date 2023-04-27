package regressiontestsuit.cucumber;
import io.cucumber.java.en.And;
import maganto.utility.TestBase;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.marketingpages.*;
import maganto.utility.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
      public class MarketingSteps extends TestBase {
        final static String configFile = "config.properties";
        final static String url = ApplicationConfig.readFromConfigProperties(configFile, "backendurl");

        BackEndLogin login;
        CartPriceRulePage cartPriceRulePage;
        MarketingDashboardPage marketingDashboardPage;
        ExcelUtility excelUtility;

        @Before("@MarketingModuleTest")
        public void setup() {

            browserSetUp(url);
            login = new BackEndLogin(driver);
            login.marketingPageLogin();
            cartPriceRulePage = new CartPriceRulePage(driver);
            marketingDashboardPage = new MarketingDashboardPage(driver);
            excelUtility = new ExcelUtility();
        }

        //Marketing Manager can add new Cart Price Rule
        @Given("Marketing manager on the dashboard page and marketing manager click on Promotions link")
        public void marketingManagerOnTheDashboardPageAndMarketingManagerClickOnPromotionsLink() {
            CartPriceRulePage cartPriceRulePage = new CartPriceRulePage(driver);
            cartPriceRulePage.clickShoppingCartPriceRulesLink();
        }

        @When("click on Shopping Cart Price Rules link to fill out {string} {string} {string} and other information information")
        public void clickOnShoppingCartPriceRulesLinkToFillOutAndOtherInformationInformation(String arg0, String arg1, String arg2) {
            CartPriceRulePage cartPriceRulePage = new CartPriceRulePage(driver);
            cartPriceRulePage.addNewShoppingCartPriceRule(arg0, arg1, arg2);
        }
        @Then("a new shopping cart price rule should be added successfully")
        public void aNewShoppingCartPriceRuleShouldBeAddedSuccessfully() {
            CartPriceRulePage cartPriceRulePage = new CartPriceRulePage(driver);
            Assert.assertTrue(cartPriceRulePage.verifyAddNewShoppingCartPriceRuleSuccessfully());
        }
        @When("select the {string} and change the {string}")
        public void selectTheAndChangeThe(String arg0, String arg1) {
            cartPriceRulePage = new CartPriceRulePage(driver);
            cartPriceRulePage.updateCartPriceRule(arg0, arg1);
        }
        @Then("cart price rule should be updated successfully")
        public void cartPriceRuleShouldBeUpdatedSuccessfully() {
            CartPriceRulePage cartPriceRulePage = new CartPriceRulePage(driver);
            Assert.assertTrue(cartPriceRulePage.verifyAddNewShoppingCartPriceRuleSuccessfully());

        }


          @When("click Catalog Price Rules in Promotions,click Add New Rule button and enters information in the fields")
          public void clickCatalogPriceRulesInPromotionsClickAddNewRuleButtonAndEntersInformationInTheFields() {
            marketingDashboardPage.addNewCatalogPriceRule();
          }

          @Then("catalog price rule should be added successfully")
          public void catalogPriceRuleShouldBeAddedSuccessfully() {
              Assert.assertTrue(marketingDashboardPage.catalogPriceRuleShouldBeAddedSuccessfully());
          }

          @When("click Catalog Price Rules in Promotions,finde rule name and edit it and click Save button")
          public void clickCatalogPriceRulesInPromotionsFindeRuleNameAndEditItAndClickSaveButton() {
              marketingDashboardPage.updatedCatalogPriceRule();
          }

          @Then("existing Catalog Price Rule should be updated successfully")
          public void existingCatalogPriceRuleShouldBeUpdatedSuccessfully() {
              Assert.assertTrue(marketingDashboardPage.catalogPriceRuleShouldBeUpdatedSuccessfully());
          }

          @When("click Catalog Price Rules in Promotions, enters ID {string} and  Rule Name {string} and clicks search button")
          public void clickCatalogPriceRulesInPromotionsEntersIDAndRuleNameAndClicksSearchButton(String id, String ruleName) {
            marketingDashboardPage.searchByIdAndRuleName(id,ruleName);
          }

          @Then("the ID and Rule Name should be display")
          public void theIDAndRuleNameShouldBeDisplay() {
            marketingDashboardPage.searchByIdAndRuleNameSuccessfully();
          }




          @After("@MarketingModuleTest")
          public void tearDown(Scenario scenario) {
              if (scenario.isFailed()) {
                  ScreenShotUtility screenShotUtility = new ScreenShotUtility();
                  screenShotUtility.takeScreenshot("image", "failedTest", driver);
              }
              closeBrowser();
          }


      }

