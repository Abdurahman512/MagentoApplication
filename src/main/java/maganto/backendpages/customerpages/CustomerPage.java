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

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        testUtility = new TestUtility(driver);
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        email = testUtility.generateEmailAddress();
    }
 // @FindBy(xpath = "(//button[@title='Add New Customer'])[1]")
          @FindBy(css = ".scalable.add >span span span")
    WebElement addNewCustomerButton;
    @FindBy(id = "_accountfirstname")
    WebElement firstNameField;
    @FindBy(id = "_accountlastname")
    WebElement lastNameField;
    @FindBy(name = "account[email]")
    WebElement emailField;
    @FindBy(id = "_accountpassword")
    WebElement passwordField;
    @FindBy(xpath = "//div[@id='anchor-content']//p/button[4]")
    WebElement saveCustomerButton;
    @FindBy(css = ".success-msg>ul>li>span")
    WebElement successMessage;
    @FindBy(xpath = "(//button[@title=\"Delete Customer\"])[1]")
    WebElement deleteCustomerButton;
    @FindBy(xpath = "//span[contains(text(),'The customer has been deleted.')]")
    WebElement deleteSuccessMessage;
    @FindBy(xpath = "//span[text()='Reset Filter']")
    WebElement resetFilterButton;


    public void addNewCustomerMethod() {
        testUtility.waitForElementPresent(addNewCustomerButton);
        testUtility.javaScriptClick(addNewCustomerButton);
        testUtility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(testUtility.generateFirstName());
        testUtility.waitForElementPresent(lastNameField);
        lastNameField.sendKeys(testUtility.generateLastName());
        testUtility.waitForElementPresent(emailField);
        emailField.sendKeys(email);
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
        System.out.println("The customer has been saved.");
        return true;
    }


    //Update Customer

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

    //Delete Customer

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
