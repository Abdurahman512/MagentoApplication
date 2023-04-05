package maganto.backendpages.customerpages;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CustomerPage{
    WebDriver driver;
    TestUtility testUtility;
    String config = "config.properties";
    String email;


    @FindBy(xpath="//*[contains(text(),'Add New Customer')])[1]")
    WebElement addNewCustomerButton;
    @FindBy(id ="_accountfirstname")
    WebElement firstNameField;
    @FindBy(id ="_accountlastname")
    WebElement lastNameField;
    @FindBy(id ="_accountemail")
    WebElement emailField;
    @FindBy(id ="_accountpassword")
    WebElement passwordField;
    @FindBy(xpath = "//div[@id='anchor-content']//p/button[4]")
    WebElement saveCustomerButton;
    @FindBy(css = ".success-msg>ul>li>span")
    WebElement successMessage;
    @FindBy(id = "customerGrid_massaction-select")
    WebElement actionsDropDown;
    @FindBy(id = "visibility")
    WebElement groupDropDown;
    @FindBy(xpath = "//span[@class=\"field-row\"]//span[text()=\"Submit\"]")
    WebElement submitButton;
    @FindBy(css = ".success-msg")
    WebElement verifyACustomerAssignToGroupSuccessfulSms;
    @FindBy(xpath = "(//button[@title=\"Delete Customer\"])[1]")
    WebElement deleteCustomerButton;
    @FindBy(xpath = "//span[contains(text(),'The customer has been deleted.')]")
    WebElement deleteSuccessMessage;
    @FindBy(xpath = "//span[text()='Reset Filter']")
    WebElement resetFilterButton;
    @FindBy(xpath = "//td[contains(text(),'team33 Monty Monty Purdy team33')]")
    WebElement customerName;
    @FindBy(xpath = "//a[@id='customer_info_tabs_account' and @class='tab-item-link'][1]")
    WebElement accountInformationLink;
    @FindBy(xpath = "(//select[@id=_accountgender])[1]")
    WebElement selectGender;
    @FindBy(css = "input[name='email']")
    WebElement emailFieldBox;
    @FindBy(css = "button[title='Search']")
    WebElement searchButton;
    @FindBy(xpath = "//table[@id=\"customerGrid_table\"]//tr/td[4]")
    WebElement emailAddressAfterSearched;
    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        email = testUtility.generateEmailAddress();
    }
    public void addNewCustomerMethod() {
       testUtility.waitForElementPresent(addNewCustomerButton);
       addNewCustomerButton.click();
        testUtility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(testUtility.generateFirstName());
        testUtility.waitForElementPresent(lastNameField);
        lastNameField.sendKeys(testUtility.generateLastName());
        testUtility.waitForElementPresent(emailField);
        emailField.sendKeys(email);
        System.out.println(email);
        testUtility.waitForElementPresent(passwordField);
        passwordField.sendKeys(ApplicationConfig.readFromConfigProperties(config, "password"));
        testUtility.waitForElementPresent(saveCustomerButton);
        saveCustomerButton.click();

    }

    public String email(){
        return email;
    }


    public boolean verifyNewCustomerAdded() {
        testUtility.waitForElementPresent(successMessage);
        if (driver.getPageSource().contains(successMessage.getText())) ;
        System.out.println("The customer has been added successfully.");
        return true;
    }
    public void updateCustomer() {
        CustomerDashboardPage customerDashboardPage = new CustomerDashboardPage(driver);
        customerDashboardPage.clickOnManageCustomers();
        testUtility.sleep(3);
        testUtility.waitForElementPresent(emailFieldBox);
        emailFieldBox.sendKeys(email);
        testUtility.waitForElementPresent(searchButton);
        searchButton.click();
        testUtility.sleep(3);
        testUtility.waitForElementPresent(emailAddressAfterSearched);
        emailAddressAfterSearched.click();
        testUtility.waitForElementPresent(accountInformationLink);
        accountInformationLink.click();
        testUtility.waitForElementPresent(lastNameField);
        lastNameField.clear();
        lastNameField.sendKeys(testUtility.generateLastName());
        testUtility.waitForElementPresent(saveCustomerButton);
        saveCustomerButton.click();
    }

    public boolean verifyUpdateCustomer() {
        testUtility.waitForElementPresent(successMessage);
        if (driver.getPageSource().contains(successMessage.getText())) ;
        System.out.println("Update an existing customer information successfully");
        return true;
    }
    public void deleteCustomer() {
        testUtility.waitForElementPresent(resetFilterButton);
        resetFilterButton.click();
        WebElement emailLocation = driver.findElement(By.xpath(String.format("//td[contains(text(),'%s')]", email)));
        testUtility.waitForElementPresent(emailLocation);
        testUtility.javaScriptClick(emailLocation);
        testUtility.waitForElementPresent(deleteCustomerButton);
        deleteCustomerButton.click();
        testUtility.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean verifyDeleteCustomer() {
        testUtility.waitForElementPresent(deleteSuccessMessage);
        if (deleteSuccessMessage.isDisplayed())
            System.out.println("The customer has been deleted.");
        return true;
    }

}
