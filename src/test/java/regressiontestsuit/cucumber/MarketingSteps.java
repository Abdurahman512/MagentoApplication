package regressiontestsuit.cucumber;
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
public class MarketingSteps extends TestBase{
    final static String configFile = "config.properties";
    final static String url = ApplicationConfig.readFromConfigProperties(configFile, "backendurl");
    BackEndLogin login;
    CartPriceRulePage cartPriceRulePage;
    ExcelUtility excelUtility;
    @Before("@MarketingModuleTest")
    public void setup() {
        browserSetUp(url);
        login = new BackEndLogin(driver);
        login.marketingPageLogin();
        excelUtility = new ExcelUtility();

    }
    //Marketing Manager can add new Cart Price Rule
    @Given("Marketing manager on the dashboard page and marketing manager click on Promotions link")
    public void marketingManagerOnTheDashboardPageAndMarketingManagerClickOnPromotionsLink() {
        CartPriceRulePage cartPriceRulePage=new CartPriceRulePage(driver);
        cartPriceRulePage.clickShoppingCartPriceRulesLink();
    }

    @When("click on Shopping Cart Price Rules link to fill out {string} {string} {string} and other information information")
    public void clickOnShoppingCartPriceRulesLinkToFillOutAndOtherInformationInformation(String arg0, String arg1, String arg2) {
        CartPriceRulePage cartPriceRulePage=new CartPriceRulePage(driver);
        cartPriceRulePage.addNewShoppingCartPriceRule(arg0,arg1,arg2);

    }

    @Then("a new shopping cart price rule should be added successfully")
    public void aNewShoppingCartPriceRuleShouldBeAddedSuccessfully() {
        CartPriceRulePage cartPriceRulePage=new CartPriceRulePage(driver);
        Assert.assertTrue(cartPriceRulePage.verifyAddNewShoppingCartPriceRuleSuccessfully());
    }

    //updatecartpricerule

    @When("select the {string} and change the {string}")
    public void selectTheAndChangeThe(String arg0, String arg1) {
        cartPriceRulePage=new CartPriceRulePage(driver);
        cartPriceRulePage.updateCartPriceRule(arg0,arg1);

    }

    @Then("cart price rule should be updated successfully")
    public void cartPriceRuleShouldBeUpdatedSuccessfully() {
        CartPriceRulePage cartPriceRulePage= new CartPriceRulePage(driver);
        Assert.assertTrue(cartPriceRulePage.updateCartPriceRuleSuccessfully());
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
