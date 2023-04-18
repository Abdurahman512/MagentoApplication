package maganto.backendpages.customerpages;

import com.github.javafaker.Faker;
import maganto.utility.TestDataHolder;
import maganto.utility.TestUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
public class CustomerGroupsPage {
    WebDriver driver;
    TestUtility utility;
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
    @FindBy(xpath = "//span[contains(text(),'Customer Groups')]")
    WebElement CustomerGroups;
    @FindBy(xpath = "//td[@class='pager']//select")
    WebElement viewSelectDropdown;
    @FindBy(css = "#tax_class_id")
    WebElement taxClassDropDown;
    @FindBy(css = "span#customerGroupGrid-total-count")
    WebElement countElement;
    public CustomerGroupsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utility = new TestUtility(driver);
        actions = new Actions(driver);
    }

    public void clickOnManageCustomers() {
        utility.waitForElementPresent(customersLink);
        actions.moveToElement(customersLink).perform();
        utility.waitForElementPresent(customerGroupsLink);
        customerGroupsLink.click();
    }

    public void addNewCustomerGroups() {
        clickOnManageCustomers();
        utility.sleep(2);
        addNewCustomerGroupButton.click();
        gName = "Arab" + System.currentTimeMillis();
        groupNameField.sendKeys(gName);
        saveCustomerGroupButton.click();
        System.out.println(gName);

    }

    public boolean verifyNewCustomerGroupAdded() {
        utility.waitForElementPresent(successMessage);
        if (driver.getPageSource().contains(successMessage.getText())) ;
        System.out.println("The customer group has been added successfully.");
        return true;
    }
    public void updateCustomerGroup() {
        clickOnManageCustomers();
        Select select = new Select(viewSelectDropdown);
        select.selectByValue("200");
        JavascriptExecutor Scrool = (JavascriptExecutor) driver;
        Scrool.executeScript("window.scrollBy(0,300)", "");
        WebElement groupName = driver.findElement(By.xpath(String.format
                ("//table[@id='customerGroupGrid_table']//tbody//tr//td[2][contains(text(),'%s')]", gName)));
        utility.waitForElementPresent(groupName);
        utility.javaScriptClick(groupName);
        utility.waitForElementPresent(taxClassDropDown);
        taxClassDropDown.click();
        Select select1=new Select(taxClassDropDown);
        select1.selectByIndex(3);
        saveCustomerGroupButton.click();

    }

    public boolean verifyUpdateCustomerGroup() {
        utility.waitForElementPresent(successMessage);
        if (driver.getPageSource().contains(successMessage.getText())) ;
        System.out.println("Update an existing customer group information successfully");
        return true;
    }

    public void deleteCustomerGroup() {
        clickOnManageCustomers();
        Select select = new Select(viewSelectDropdown);
        select.selectByValue("200");
        utility.sleep(5);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement groupName = driver.findElement(By.xpath(String.format
                ("//table[@id='customerGroupGrid_table']//tbody//tr//td[2][contains(text(),'%s')]", gName)));
        js.executeScript("arguments[0].scrollIntoView();",groupName);
        utility.waitForElementPresent(groupName);
        utility.javaScriptClick(groupName);
        utility.waitForElementPresent(deleteCustomerGroupButton);
        deleteCustomerGroupButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean verifyDeleteCustomerGroup() {
        utility.waitForElementPresent(deleteSuccessMessage);
        if (deleteSuccessMessage.isDisplayed())
            System.out.println("The customer has been deleted.");
        return true;
    }

}

