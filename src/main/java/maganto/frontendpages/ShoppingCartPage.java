package maganto.frontendpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage {
    // A user should be able to add products to shopping cart
    WebDriver driver;
    @FindBy(xpath = "//div[@class=\"page-header-container\"]/a/img[1]")
    WebElement titleLink;
    @FindAll(
            @FindBy(xpath = "//ul[@class=\"products-grid\"]/li/a")
    )
    List<WebElement> products;
    @FindBy(xpath = "//span[contains(text(),\"Add to Cart\")]")
    WebElement addToCartLink;
    @FindBy(xpath = "//li[@class=\"success-msg\"]/ul/li/span")
    WebElement SuccessMassage;
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





}
