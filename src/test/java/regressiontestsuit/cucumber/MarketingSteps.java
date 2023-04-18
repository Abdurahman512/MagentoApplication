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
      public class       MarketingSteps extends TestBase {
          final static String configFile = "config.properties";
          final static String url = ApplicationConfig.readFromConfigProperties(configFile, "backendurl");

          BackEndLogin login;
          CartPriceRulePage cartPriceRulePage;
          NewsletterTemplatePage newsletterTemplatePage;
          NewsletterPage newsletterPage;
          ExcelUtility excelUtility;
          ReviewsPage reviewsPage;

          @Before("@MarketingModuleTest")
          public void setup() {
              browserSetUp(url);
              login = new BackEndLogin(driver);
              login.marketingPageLogin();
              cartPriceRulePage = new CartPriceRulePage(driver);
              newsletterTemplatePage = new NewsletterTemplatePage(driver);
              excelUtility = new ExcelUtility();
              reviewsPage=new ReviewsPage(driver);
          }
          //marketing manager can add newsletterTemplate and update and delete


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

          @After("@MarketingModuleTest")
          public void tearDown(Scenario scenario) {
              if (scenario.isFailed()) {
                  ScreenShotUtility screenShotUtility = new ScreenShotUtility();
                  screenShotUtility.takeScreenshot("image", "failedTest", driver);
              }
              closeBrowser();
          }


          @Given("Marketing manager on the dashboard page and marketing manager click on Newsletter template")
          public void marketingManagerOnTheDashboardPageAndMarketingManagerClickOnNewsletterTemplate() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.clickOnNewsletterTemplate();
          }

          @When("click on Add new template")
          public void clickOnAddNewTemplate() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.clickOnAddTemplate();

          }

          @And("Fill in Template information fields")
          public void fillInTemplateInformationFields() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.fillInTemplateInformationfield();
          }

          @Then("Save the new template")
          public void saveTheNewTemplate() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.saveTemplateButton();
          }

          @Given("Marketing manager on the newsletter templates page")
          public void marketingManagerOnTheNewsletterTemplatesPage() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.clickOnNewsletterTemplate();
          }

          @When("click on template name field")
          public void clickOnTemplateNameField() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.clickOnTemplateNameField();
          }

          @And("search the existing template and click")
          public void searchTheExistingTemplateAndClick() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.clickOnSearchButton();
          }

          @And("change the subject name and save the template")
          public void changeTheSubjectNameAndSaveTheTemplate() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.changeExistingSubjectNameAndSave();
          }

          @Then("The template successfully updated")
          public void theTemplateSuccessfullyUpdated() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
//
          }

          @Given("search the existing template click the template")
          public void searchTheExistingTemplateClickTheTemplate() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.searchTheExistingTemplate();
          }

          @When("click on the delete template button")
          public void clickOnTheDeleteTemplateButton() {
              NewsletterTemplatePage newsletterTemplatePage = new NewsletterTemplatePage(driver);
              newsletterTemplatePage.clickOnTheDeleteTemplateButton();
          }

          @Then("the template should be successfully deleted")
          public void theTemplateShouldBeSuccessfullyDeleted() {
          }


          @Given("marketing manager on the dashboard page and can click on Newsletter link")
          public void marketingManagerCanClickNewsletterLink() {
              NewsletterPage newsletterPage = new NewsletterPage(driver);
              newsletterPage.clickOnNewsletterLink();
          }

          @When("marketing manager can click on Newsletter Subscribers link")
          public void clickOnNewsletterSubscribersLink() {
              NewsletterPage newsletterPage = new NewsletterPage(driver);
              newsletterPage.clickOnNewsLetterSubscribersLink();
          }

          @Then("marketing manager can view newsletter subscribers successfully")
          public void marketingManagerVerifyViewNewsletter() {
              NewsletterPage newsletterPage = new NewsletterPage(driver);
              Assert.assertTrue(newsletterPage.viewNewsLetterSubscribersSuccessfully());
          }
           //1.Marketing manager can view All Reviews
          @When("click on the CatalogLink select Reviews and Rating and move to Customer Reviews and Select All Views")
          public void clickOnTheCatalogLinkSelectReviewsAndRatingAndMoveToCustomerReviewsAndSelectAllViews() {
              reviewsPage.clickAllReviewsLink();
          }



          @Then("All Reviews page should display with the Reviews InformationMa")
          public void allReviewsPageShouldDisplayWithTheReviewsInformationMa() {
              reviewsPage.verifyViewAllReviews();
          }

          //2.Marketing manager can update Existing Reviews
          @When("select the existing reviews and edit the nickname field")
          public void selectTheExistingReviewsAndEditTheNicknameField() {
              reviewsPage.updateExistingReviews();
          }@Then("existing reviews should  be updated successfully")
          public void existingReviewsShouldBeUpdatedSuccessfully() {
              reviewsPage.verifyUpdateReviews();

          }
          //3. Marketing manager can view Pending Reviews
          @When("select Reviews and Rating and move to Customer Review and select Pending Reviews")
          public void selectReviewsAndRatingAndMoveToCustomerReviewAndSelectPendingReviews() {
              reviewsPage.viewPendingReviews();
          }
          @Then("Pending Reviews page should display with the Review information")
          public void pendingReviewsPageShouldDisplayWithTheReviewInformation() {
              reviewsPage.verifViewPendingViews();
          }



          //4. Marketing manager can update the Existing Pending Reviews

          @When("select the existing pending reviews and edit the summary of reviews filed")
          public void selectTheExistingPendingReviewsAndEditTheSummaryOfReviewsFiled() {
              reviewsPage.updatePendingReviews();
          }

          @Then("existing pending reviews should be updated successfully")
          public void existingPendingReviewsShouldBeUpdatedSuccessfully() {
              reviewsPage.verifyUpdatePendinReviews();
          }


      }

