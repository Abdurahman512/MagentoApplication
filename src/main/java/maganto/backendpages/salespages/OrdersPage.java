package maganto.backendpages.salespages;

import maganto.utility.ExcelUtility;
import maganto.utility.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='Create New Order']")
    WebElement createOrderButton;
    @FindBy(xpath = "//input[@name='name']")
    WebElement customerNameField;
    @FindBy(xpath = "//span[text()='Search']")
    WebElement searchButton;
    @FindBy(xpath = "//label[@for='store_1']")
    WebElement selectStore;
    @FindBy(xpath = "//span[text()='Add Products']")
    WebElement addProductsButton;
    @FindBy(xpath = "//input[@value='1608']")
    WebElement productCheckBox;
    @FindBy(xpath = "//span[text()='Add Selected Product(s) to Order']")
    WebElement addSelectedProductsButton;
    @FindBy(linkText = "Get shipping methods and rates")
    WebElement getShippingMethod;
    @FindBy(xpath = "//label[text()='Cash On Delivery']")
    WebElement selectPaymentMethod;
    @FindBy(id = "s_method_freeshipping_freeshipping")
    WebElement selectShippingMethod;
    @FindBy(xpath = "//button[@id='submit_order_top_button']")
    WebElement submitOrderButton;
    @FindBy(xpath = "//span[text()='The order has been created.']")
    WebElement orderCreatedMessage;

    TestUtility utility;
    ExcelUtility excelUtility;
    Actions actions;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        excelUtility=new ExcelUtility();
        actions=new Actions(driver);
    }

    public void createOrder(String fileName,String sheetName,int rowNo,int clmnNo){
        utility.waitForElementPresent(createOrderButton);
        utility.javaScriptClick(createOrderButton);
        utility.waitForElementPresent(customerNameField);
        String customerName=excelUtility.readFromExcelCell(fileName,sheetName,rowNo,clmnNo);
        utility.sleep(2);
        customerNameField.sendKeys(customerName);
        utility.waitForElementPresent(searchButton);
        searchButton.click();
        utility.sleep(3);
        WebElement selectCustomer= driver.findElement(By.xpath(String.format("//tbody//td[contains(text(),'%s')]",customerName)));
        utility.waitForElementPresent(selectCustomer);
        selectCustomer.click();
        utility.waitForElementPresent(selectStore);
        selectStore.click();
        utility.waitForElementPresent(addProductsButton);
        utility.javaScriptClick(addProductsButton);
        utility.waitForElementPresent(productCheckBox);
        utility.javaScriptClick(productCheckBox);
        utility.sleep(2);
        utility.waitForElementPresent(addSelectedProductsButton);
        actions.moveToElement(addSelectedProductsButton).build().perform();
        utility.sleep(2);
        addSelectedProductsButton.click();
        utility.waitForElementPresent(getShippingMethod);
        actions.moveToElement(getShippingMethod).build().perform();
        utility.sleep(2);
        getShippingMethod.click();
        utility.waitForElementPresent(selectPaymentMethod);
        actions.moveToElement(selectPaymentMethod).build().perform();
        utility.sleep(2);
        selectPaymentMethod.click();
        utility.waitForElementPresent(selectShippingMethod);
        selectShippingMethod.click();
        utility.waitForElementPresent(submitOrderButton);
        actions.moveToElement(submitOrderButton).build().perform();
        utility.sleep(2);
        submitOrderButton.click();
    }
    public boolean isOrderCreated(){
        utility.waitForElementPresent(orderCreatedMessage);
        if(orderCreatedMessage.isDisplayed())
            return true;
        else return false;
    }

}
