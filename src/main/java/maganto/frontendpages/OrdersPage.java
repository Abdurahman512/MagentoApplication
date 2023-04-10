package maganto.frontendpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
    WebDriver driver;
    TestUtility utility;
    @FindBy(xpath = "(//a[contains(text(),'My Orders')])")
    WebElement MyOrders;
    @FindBy(xpath = "//h1[text()='My Orders']")
    WebElement MyOrdersPage;
    @FindBy(xpath = "(//a[contains(text(),'My Downloadable Products')])")
    WebElement MyDownloadable;
    @FindBy(xpath = "//h1[text()='My Downloadable Products']")
    WebElement MyDownloadablePage;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utility = new TestUtility(driver);
    }

    public void ViewMyOrders() {
        utility.waitForElementPresent(MyOrders);
        MyOrders.click();
    }

    public boolean MyOrdersPage() {
        utility.waitForElementPresent(MyOrdersPage);
        if (MyOrdersPage.isDisplayed())
            return true;
        else return false;
    }

    public void viewDownloadOrders() {
        utility.waitForElementPresent(MyDownloadable);
        MyDownloadable.click();
    }
    

    public boolean DownloadOrdersPage() {
        utility.waitForElementPresent(MyDownloadablePage);
        if (MyDownloadablePage.isDisplayed())
            return true;
        else return false;
    }
}
