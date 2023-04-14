package regressiontestsuit.cucumber;
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
        ExcelUtility excelUtility;

        ReviewsPage reviewsPage;



        @Before("@MarketingModuleTest")
        public void setup() {
            browserSetUp(url);
            login = new BackEndLogin(driver);
            login.marketingPageLogin();
            cartPriceRulePage = new CartPriceRulePage(driver);
            excelUtility = new ExcelUtility();
            reviewsPage=new ReviewsPage(driver);

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

        //1,Marketing manager can view All Reviews and Verification
          @When("click on the CatalogLink select Reviews and Rating and move to Customer Reviews and Select All Views")
          public void click_on_the_catalog_link_select_reviews_and_rating_and_move_to_customer_reviews_and_select_all_views() {
            reviewsPage.clickAllReviewsLink();

          }
          @Then("All Reviews page should display with the Reviews InformationMa")
          public void all_reviews_page_should_display_with_the_reviews_information_ma() {
            reviewsPage.verifyViewAllReviews();

          }
          //2.Marketing manager can edit existing reviews

          @When("select the existing reviews and edit the nickname field")
          public void selectTheExistingReviewsAndEditTheNicknameField() {
            reviewsPage.updateExistingReviews();

          }

          @Then("existing reviews should  be updated successfully")
          public void existingReviewsShouldBeUpdatedSuccessfully() {
            reviewsPage.verifyUpdateReviews();
          }


          //3.Marketing Manager can view Pending Reviews and Verification
          @When("select Reviews and Rating and move to Customer Review and select Pending Reviews")
          public void selectReviewsAndRatingAndMoveToCustomerReviewAndSelectPendingReviews() {
              reviewsPage.viewPendingReviews();
          }

          @Then("Pending Reviews page should display with the Review information")
          public void pendingReviewsPageShouldDisplayWithTheReviewInformation() {
              reviewsPage.verifViewPendingViews();
          }

          //4.Marketing Manager can edit the existing Pending Reviews
          @Then("existing pending reviews should be updated successfully")
          public void existingPendingReviewsShouldBeUpdatedSuccessfully() {
            reviewsPage.updatePendingReviews();
          }

          @When("select the existing pending reviews and edit the summary of reviews filed")
          public void selectTheExistingPendingReviewsAndEditTheSummaryOfReviewsFiled() {
            reviewsPage.verifyUpdatePendinReviews();
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

