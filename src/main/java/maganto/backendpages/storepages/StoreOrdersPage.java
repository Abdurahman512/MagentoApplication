package maganto.backendpages.storepages;

import maganto.backendpages.catalogpages.ProductPage;
import maganto.backendpages.customerpages.CustomerPage;
import maganto.utility.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class StoreOrdersPage {
    WebDriver driver;
    TestUtility utility;
    Actions actions;
    StoreDashboardPage storeDashboardPage;
    CustomerPage customerPage;
    ProductPage productPage;

    public StoreOrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
        storeDashboardPage=new StoreDashboardPage(driver);
        customerPage=new CustomerPage(driver);
        productPage=new ProductPage(driver);
    }
   //create Order
    @FindBy(xpath = "//div[@id=\"page:main-container\"]//span[contains(text(),\"Create New Order\")]")
    WebElement createNewOrderLink;
    @FindBy(id = "sales_order_create_customer_grid_filter_email")
    WebElement emailField;
    @FindBy(xpath = "//button[@id=\"id_b695e7c865ad3ba1e66c96d90b9fe2b8\"]//span[contains(text(),\"Search\")]")
    WebElement searchButton;
    @FindBy(xpath = "//tr[@class=\"even pointer\"]//td[3]")
    WebElement selectedCustomer;
    @FindBy(xpath = "(//label[contains(text(),\"Demo Store View\")])[1]")
    WebElement selectedStore;
    @FindBy(xpath = "//span[contains(text(),\"Add Products\")]")
    WebElement addProductsButton;
    @FindBy(xpath = "//input[@id=\"sales_order_create_search_grid_filter_name\"]")
    WebElement productNameField;
    @FindBy(xpath = "//button[@id=\"id_fa9bb3aa6d28ec88db48d98da0625215\"]//span[contains(text(),\"Search\")]")
    WebElement searchButton1;
    @FindBy(xpath = "//table[@id=\"sales_order_create_search_grid_table\"]//tbody//tr//td[1]")
    WebElement selectedProduct;
    @FindBy(xpath = "//button[@id=\"id_e34591d9c3b3c71dddc8913a4b51c873\"]//span[contains(text(),\"Add Selected Product(s) to Order\")]")
   WebElement addSelectedProductLink;
    @FindBy(xpath = "//input[@name=\"item[1093][qty]\"]")
    WebElement productQtyField;
    @FindBy(xpath = "//button[@id=\"id_f2e552ee20c046910a0aec6ab8dfa71a\"]//span[contains(text(),\"Update Items and Qty's\")]")
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
    @FindBy(xpath = "//button[@id=\"id_ac2863dd30fe85e244dfe4473865f984\"]//span[contains(text(),\"Submit Order\")]")
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
        utility.waitForElementPresent(emailField);
        emailField.sendKeys("elida.raynor@gmail.com");
        utility.waitForElementPresent(searchButton);
        utility.javaScriptClick(searchButton);
        utility.waitForElementPresent(selectedCustomer);
        selectedCustomer.click();
        utility.waitForElementPresent(selectedStore);
        selectedStore.click();
        utility.waitForElementPresent(addProductsButton);
        addProductsButton.click();
        utility.waitForElementPresent(productNameField);
        productNameField.sendKeys("Kiwi ");
        utility.waitForElementPresent(searchButton1);
        searchButton1.click();
        utility.waitForElementPresent(selectedProduct);
        selectedProduct.click();
        utility.waitForElementPresent(addSelectedProductLink);
        addSelectedProductLink.click();
        utility.waitForElementPresent(productQtyField);
        productQtyField.sendKeys("45");
        utility.waitForElementPresent(updateQtyButton);
        updateQtyButton.click();
        utility.waitForElementPresent(billingFirstNameField);
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
        utility.waitForElementPresent(stateDropDown);
        select=new Select(stateDropDown);
        select.selectByVisibleText("Maine");
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
        editIcon.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        utility.waitForElementPresent(productQtyField);
        productQtyField.clear();
        productQtyField.sendKeys("50");
        utility.waitForElementPresent(billingFirstNameField);
        billingFirstNameField.sendKeys(utility.generateFirstName());
        utility.waitForElementPresent(billingLastNameField);
        billingLastNameField.sendKeys(utility.generateLastName());
        utility.waitForElementPresent(streetField);
        streetField.sendKeys(utility.generateStreetAddress());
        utility.waitForElementPresent(submitOrderButton);
        submitOrderButton.click();
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
