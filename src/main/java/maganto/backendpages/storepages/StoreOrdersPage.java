package maganto.backendpages.storepages;
import maganto.backendpages.customerpages.CustomerPage;
import maganto.utility.TestUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class StoreOrdersPage {
    WebDriver driver;
    TestUtility utility;
    Actions actions;
    StoreDashboardPage storeDashboardPage;
    CustomerPage customerPage;

    public StoreOrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
        storeDashboardPage=new StoreDashboardPage(driver);
        customerPage=new CustomerPage(driver);

    }
   //create Order
    @FindBy(xpath = "//div[@id=\"page:main-container\"]//span[contains(text(),\"Create New Order\")]")
    WebElement createNewOrderLink;
    @FindBy(id = "sales_order_create_customer_grid_filter_email")
    WebElement emailField;
    @FindBy(xpath = "//button[@id=\"id_b695e7c865ad3ba1e66c96d90b9fe2b8\"]//span[contains(text(),\"Search\")]")
    WebElement searchButton;
    @FindBy(xpath = "(//span[contains(text(),\"Create New Customer\")])[1]")
    WebElement createNewCustomer;
    @FindBy(xpath = "//input[@id=\"email\"]")
    WebElement emailFieldFromAccount;
    @FindBy(xpath = "//table[@id=\"sales_order_create_customer_grid_table\"]/tbody/tr[1]/td[1]")
    WebElement selectedCustomer;
    @FindBy(xpath = "(//label[contains(text(),\"Demo Store View\")])[1]")
    WebElement selectedStore;
    @FindBy(xpath = "//span[contains(text(),\"Add Products\")]")
    WebElement addProductsButton;
    @FindBy(xpath = "//input[@id=\"sales_order_create_search_grid_filter_name\"]")
    WebElement productNameField;
    @FindBy(xpath = "//button[@id=\"id_fa9bb3aa6d28ec88db48d98da0625215\"]//span[contains(text(),\"Search\")]")
    WebElement searchButton1;
    @FindBy(xpath = "//table[@id=\"sales_order_create_search_grid_table\"]/tbody/tr[2]/td[2]")
    WebElement selectedProduct;
    @FindBy(xpath = "//span[contains(text(),\"Add Selected Product(s) to Order\")]")
   WebElement addSelectedProductLink;
    @FindBy(xpath = "//table[@class=\"data order-tables\"]/tbody/tr[1]/td[4]/input")
    WebElement productQtyField;
    @FindBy(xpath = "//span[contains(text(),\"Update Items and Qty's\")]")
    WebElement updateQtyButton;
    @FindBy(xpath = "//input[@id=\"order-billing_address_firstname\"]")
    WebElement billingFirstNameField;
    @FindBy(xpath = "//input[@id=\"order-billing_address_lastname\"]")
    WebElement billingLastNameField;
    @FindBy(xpath = "//input[@id=\"order-billing_address_street0\"]")
    WebElement streetField;
    @FindBy(xpath = "//input[@id=\"order-billing_address_city\"]")
    WebElement cityField;
    @FindBy(xpath = "//select[@id=\"order-billing_address_country_id\"]")
    WebElement countryDropDown;
    @FindBy(xpath = "//select[@id=\"order-billing_address_region_id\"]")
    WebElement stateDropDown;
    @FindBy(xpath = "//input[@id=\"order-billing_address_postcode\"]")
    WebElement zipCodeField;
    @FindBy(xpath = "//input[@id=\"order-billing_address_telephone\"]")
    WebElement telephoneField;
    @FindBy(xpath = "//input[@id=\"order-billing_address_save_in_address_book\"]")
    WebElement saveInAddressBookCheckBox;
    @FindBy(xpath = "//input[@id=\"p_method_checkmo\"]")
    WebElement checkMoneyOrderCheckBox;
    @FindBy(xpath = "(//span[contains(text(),\"Submit Order\")])[2]")
    WebElement submitOrderButton;
    //update Order
    @FindBy(xpath = "(//span[contains(text(),\"Edit\")])[1]")
    WebElement editIcon;
    //cancel order
    @FindBy(xpath = "(//span[contains(text(),\"Cancel\")])[2]")
    WebElement cancelButton;
    @FindBy(xpath = "//span[contains(text(),\"The order has been cancelled.\")]")
    WebElement deleteSuccessMassage;
    public void createNewOrder(){
        storeDashboardPage.clickOnOrdersLink();
        utility.waitForElementPresent(createNewOrderLink);
        actions.moveToElement(createNewOrderLink).click().build().perform();
        utility.waitForElementPresent(selectedCustomer);
        selectedCustomer.click();
        utility.waitForElementPresent(selectedStore);
        selectedStore.click();
        utility.waitForElementPresent(addProductsButton);
        utility.javaScriptClick(addProductsButton);
       // utility.waitForElementPresent(productNameField);
       // productNameField.sendKeys("Kiwi ");
       // utility.waitForElementPresent(searchButton1);
       // searchButton1.click();
        utility.waitForElementPresent(selectedProduct);
        actions.moveToElement(selectedProduct).click().build().perform();
        utility.waitForElementPresent(addSelectedProductLink);
        actions.moveToElement(addSelectedProductLink).click().build().perform();
        utility.waitForElementPresent(productQtyField);
        actions.moveToElement(productQtyField).click().build().perform();
        productQtyField.clear();
        productQtyField.sendKeys("45");
        utility.waitForElementPresent(updateQtyButton);
        actions.moveToElement(updateQtyButton).click().build().perform();
        utility.waitForElementPresent(billingFirstNameField);
        utility.javaScriptClick(billingFirstNameField);
        billingFirstNameField.sendKeys(utility.generateFirstName());
        utility.waitForElementPresent(billingLastNameField);
        billingLastNameField.sendKeys(utility.generateLastName());
        utility.waitForElementPresent(streetField);
        streetField.sendKeys(utility.generateStreetAddress());
        utility.waitForElementPresent(cityField);
        cityField.sendKeys(utility.generateCityName());
        utility.waitForElementPresent(countryDropDown);
        Select select=new Select(countryDropDown);
        select.selectByVisibleText("Argentina");
        utility.waitForElementPresent(zipCodeField);
        zipCodeField.sendKeys(utility.generateZipCode());
        utility.waitForElementPresent(telephoneField);
        telephoneField.sendKeys(utility.generateTelephoneNumber());
        utility.waitForElementPresent(saveInAddressBookCheckBox);
        saveInAddressBookCheckBox.click();
        utility.waitForElementPresent(checkMoneyOrderCheckBox);
        checkMoneyOrderCheckBox.click();
        utility.waitForElementPresent(submitOrderButton);
        submitOrderButton.click();
    }
    public void createNewOrderMethod(){
        storeDashboardPage.clickOnOrdersLink();
        utility.waitForElementPresent(createNewOrderLink);
        actions.moveToElement(createNewOrderLink).click().build().perform();
        utility.waitForElementPresent(createNewCustomer);
        utility.javaScriptClick(createNewCustomer);
        utility.waitForElementPresent(selectedStore);
        selectedStore.click();
        utility.waitForElementPresent(addProductsButton);
        utility.javaScriptClick(addProductsButton);
        utility.waitForElementPresent(selectedProduct);
        actions.moveToElement(selectedProduct).click().build().perform();
        utility.waitForElementPresent(addSelectedProductLink);
        actions.moveToElement(addSelectedProductLink).click().build().perform();
        utility.waitForElementPresent(productQtyField);
        actions.moveToElement(productQtyField).click().build().perform();
        productQtyField.clear();
        productQtyField.sendKeys("45");
        utility.waitForElementPresent(updateQtyButton);
        actions.moveToElement(updateQtyButton).click().build().perform();
        utility.waitForElementPresent(emailFieldFromAccount);
        emailFieldFromAccount.sendKeys(utility.generateEmailAddress());
        utility.waitForElementPresent(billingFirstNameField);
        utility.javaScriptClick(billingFirstNameField);
        billingFirstNameField.sendKeys(utility.generateFirstName());
        utility.waitForElementPresent(billingLastNameField);
        billingLastNameField.sendKeys(utility.generateLastName());
        utility.waitForElementPresent(streetField);
        streetField.sendKeys(utility.generateStreetAddress());
        utility.waitForElementPresent(cityField);
        cityField.sendKeys(utility.generateCityName());
        utility.waitForElementPresent(countryDropDown);
        Select select=new Select(countryDropDown);
        select.selectByVisibleText("Argentina");
        utility.waitForElementPresent(zipCodeField);
        zipCodeField.sendKeys(utility.generateZipCode());
        utility.waitForElementPresent(telephoneField);
        telephoneField.sendKeys(utility.generateTelephoneNumber());
        utility.waitForElementPresent(saveInAddressBookCheckBox);
        saveInAddressBookCheckBox.click();
        utility.waitForElementPresent(checkMoneyOrderCheckBox);
        checkMoneyOrderCheckBox.click();
        utility.waitForElementPresent(submitOrderButton);
        submitOrderButton.click();
    }
    public void updateOrder(){
        utility.waitForElementPresent(editIcon);
        actions.moveToElement(editIcon).click().build().perform();
        Alert alt = driver.switchTo().alert();
        alt.accept();
        utility.waitForElementPresent(productQtyField);
        productQtyField.clear();
        productQtyField.sendKeys("50");
        utility.waitForElementPresent(billingFirstNameField);
        utility.javaScriptClick(billingFirstNameField);
        billingFirstNameField.sendKeys(utility.generateFirstName());
        utility.waitForElementPresent(billingLastNameField);
        billingLastNameField.sendKeys(utility.generateLastName());
        utility.waitForElementPresent(streetField);
        streetField.sendKeys(utility.generateStreetAddress());
        utility.waitForElementPresent(submitOrderButton);
        actions.moveToElement(submitOrderButton).click().build().perform();
    }
    public void cancelOrder(){
        utility.waitForElementPresent(cancelButton);
        cancelButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public boolean deleteOrderSuccessfully(){
        if(deleteSuccessMassage.isDisplayed()){
            return true;
        }else
            return false;
    }

}
