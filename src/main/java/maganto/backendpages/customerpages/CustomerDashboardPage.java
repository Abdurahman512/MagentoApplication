package maganto.backendpages.customerpages;

import maganto.utility.TestUtility;
import maganto.utility.ApplicationConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

public class CustomerDashboardPage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    String config="config.properties";
    String email;


    @FindBy(xpath = "//span[text()='Customers']")
    WebElement customersLink;

    @FindBy(xpath = "//span[text()='Manage Customers']")
    WebElement manageCustomersLink;
    @FindBy(xpath = "//span[text()='Customer Groups']")
    WebElement customerGroupsLink;
    @FindBy(css = "button[title='Search']")
    WebElement searchButton;
    @FindBy(css = "input[name='email']")
    WebElement emailTextBox;
    @FindBy(id = "customerGrid_filter_group")
    WebElement groupFilterBox;
    @FindBy(id = "customerGrid_filter_billing_region")
    WebElement stateFilterBox;
    @FindBy(id = "customerGrid_filter_billing_country_id")
    WebElement countryFilterBox;
    @FindBy(id = "customerGrid_filter_website_id")
    WebElement websiteFilterBox;
    @FindBy(xpath = "//td[contains(text(),\"IstanbulTeam1@gmail.com\")]")
    WebElement customerEmailAddress;
    @FindBy(css = "#customer_info_tabs_addresses")
    WebElement addressesLink;
    @FindBy(xpath = "//*[contains(text(),'No records found.')]")
    WebElement verifyNoRecordsFound;
    @FindBy(xpath = "//td[contains(text(),\"Istanbul Team\")]")
    WebElement verifyCustomerByGroup;


    public CustomerDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions = new Actions(driver);
        email= testUtility.generateEmailAddress();
    }
    public void clickOnManageCustomers(){
        testUtility.waitForElementPresent(customersLink);
        actions.moveToElement(customersLink).perform();
        testUtility.waitForElementPresent(manageCustomersLink);
        manageCustomersLink.click();
    }
    public void filterCustomersByEmail(){
        testUtility.waitForElementPresent(emailTextBox);
        emailTextBox.sendKeys(email);
        System.out.println(email);
        testUtility.waitForElementPresent(searchButton);
        actions.click(searchButton).perform();
        testUtility.sleep(2);
    }
    public boolean verifyCustomerFilteredByEmail(){
        testUtility.waitForElementPresent(verifyNoRecordsFound);
        if(verifyNoRecordsFound.isDisplayed())
            System.out.println(verifyNoRecordsFound);
            return true;
    }

    public void filterCustomersByGroup(){
        testUtility.waitForElementPresent(groupFilterBox);
        Select select=new Select(groupFilterBox);
        select.selectByValue("697");
        testUtility.sleep(1);
        testUtility.waitForElementPresent(searchButton);
        actions.click(searchButton).perform();
        testUtility.sleep(2);
    }
    public boolean verifyCustomerFilteredByGroup(){
        if (verifyCustomerByGroup.isDisplayed())
            return true;
        else if (verifyNoRecordsFound.isDisplayed())
            return true;
        else return false;
    }
    public void filterCustomersByCountryStateWebsite(String state){
        testUtility.waitForElementPresent(countryFilterBox);
        Select select1=new Select(countryFilterBox);
        select1.selectByValue("US");
        testUtility.sleep(1);
        testUtility.waitForElementPresent(websiteFilterBox);
        Select select2=new Select(websiteFilterBox);
        select2.selectByValue("0");
        testUtility.waitForElementPresent(stateFilterBox);
        stateFilterBox.sendKeys(state);
        testUtility.waitForElementPresent(searchButton);
        actions.click(searchButton).perform();
        testUtility.sleep(2);
    }




}
