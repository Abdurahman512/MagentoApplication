package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.By;
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

    int priceOfOneProduct;
    @FindBy(xpath = "//div[@class=\"page-header-container\"]/a/img[1]")
    WebElement titleLink;
    @FindBy(xpath ="//ul[@class=\"products-grid\"]/li/a[1]" )
    WebElement selectedProduct;
    @FindAll(
            @FindBy(xpath = "//ul[@class=\"products-grid\"]/li/a")
    )
    List<WebElement> products;
    @FindBy(xpath = "(//a[contains(text(),\"citron \")])[1]")
    WebElement productInShoppingCart;
    @FindBy(xpath = "//span[@class=\"count\"]")
    WebElement shoppingCartValue;
    @FindBy(xpath = "(//span[contains(text(),\"Add to Cart\")])[2]")
    WebElement addToCartLink;
    @FindBy()
    WebElement price;
    @FindBy(xpath = "//li[@class=\"success-msg\"]/ul/li/span")
    WebElement successMassage;
    //A user should be able to update shopping cart
    @FindBy(id = "qty")
    WebElement QtyField;
    @FindBy(xpath = "//td[4]//ul[@class=\"cart-links\"]//a[contains(text(),\"Edit\")]")
    WebElement editIcon;
    @FindBy(xpath = "//a[contains(text(),\" Edit item \")]")
    WebElement editItemButton;
    @FindBy(xpath = "//span[contains(text(),\"Update Cart\")]")
    WebElement updateLink;
    @FindBy(xpath = "//tr[@class=\"first last odd\"]/td[4]")
    WebElement clickElement;

    @FindBy(xpath = "//input[@class=\"input-text qty\"]")
    WebElement afterQty;
    @FindBy(xpath = "//table[@id=\"shopping-cart-table\"]//td[5]//span[contains(text(),\"$5,000.00\")]")
    WebElement subTotal;
    //3. A user should be able to check out the order
    @FindBy(xpath = "(//a[contains(text(),\"Checkout\")])[1]")
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
    @FindBy(xpath = "//div[@id=\"billing-buttons-container\"]//button")
    WebElement continueButton1;
    @FindBy(xpath = "//input[@id=\"s_method_freeshipping_freeshipping\"]")
    WebElement freeShippingCheckBox;
    @FindBy(xpath = "//input[@id=\"p_method_cashondelivery\"]")
    WebElement cashOnDeliveryCheckBox;
    @FindBy(xpath = "//div[@id=\"shipping-method-buttons-container\"]//button")
    WebElement continueButton2;
    @FindBy(xpath = "//div[@id=\"payment-buttons-container\"]//button")
    WebElement continueButton3;
    @FindBy(xpath = "//button[@class=\"button btn-checkout\"]//span[contains(text(),\"Place Order\")]")
    WebElement placeOrderButton;
    @FindBy(xpath = "//*[contains(text(),\"Your order has been received.\")]")
     WebElement checkOutSuccessfully;
    @FindBy(xpath = "(//span[contains(text(),\"Cart\")])[1]")
    WebElement cartLink;
    @FindBy(xpath = "//span[@class=\"count\"]")
    WebElement cartValue;
     String beforeUpdate;
     String afterUpdate;
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        action=new Actions(driver);
    }
    int i;
    public void addProductsToShoppingCart(){
        utility.waitForElementPresent(titleLink);
        action.moveToElement(titleLink).build().perform();
        titleLink.click();
        utility.waitForElementPresent(selectedProduct);
       action.moveToElement(selectedProduct).click().build().perform();
       utility.waitForElementPresent(QtyField);
        String qty=String.valueOf((int)((Math.random()*10)+1));
        QtyField.clear();
        QtyField.sendKeys(qty);
        utility.waitForElementPresent(addToCartLink);
        action.moveToElement(addToCartLink).click().build().perform();
    }

    public boolean verifyAddedToShoppingCartSuccessfully(){
        utility.waitForElementPresent(successMassage);
        if(successMassage.isDisplayed()){
            return true;
        }else
            return false;
    }
    public void updateShoppingCart(){
        utility.waitForElementPresent(cartLink);
        utility.javaScriptClick(cartLink);
        utility.waitForElementPresent(editItemButton);
        editItemButton.click();
        utility.waitForElementPresent(QtyField);
        action.moveToElement(QtyField).build().perform();
        beforeUpdate=QtyField.getAttribute("value");
        System.out.println(beforeUpdate);
        QtyField.clear();
        String qty=String.valueOf((int)((Math.random()*20)+1));
        QtyField.sendKeys(qty);
        utility.waitForElementPresent(updateLink);
        updateLink.click();
        afterUpdate=cartValue.getText();
        System.out.println(afterUpdate);
    }

    public boolean verifyUpdateSuccessfully(){

        if(!beforeUpdate.equalsIgnoreCase(afterUpdate)){
        return true;
        }
        else
            return false;
    }
    public void checkOutOrder(String address,String city,String zipCode,String telephone){
        utility.waitForElementPresent(cartLink);
        utility.javaScriptClick(cartLink);
        utility.waitForElementPresent(checkoutLink);
        checkoutLink.click();
        utility.waitForElementPresent(continueButton1);
        continueButton1.click();
        utility.waitForElementPresent(freeShippingCheckBox);
        utility.javaScriptClick(freeShippingCheckBox);
        utility.waitForElementPresent(continueButton2);
        utility.javaScriptClick(continueButton2);
        utility.waitForElementPresent(cashOnDeliveryCheckBox);
        cashOnDeliveryCheckBox.click();
        utility.waitForElementPresent(continueButton3);
        continueButton3.click();
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
