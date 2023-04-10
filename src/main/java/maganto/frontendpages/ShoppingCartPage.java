package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
public class ShoppingCartPage {
    // A user should be able to add products to shopping cart
    WebDriver driver;
    TestUtility utility;
    Actions action;
    @FindBy(xpath = "//div[@class=\"page-header-container\"]/a/img[1]")
    WebElement titleLink;
    @FindBy(xpath ="//ul[@class=\"products-grid\"]/li/a[1]" )
    WebElement selectedProduct;
    @FindAll(
            @FindBy(xpath = "//ul[@class=\"products-grid\"]/li/a")
    )
    List<WebElement> products;
    @FindBy(xpath = "//span[contains(text(),\"Add to Cart\")]")
    WebElement addToCartLink;
    @FindBy(xpath = "//li[@class=\"success-msg\"]/ul/li/span")
    WebElement successMassage;
    //A user should be able to update shopping cart
    @FindBy(id = "qty")
    WebElement QtyField;
    @FindBy(xpath = "//td[4]//ul[@class=\"cart-links\"]//a[contains(text(),\"Edit\")]")
    WebElement editIcon;
    @FindBy(xpath = "//*[@class=\"button btn-cart\"]/span")
    WebElement updateLink;
    @FindBy(xpath = "//tr[@class=\"first last odd\"]/td[4]")
    WebElement clickElement;

    @FindBy(xpath = "//input[@class=\"input-text qty\"]")
    WebElement afterQty;
    @FindBy(xpath = "//table[@id=\"shopping-cart-table\"]//td[5]//span[contains(text(),\"$5,000.00\")]")
    WebElement subTotal;
    //3. A user should be able to check out the order
    @FindBy(xpath = "//ul[@class=\"checkout-types top\"]//span[contains(text(),\"Proceed to Checkout\")]")
    WebElement checkoutLink;
    @FindBy(id = "billing:street1")
    WebElement addressField;
    @FindBy(id = "billing:city")
    WebElement cityField;
    @FindBy(xpath = "//select[@id=\"billing:region_id\"]")
    WebElement stateDropDown;
    @FindBy(id = "billing:postcode")
    WebElement zipCodeField;
    @FindBy(xpath = "//select[@id=\"billing:country_id\"]")
    WebElement countryDropDown;
    @FindBy(id = "billing:telephone")
    WebElement telephoneField;
    @FindBy(xpath = "//div[@class=\"fieldset\"]//span[contains(text(),\"Continue\")]")
    WebElement continueButton1;
    @FindBy(xpath = "//input[@id=\"p_method_cashondelivery\"]")
    WebElement cashOnDeliveryCheckBox;
    @FindBy(xpath = "//div[@id=\"payment-buttons-container\"]//span[contains(text(),\"Continue\")]")
    WebElement continueButton2;
    @FindBy(xpath = "//button[@class=\"button btn-checkout\"]//span[contains(text(),\"Place Order\")]")
    WebElement placeOrderButton;
    @FindBy(xpath = "//*[contains(text(),\"Your order has been received.\")]")
     WebElement checkOutSuccessfully;
     String beforeUpdate;
     String afterUpdate;
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        action=new Actions(driver);
    }
    public void addProductsToShoppingCart(){
        utility.waitForElementPresent(titleLink);
        action.moveToElement(titleLink).build().perform();
        titleLink.click();
        utility.waitForElementPresent(selectedProduct);
       selectedProduct.click();
        utility.waitForElementPresent(addToCartLink);
        addToCartLink.click();
    }
    public boolean verifyAddedToShoppingCartSuccessfully(){
        utility.waitForElementPresent(successMassage);
        if(successMassage.isDisplayed()){
            return true;
        }else
            return false;
    }
    public void updateShoppingCart(String Qty){
        utility.waitForElementPresent(editIcon);
        action.moveToElement(editIcon).build().perform();
        editIcon.click();
        utility.waitForElementPresent(QtyField);
        action.moveToElement(QtyField).build().perform();
        beforeUpdate=QtyField.getAttribute("value");
        System.out.println(beforeUpdate);
        QtyField.clear();
        QtyField.sendKeys(Qty);
        utility.waitForElementPresent(updateLink);
        updateLink.click();
        JavascriptExecutor executor=(JavascriptExecutor)driver;
       executor.executeScript("arguments[0].click();",clickElement);
        afterUpdate=afterQty.getAttribute("value");
        System.out.println(afterUpdate);

    }
    public boolean verifyUpdateSuccessfully(String expectedText){

        if(expectedText.equalsIgnoreCase(afterUpdate)){
        return true;
        }
        else
            return false;
    }
    public void checkOutOrder(String address,String city,String zipCode,String telephone){
        utility.waitForElementPresent(checkoutLink);
        checkoutLink.click();
        utility.waitForElementPresent(addressField);
        addressField.sendKeys(address);
        utility.waitForElementPresent(cityField);
        cityField.sendKeys(city);
        Select select1=new Select(stateDropDown);
        select1.selectByValue("18");
        utility.waitForElementPresent(zipCodeField);
        zipCodeField.sendKeys(zipCode);
        utility.waitForElementPresent(countryDropDown);
        Select select2=new Select(countryDropDown);
        select2.selectByValue("US");
        utility.waitForElementPresent(telephoneField);
        telephoneField.sendKeys(telephone);
        utility.waitForElementPresent(continueButton1);
        continueButton1.click();
        utility.waitForElementPresent(cashOnDeliveryCheckBox);
        cashOnDeliveryCheckBox.click();
        utility.waitForElementPresent(continueButton2);
        continueButton2.click();
        utility.waitForElementPresent(placeOrderButton);
        placeOrderButton.click();
    }
    public boolean checkOutOrderSuccessfully(){
        utility.waitForElementPresent(checkOutSuccessfully);
        if(checkOutSuccessfully.isDisplayed()){
            return true;
        }else
            return false;
    }

}
