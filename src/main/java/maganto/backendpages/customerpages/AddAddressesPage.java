package maganto.backendpages.customerpages;


import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class AddAddressesPage {
    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    @FindBy(xpath = "//span[contains(text(),'Add New Address')]")
    WebElement addNewAddressButton;
    @FindBy(id = "_item1street0")
    WebElement streetAddressField;
    @FindBy(id = "_item1city")
    WebElement cityField;

    @FindBy(id = "_item1postcode")
    WebElement zipCodeField;
    @FindBy(id = "_item1telephone")
    WebElement telephoneField;
    @FindBy(xpath = "//div[@id='page:main-container']//button[@onclick='editForm.submit();']//span[text()='Save Customer']")
    WebElement addedAddressSaveButton;
    @FindBy(xpath = "//a[@id='customer_info_tabs_addresses' and @class='tab-item-link'][1]")
    WebElement addresses;
    @FindBy(xpath = "//*[@id=\"_item1country_id\"]/option[226]")
    WebElement country;
    @FindBy(xpath = "//*[@id=\"customerGrid_table\"]/tbody/tr[1]")
    WebElement customer;
    @FindBy(xpath = "//*[@id=\"messages\"]/ul/li/ul/li/span")
    WebElement successMessage;
    public AddAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions = new Actions(driver);
    }
    public void addNewAddress() {
        CustomerDashboardPage customerDashboardPage = new CustomerDashboardPage(driver);
        customerDashboardPage.clickOnManageCustomers();
        testUtility.waitForElementPresent(customer);
        customer.click();
        testUtility.waitForElementPresent(addresses);
        addresses.click();
        testUtility.waitForElementPresent(addNewAddressButton);
        testUtility.javaScriptClick(addNewAddressButton);
        testUtility.waitForElementPresent(streetAddressField);
        streetAddressField.sendKeys(testUtility.generateStreetAddress());
        testUtility.waitForElementPresent(cityField);
        cityField.sendKeys(testUtility.generateCityName());
        testUtility.waitForElementPresent(country);
        country.click();
        testUtility.sleep(1);
        testUtility.waitForElementPresent(zipCodeField);
        zipCodeField.sendKeys(testUtility.generateZipCode());
        testUtility.waitForElementPresent(telephoneField);
        telephoneField.sendKeys(testUtility.generateTelephoneNumber());
        testUtility.sleep(3);
        testUtility.javaScriptClick(addedAddressSaveButton);
    }

    public boolean addAddressVerify() {
        testUtility.waitForElementPresent(successMessage);
        if (successMessage.isDisplayed())
            return true;
        else return false;
    }
}
