package maganto.backendpages.reportingpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportingDashboardPage {

    WebDriver driver;
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsLink;
    @FindBy(xpath = "//span[text()='Sales']")
    WebElement salesLink;
    @FindBy(xpath = "//span[text()='Orders']")
    WebElement ordersLink;
    @FindBy(xpath = "(//span[text()=\"Customers\"])[1]")
    WebElement customersLink;
    @FindBy(xpath = "//span[text()='Customers by orders total']")
    WebElement customerByOrdersTotalLink;
    @FindBy(xpath = "//span[text()='Customers by number of orders']")
    WebElement customerByNumberOfOrdersLink;
    @FindBy(xpath = "(//span[contains(text(),'Invoiced')])[1]")
    WebElement invoicedOption;
    @FindBy(xpath = "//span[contains(text(),'Shipping')]")
    WebElement shippingLink;
    TestUtility utility;
    Actions actions;

    public ReportingDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    public void openOrdersPage(){
        utility.waitForElementPresent(reportsLink);
        actions.moveToElement(reportsLink).build().perform();
        utility.sleep(2);
        utility.waitForElementPresent(salesLink);
        actions.moveToElement(salesLink).build().perform();
        utility.sleep(2);
        utility.waitForElementPresent(ordersLink);
        ordersLink.click();
    }
    public void ClickOnCustomersByOrdersTotalLink() {
        utility.waitForElementPresent(reportsLink);
        actions.moveToElement(reportsLink).moveToElement(customersLink).moveToElement(customerByOrdersTotalLink).click().perform();

    }
    public void ClickOnCustomersByNumberOfOrdersLink() {
        utility.waitForElementPresent(reportsLink);
        actions.moveToElement(reportsLink).moveToElement(customersLink).moveToElement(customerByNumberOfOrdersLink).click().perform();
    }
    public void ClickOnInvoicedOption() {
        utility.waitForElementPresent(reportsLink);
        actions.moveToElement(reportsLink).click().perform();
        utility.waitForElementPresent(salesLink);
        actions.moveToElement(salesLink).click().perform();
        utility.waitForElementPresent(invoicedOption);
        actions.moveToElement(invoicedOption).click().perform();
    }
    public void openShippingPage(){
        utility.waitForElementPresent(reportsLink);
        actions.moveToElement(reportsLink).moveToElement(salesLink).moveToElement(shippingLink).click().perform();
    }

}
