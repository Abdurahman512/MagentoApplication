package maganto.backendpages.marketingpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CartPriceRulePage {

    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    @FindBy(xpath = "//span[text()='Promotions']")
    WebElement promotionsLink;
    @FindBy(xpath = "//span[text()='Shopping Cart Price Rules']")
    WebElement shoppingCartPriceRulesLink;
    @FindBy(xpath = "(//span[text()='Add New Rule'])[1]")
    WebElement addNewRuleButton;
    @FindBy(xpath = "//input[@id='rule_name']")
    WebElement ruleNameField;
    @FindBy(xpath = "//input[@id='rule_name']")
    WebElement descriptionField;
    @FindBy(id = "rule_is_active")
    WebElement status;
    @FindBy(id ="rule_website_ids" )
    WebElement websites;
    @FindBy(id="rule_customer_group_ids")
    WebElement customerGroups;
    @FindBy(id = "rule_coupon_type")
    WebElement coupon;
    @FindBy(css = "#rule_sort_order")
    WebElement priorityField;
    @FindBy(xpath = "(//span[text()='Save'])[1]")
    WebElement saveButton;
    @FindBy(xpath = "//span[text()='The rule has been saved.']")
    WebElement successMessages;
    @FindBy(xpath = "//input[@id='promo_quote_grid_filter_name']")
    WebElement CartRuleNameField;

    @FindBy(xpath = "//*[contains(text(),'Search')]")
    WebElement cartRuleSearchButton;

    public CartPriceRulePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions=new Actions(driver);
    }
    public void clickShoppingCartPriceRulesLink() {
        testUtility.waitForElementPresent(promotionsLink);
        actions.moveToElement(promotionsLink).click().perform();
        testUtility.waitForElementPresent(shoppingCartPriceRulesLink);
        actions.moveToElement(shoppingCartPriceRulesLink).click().perform();

    }
    public void addNewShoppingCartPriceRule(String RuleName,String description ,String Priority ){

        testUtility.waitForElementPresent(addNewRuleButton);
        addNewRuleButton.click();
        testUtility.waitForElementPresent(ruleNameField);
        ruleNameField.sendKeys(RuleName);
        testUtility.waitForElementPresent(descriptionField);
        descriptionField.sendKeys(description);
        Select dropDownStatus = new Select(status);
        dropDownStatus.selectByVisibleText("Active");
        Select dropDownWebsites=new Select(websites);
        dropDownWebsites.selectByIndex(1);
        Select dropDownGroups=new Select(customerGroups);
        dropDownGroups.selectByValue("1");
        Select dropDownCoupon=new Select(coupon);
        dropDownCoupon.selectByValue("1");
        testUtility.waitForElementPresent(priorityField);
        priorityField.sendKeys(Priority);
        testUtility.waitForElementPresent(saveButton);
        actions.moveToElement(saveButton).build().perform();
        saveButton.click();
    }
    public boolean verifyAddNewShoppingCartPriceRuleSuccessfully() {
        testUtility.waitForElementPresent(successMessages);
        if (driver.getPageSource().contains(successMessages.getText())) {
            System.out.println("Marketing Manager can Add New Shopping Cart Test is Passed!!!");
            return true;
        } else {
            System.out.println("Marketing Manager can Add New Shopping Cart  Test is Failed!!!");
            return false;
        }
    }

    public void updateCartPriceRule(String RuleName,String Description){
        testUtility.waitForElementPresent(CartRuleNameField);
        CartRuleNameField.click();
        CartRuleNameField.clear();
        testUtility.waitForElementPresent(cartRuleSearchButton);
        cartRuleSearchButton.click();
        CartRuleNameField.click();
        CartRuleNameField.sendKeys(RuleName);
        testUtility.waitForElementPresent(cartRuleSearchButton);
        cartRuleSearchButton.click();
        WebElement selectedCartRuleNamesFirstColumn=driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", RuleName)));
        testUtility.waitForElementPresent(selectedCartRuleNamesFirstColumn);
        selectedCartRuleNamesFirstColumn.click();
        testUtility.waitForElementPresent(descriptionField);
        testUtility.sleep(3);
        descriptionField.click();
        descriptionField.clear();
        descriptionField.sendKeys(Description);
        testUtility.waitForElementPresent(saveButton);
        saveButton.click();
    }

    public boolean updateCartPriceRuleSuccessfully(){
        testUtility.waitForElementPresent(successMessages);
        if (driver.getPageSource().contains(successMessages.getText())) {
            System.out.println("Marketing Manager can update Shopping Cart Price Rule Test is Passed!!!");
            return true;
        } else {
            System.out.println("Marketing Manager can update Shopping Cart Price Rule Test is Failed!!!");
            return false;
        }

    }

}
