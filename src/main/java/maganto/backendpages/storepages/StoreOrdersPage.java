package maganto.backendpages.storepages;

import maganto.backendpages.catalogpages.ProductPage;
import maganto.backendpages.customerpages.CustomerPage;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class StoreOrdersPage {
    WebDriver driver;
    TestUtility utility;
    Actions actions;
    CustomerPage customerPage;
    ProductPage productPage;
    @FindBy(xpath = "//ul[@id=\"nav\"]//li/a/span[contains(text(),\"Sales\")]")
    WebElement salesLink;
    @FindBy(xpath = "//span[contains(text(),\"Orders\")]")
    WebElement ordersLink;
    @FindBy(xpath = "//div[@id=\"page:main-container\"]//span[contains(text(),\"Create New Order\")]")
    WebElement createNewOrderLink;
    @FindBy(xpath = "//table//td[contains(text(),\" Jack Sparrow \")]")
    WebElement selectedCustomer;

}
