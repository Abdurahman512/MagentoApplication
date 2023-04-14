package maganto.backendpages.customerpages;

import com.github.javafaker.Faker;
import maganto.utility.TestUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerGroupsPage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    String config="config.properties";

    Faker faker = new Faker();
    String gName;

    @FindBy(xpath = "(//span[.='Customers'])[1]")
    public WebElement customersLink;

    @FindBy(xpath = "//a[.='Customer Groups']")
    public WebElement customerGroupsLink;

    @FindBy(xpath = "(//span[.='Add New Customer Group'])[1]")
    public WebElement addNewCustomerGroupButton;

    @FindBy(id = "customer_group_code")
    public WebElement groupNameField;

    @FindBy(xpath = "(//span[.='Save Customer Group'])[1]")
    public WebElement saveCustomerGroupButton;

    @FindBy(xpath = "//span[.='The customer group has been saved.']")
    public WebElement successMessage;

    @FindBy(xpath = "//span[.='The customer group has been deleted.']")
    public WebElement deleteSuccessMessage;

    @FindBy(xpath = ("(//span[.='Delete Customer Group'])[1]"))
    public WebElement deleteCustomerGroupButton;

    @FindBy(css = "#customerGroupGrid_filter_type")
    WebElement groupNameField1;

    @FindBy(xpath = "//button[@class='scalable task']")
    WebElement searchBtn;

    @FindBy(xpath = "//table[@id='customerGroupGrid_table']//tbody/tr[1]/td[2]")
    WebElement addedCustomerGroup;

    @FindBy(xpath = "//td[@class='pager']//select")
    WebElement viewSelectDropdown;

    public CustomerGroupsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions = new Actions(driver);
    }

    public void clickOnManageCustomers() {
        testUtility.waitForElementPresent(customersLink);
        actions.moveToElement(customersLink).perform();
        testUtility.waitForElementPresent(customerGroupsLink);
        customerGroupsLink.click();
    }

    public String addNewCustomerGroups() {
        clickOnManageCustomers();
        testUtility.sleep(2);
        addNewCustomerGroupButton.click();
        gName = faker.color().name() + System.currentTimeMillis();
        groupNameField.sendKeys(gName);
        saveCustomerGroupButton.click();

        return gName;
    }

    public boolean verifyNewCustomerGroupAdded() {
        testUtility.waitForElementPresent(successMessage);
        if (driver.getPageSource().contains(successMessage.getText())) ;
        System.out.println("The customer group has been added successfully.");
        return true;
    }

    public void javaScriptClick(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", element);
    }

    public void updateCustomerGroup() {
        clickOnManageCustomers();
        Select select = new Select(viewSelectDropdown);
        select.selectByValue("200");

        WebElement groupName = driver.findElement(By.xpath(String.format
                ("//table[@id='customerGroupGrid_table']//tbody//tr//td[2][contains(text(),'%s')]", gName)));

        testUtility.sleep(2);
        testUtility.waitForElementPresent(groupName);
        testUtility.javaScriptClick(groupName);
        groupNameField.clear();

        String number = String.valueOf((Math.random() * 10) + 1);
        groupNameField.sendKeys("Abdurahman" + number);
        System.out.println(gName);
        saveCustomerGroupButton.click();

    }

    public boolean verifyUpdateCustomerGroup() {
        testUtility.waitForElementPresent(successMessage);
        if (driver.getPageSource().contains(successMessage.getText())) ;
        System.out.println("Update an existing customer group information successfully");
        return true;
    }

    public void deleteCustomerGroup() {

        clickOnManageCustomers();
        Select select = new Select(viewSelectDropdown);
        select.selectByValue("200");
        testUtility.sleep(2);
        WebElement groupName = driver.findElement(By.xpath(String.format
                ("//table[@id='customerGroupGrid_table']//tbody//tr//td[2][contains(text(),'%s')]", gName)));
        testUtility.waitForElementPresent(groupName);
        javaScriptClick(groupName);
        deleteCustomerGroupButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean verifyDeleteCustomerGroup() {
        testUtility.waitForElementPresent(deleteSuccessMessage);
        if (deleteSuccessMessage.isDisplayed())
            System.out.println("The customer has been deleted.");
        return true;
    }


}