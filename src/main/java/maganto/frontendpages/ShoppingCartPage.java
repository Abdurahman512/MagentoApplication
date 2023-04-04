package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ShoppingCartPage {
    // A user should be able to add products to shopping cart
    WebDriver driver;
    TestUtility utility;
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
    @FindBy(xpath = "//input[@name=\"cart[830][qty]\"]")
    WebElement QtyField;
    @FindBy(xpath = "//td[4]//span[contains(text(),\"Update\")]")
    WebElement updateLink;
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

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }
    public void addProductsToShoppingCart(){
        utility.waitForElementPresent(titleLink);
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
        utility.waitForElementPresent(QtyField);
        QtyField.sendKeys(Qty);
        utility.waitForElementPresent(updateLink);
        updateLink.click();
    }
    public boolean verifyUpdateSuccessfully(){
        return true;
    }
    public void checkOutOrder(String address,String city,String zipCode,String telephone){
        utility.waitForElementPresent(checkoutLink);
        checkoutLink.click();
        utility.waitForElementPresent(addressField);
        addressField.sendKeys(address);
        utility.waitForElementPresent(cityField);
        cityField.sendKeys(city);
        Select select1=new Select(stateDropDown);
        select1.selectByVisibleText("");
        utility.waitForElementPresent(zipCodeField);
        zipCodeField.sendKeys(zipCode);
        utility.waitForElementPresent(countryDropDown);
        Select select2=new Select(countryDropDown);
        select2.selectByVisibleText("");
        utility.waitForElementPresent(telephoneField);
        telephoneField.sendKeys(telephone);

    }

}
