package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreDashboardPage {
    WebDriver driver;
    TestUtility utility;
    Actions actions;

    public StoreDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
    }
    @FindBy(xpath = "//ul[@id=\"nav\"]//li/a/span[contains(text(),\"Sales\")]")
    WebElement salesLink;
    @FindBy(xpath = "//span[contains(text(),\"Orders\")]")
    WebElement ordersLink;
    @FindBy(xpath = "//span[contains(text(),\"The order has been created.\")]")
    WebElement orderSuccessfullyCreated;
    @FindBy(xpath = "//ul[@id=\"nav\"]//li/a/span[contains(text(),\"Catalog\")]")
    WebElement catalogLink;
    @FindBy(xpath = "//ul[@id=\"nav\"]//li/a/span[contains(text(),\"Manage Products\")]")
    WebElement manageProductsLink;

    @FindBy(xpath = "//span[text()=\"System\"]")
    WebElement systemLink;

    @FindBy(xpath = "//span[text()=\"Manage Stores\"]")
    WebElement managerStoresLink;

    public void clickOnOrdersLink(){
        utility.waitForElementPresent(salesLink);
        actions.moveToElement(salesLink).click().build().perform();
        utility.waitForElementPresent(ordersLink);
        actions.moveToElement(ordersLink).click().build().perform();
    }
    public boolean orderSuccessfullyCreated(){
        if(orderSuccessfullyCreated.isDisplayed()){
            return true;
        } else
            return false;
    }

    public void clickOnCatalogLink(){
        utility.waitForElementPresent(catalogLink);
        actions.moveToElement(catalogLink).click().build().perform();
        utility.waitForElementPresent(manageProductsLink);
        actions.moveToElement(manageProductsLink).click().build().perform();

    }

    public void clickOnManagerStoreLink(){
        utility.waitForElementPresent(systemLink);
        systemLink.click();
        utility.waitForElementPresent(managerStoresLink);
        managerStoresLink.click();
    }

}
