package maganto.backendpages.marketingpages;

import com.github.javafaker.Faker;
import maganto.backendpages.BackEndLogin;
import maganto.backendpages.catalogpages.ProductPage;
import maganto.backendpages.salespages.SalesShipmentsPage;
import maganto.utility.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.List;

public class MarketingDashboardPage {

    @FindBy(xpath = "//span[text()='Promotions']")
    public WebElement promotionsLink;
    @FindBy(xpath = "//span[text()='Catalog Price Rules']")
    public WebElement catalogPriceRulesLink ;
    @FindBy(xpath = "(//span[text()='Add New Rule'])[1]")
    public WebElement addNewRoleBtn;
    @FindBy(id = "rule_name")
    public WebElement ruleNameField;
    @FindBy(id = "promo_catalog_grid_filter_name")
    public WebElement searchRuleNameField;
    @FindBy(xpath = "//select[@name='limit']")
    public WebElement viewSelectDropdown;
    @FindBy(id = "rule_description")
    public WebElement descriptionField;
    @FindBy(xpath = "//input[@name='from_date']")
    public WebElement fromDateField;
    @FindBy(xpath = "//input[@name='to_date']")
    public WebElement toDateField;
    @FindBy(id = "rule_sort_order")
    public WebElement priorityField;
    @FindBy(xpath = "(//span[text()='Save'])[1]")
    public WebElement saveButton;
    @FindBy(id = "rule_is_active")
    public WebElement status;
    @FindBy(id ="rule_website_ids" )
    public WebElement websites;
    @FindBy(id="rule_customer_group_ids")
    public WebElement customerGroups;
    @FindBy(id = "rule_discount_amount")
    public WebElement discountAmountField;
    @FindBy(xpath = "//span[text()='The rule has been saved.']")
    public WebElement successMessag;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement ruleNameSearchField;
    @FindBy(xpath = "//span[text()='Search']")
    public WebElement searchBtn;
    @FindBy(xpath = "//input[@name='rule_id']")
    public WebElement idField;
    @FindBy(xpath = "//span[.='There are rules that have been changed but were not applied. Please, click Apply Rules in order to see immediate effect in the catalog.']")
    public WebElement successMessag1;

    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    Faker faker;
    ProductPage productPage;
    BackEndLogin backEndLogin;

    String ruleName;

    String text;

    public MarketingDashboardPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions = new Actions(driver);
        productPage = new ProductPage(driver);
        faker = new Faker();
        backEndLogin = new BackEndLogin(driver);

    }

    public void addNewCatalogPriceRule() {

        testUtility.waitForElementPresent(promotionsLink);
        actions.moveToElement(promotionsLink).click().perform();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(catalogPriceRulesLink);
        actions.moveToElement(catalogPriceRulesLink).click().perform();
        testUtility.waitForElementPresent(addNewRoleBtn);
        addNewRoleBtn.click();
        testUtility.waitForElementPresent(ruleNameField);

        ruleName = testUtility.generateCityName();
        ruleNameField.sendKeys(ruleName);
        testUtility.waitForElementPresent(descriptionField);
        descriptionField.sendKeys(productPage.generateDescription());
        Select dropDownStatus = new Select(status);
        dropDownStatus.selectByVisibleText("Active");
        Select dropDownWebsites = new Select(websites);
        dropDownWebsites.selectByIndex(1);
        Select dropDownGroups = new Select(customerGroups);
        dropDownGroups.selectByValue("1");
        testUtility.waitForElementPresent(fromDateField);
        fromDateField.sendKeys(faker.date().toString());
        testUtility.waitForElementPresent(toDateField);
        toDateField.sendKeys(faker.date().toString());
        testUtility.waitForElementPresent(priorityField);
        priorityField.sendKeys(faker.number().toString());
        testUtility.waitForElementPresent(saveButton);
        actions.moveToElement(saveButton).build().perform();
        saveButton.click();
        discountAmountField.sendKeys("50%");
        saveButton.click();
        testUtility.sleep(2);
    }
    public boolean catalogPriceRuleShouldBeAddedSuccessfully() {
        testUtility.waitForElementPresent(successMessag);
        if (driver.getPageSource().contains(successMessag.getText())) {
            System.out.println("Marketing Manager can Add New Shopping Cart Test is Passed!!!");
            return true;
        } else {
            System.out.println("Marketing Manager can Add New Shopping Cart  Test is Failed!!!");
            return false;
        }
    }

    public String getRuleText() {

        String locator = "//table[@id='promo_catalog_grid_table']//tbody/tr//td[2]";

            List<WebElement> rules = driver.findElements(By.xpath(locator));

        for (WebElement rule : rules) {

             text = rule.getText();
        }
            return text;
        }

    public void updatedCatalogPriceRule(){

        testUtility.waitForElementPresent(promotionsLink);
        actions.moveToElement(promotionsLink).click().perform();
        testUtility.waitForElementPresent(catalogPriceRulesLink);
        actions.moveToElement(catalogPriceRulesLink).click().perform();
        Select select = new Select(viewSelectDropdown);
        select.selectByValue("200");
        // "//table[@id='promo_catalog_grid_table']//tr//td[normalize-space()='Alannaport']"
        WebElement name = driver.findElement(By.xpath(String.format(
                "//table[@id='promo_catalog_grid_table']//tr//td[normalize-space()='%s']",getRuleText())));

        testUtility.waitForElementPresent(name);
        actions.moveToElement(name);
        testUtility.javaScriptClick(name);
        testUtility.sleep(1);
        ruleNameField.clear();
        ruleNameField.sendKeys(faker.book().title());
        testUtility.sleep(1);
        saveButton.click();

    }
    public boolean catalogPriceRuleShouldBeUpdatedSuccessfully() {
        testUtility.waitForElementPresent(successMessag);
        if (driver.getPageSource().contains(successMessag.getText())) {
            System.out.println("Marketing Manager can Add New Shopping Cart Test is Passed!!!");
            return true;
        } else {
            System.out.println("Marketing Manager can Add New Shopping Cart  Test is Failed!!!");
            return false;
        }
  }
  public void searchByIdAndRuleName(String id, String ruleName){
      testUtility.waitForElementPresent(promotionsLink);
      actions.moveToElement(promotionsLink).click().perform();
      testUtility.waitForElementPresent(catalogPriceRulesLink);
      actions.moveToElement(catalogPriceRulesLink).click().perform();

      testUtility.waitForElementPresent(idField);
      idField.sendKeys(id);
      testUtility.waitForElementPresent(searchRuleNameField);
      searchRuleNameField.sendKeys(ruleName);
      testUtility.waitForElementPresent(searchBtn);
      searchBtn.click();


  }
    public boolean searchByIdAndRuleNameSuccessfully() {
        testUtility.waitForElementPresent(successMessag1);
        if (driver.getPageSource().contains(successMessag1.getText())) {
            System.out.println("There are rules that have been changed but were not applied. Please," +
                    " click Apply Rules in order to see immediate effect in the catalog.");
            return true;
        } else {
            System.out.println("Marketing Manager can search by ID and RuleName Test is Failed!!!");
            return false;
        }
    }

}
