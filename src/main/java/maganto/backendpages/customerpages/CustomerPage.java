package maganto.backendpages.customerpages;
import maganto.utility.ApplicationConfig;
import maganto.utility.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



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
  @FindBy(xpath = "(//button[@title='Add New Customer'])[1]")
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
    @FindBy(xpath = "//span[text()='Export']")
    WebElement ExportButton;
    @FindBy (name = "customer")
    WebElement checkbox;
    @FindBy(id = "customerGrid_massaction-select")
    WebElement ActionsButton;
    @FindBy(name ="group")
    WebElement Groups;
    @FindBy(xpath="//span[text()='Submit']" )
    WebElement SubmitButton;
    @FindBy(xpath = "//span[text()='Total of 1 record(s) were updated.']")
    WebElement VerifySubmitbuttonClick;

    @FindBy(xpath = "//*[@id=\"customerGrid_table\"]/tbody/tr[1]")
    WebElement customer;

    @FindBy(xpath = "//*[@id=\"account-send-pass\"][1]")
    WebElement generatedPassword;

    @FindBy(xpath = "//*[@id=\"_accountcurrent_password\"]")
    WebElement adminPassword;



    public String addNewCustomerMethod() {
        testUtility.waitForElementPresent(addNewCustomerButton);
        addNewCustomerButton.click();
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
        return email;

    }

    public String email(){
        return email;
    }

    public boolean verifyNewCustomerAdded() {
        testUtility.waitForElementPresent(successMessage);
        if (driver.getPageSource().contains(successMessage.getText()))
        System.out.println("The customer has been saved.");
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
        if (driver.getPageSource().contains(successMessage.getText()))
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

    public void ExportCustomer() {
        testUtility.waitForElementPresent(ExportButton);
        ExportButton.click();

    }
    public  boolean  verifyClickExportButton(){
        testUtility.waitForElementPresent(ExportButton);
        if (ExportButton.isEnabled())
            System.out.println("download symbol down right also appeared");
        return true;
    }
public void AssignCustomer(){
        testUtility.waitForElementPresent(checkbox);
        testUtility.javaScriptClick(checkbox);
        //checkbox.click();
        testUtility.waitForElementPresent(ActionsButton);
        Select select=new Select(ActionsButton);
        select.selectByValue("assign_group");
        ActionsButton.click();
        testUtility.waitForElementPresent(Groups);
        Select group=new Select(Groups);
        group.selectByValue("264");
        Groups.click();

        Groups.click();
        testUtility.waitForElementPresent(SubmitButton);
       SubmitButton.click();
    }
    public boolean verifyAssigncustomer(){
        testUtility.waitForElementPresent(VerifySubmitbuttonClick);
        if (VerifySubmitbuttonClick.isDisplayed())
        System.out.println("Total of 1 record(s) were updated.");
        return true;
    }

    public void restPassword(){
        CustomerDashboardPage customerDashboardPage = new CustomerDashboardPage(driver);
        customerDashboardPage.clickOnManageCustomers();
        testUtility.waitForElementPresent(customer);
        customer.click();
        testUtility.waitForElementPresent(accountInformationLink);
        accountInformationLink.click();
        testUtility.waitForElementPresent(generatedPassword);
        generatedPassword.click();
        testUtility.waitForElementPresent(adminPassword);
        adminPassword.click();
        adminPassword.sendKeys(ApplicationConfig.readFromConfigProperties(config, "password"));
        testUtility.waitForElementPresent(saveCustomerButton);
        testUtility.javaScriptClick(saveCustomerButton);
        testUtility.sleep(1);

    }
    public boolean verifyRestPassword() {
        testUtility.waitForElementPresent(successMessage);
        if(successMessage.isDisplayed())
        return true;
        else return false;
        }
    }
