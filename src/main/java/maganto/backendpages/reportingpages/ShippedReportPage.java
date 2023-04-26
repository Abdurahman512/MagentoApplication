package maganto.backendpages.reportingpages;

import maganto.utility.ExcelUtility;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShippedReportPage {

    WebDriver driver;
    @FindBy(id = "sales_report_from")
    WebElement shippingReportDateFrom;
    @FindBy(id = "sales_report_to")
    WebElement shippingReportDateTo;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement showReportButton;
    @FindBy(xpath = "//tr[@class='totals']")
    WebElement totalShipped;

    TestUtility utility;
    Actions actions;
    public ShippedReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
    }

    public void viewTotalShippedReport(String dateFrom, String dateTo){
        utility.waitForElementPresent(shippingReportDateFrom);
        shippingReportDateFrom.sendKeys(dateFrom);
        utility.waitForElementPresent(shippingReportDateTo);
        utility.sleep(2);
        shippingReportDateTo.sendKeys(dateTo);
        utility.waitForElementPresent(showReportButton);
        showReportButton.click();
    }
    public boolean confirmIsReportAppeared(){
        utility.waitForElementPresent(totalShipped);
        if (totalShipped.isDisplayed())
            return true;
        else return false;
    }

}
