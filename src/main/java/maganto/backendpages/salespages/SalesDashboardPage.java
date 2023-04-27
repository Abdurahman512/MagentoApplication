package maganto.backendpages.salespages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesDashboardPage {

    WebDriver driver;
    TestUtility utility;
    Actions actions;
    @FindBy(xpath = "//span[contains(text(),'Sales')]")
    WebElement salesLink;
    @FindBy(xpath = "//span[contains(text(),'Orders')]")
    WebElement ordersLink;
    @FindBy(xpath = "//span[contains(text(),\"Invoices\")]")
    WebElement invoiceLink;

    public SalesDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    public void openOrdersPage(){
        utility.waitForElementPresent(salesLink);
        actions.moveToElement(salesLink).build().perform();
        utility.javaScriptClick(salesLink);
        utility.sleep(2);
        utility.waitForElementPresent(ordersLink);
        ordersLink.click();
    }
      public void openInvoicePage(){
          utility.waitForElementPresent(salesLink);
          actions.moveToElement(salesLink).build().perform();
          utility.javaScriptClick(salesLink);
          utility.sleep(2);
          utility.waitForElementPresent(invoiceLink);
          invoiceLink.click();
      }

}
