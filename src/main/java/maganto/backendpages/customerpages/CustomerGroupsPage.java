package maganto.backendpages.customerpages;

import com.github.javafaker.Faker;
import maganto.utility.TestUtility;
import maganto.utility.ApplicationConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class CustomerGroupsPage {
        WebDriver driver;
        TestUtility testUtility;
        Actions actions;
        String config="config.properties";

        Faker faker = new Faker();
        String gName = faker.color().name();

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

        public CustomerGroupsPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
            testUtility = new TestUtility(driver);
            actions = new Actions(driver);
        }

        public void clickOnManageCustomers(){
                testUtility.waitForElementPresent(customersLink);
                actions.moveToElement(customersLink).perform();

                testUtility.waitForElementPresent(customerGroupsLink);
                customerGroupsLink.click();
        }
        public void addNewCustomerGroups(){
            clickOnManageCustomers();
            testUtility.sleep(2);
            addNewCustomerGroupButton.click();
            groupNameField.sendKeys(gName);
            System.out.println(gName);
            saveCustomerGroupButton.click();

        }

        public boolean verifyNewCustomerGroupAdded() {
            testUtility.waitForElementPresent(successMessage);
            if (driver.getPageSource().contains(successMessage.getText())) ;
            System.out.println("The customer group has been added successfully.");
            return true;
        }

        public void clickGroupName(String gName){
            WebElement groupName = driver.findElement(By.xpath("//td[normalize-space(text())='"+gName+"']"));
            groupName.click();
        }

        public void updateCustomerGroup(){
            //addNewCustomerGroups();
            clickOnManageCustomers();
            testUtility.sleep(2);
            clickGroupName(gName);
            //groupName.click();
            groupNameField.clear();
            groupNameField.sendKeys(gName);
            saveCustomerGroupButton.click();

        }

        public boolean verifyUpdateCustomerGroup() {
            testUtility.waitForElementPresent(successMessage);
            if (driver.getPageSource().contains(successMessage.getText())) ;
            System.out.println("Update an existing customer group information successfully");
            return true;
        }
        public void deleteCustomerGroup(){
           // updateCustomerGroup();
            clickOnManageCustomers();
            clickGroupName(gName);
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
