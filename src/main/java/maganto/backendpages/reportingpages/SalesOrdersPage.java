package maganto.backendpages.reportingpages;

import maganto.utility.ExcelUtility;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesOrdersPage {
    WebDriver driver;

    @FindBy(id = "sales_report_from")
    WebElement reportDateFrom;
    @FindBy(id = "sales_report_to")
    WebElement reportDateTo;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement showReportButton;
    @FindBy(xpath = "//tr[@class='totals']")
    WebElement orderTotal;

    TestUtility utility;
    ExcelUtility excelUtility;
    Actions actions;

    public SalesOrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        excelUtility=new ExcelUtility();
        actions=new Actions(driver);
    }
    public void viewTotalOrdersReport(String dateFrom ,String dateTo ){
        utility.waitForElementPresent(reportDateFrom);
        reportDateFrom.sendKeys(dateFrom);
        utility.waitForElementPresent(reportDateTo);
        utility.sleep(2);
        reportDateTo.sendKeys(dateTo);
        utility.waitForElementPresent(showReportButton);
        showReportButton.click();
    }

    public boolean isOrdersReportShowed(){
        utility.waitForElementPresent(orderTotal);
        if(orderTotal.isDisplayed())
            return true;
        else return false;
    }


}
