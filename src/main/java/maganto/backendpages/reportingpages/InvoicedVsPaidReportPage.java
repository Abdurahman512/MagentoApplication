package maganto.backendpages.reportingpages;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
public class InvoicedVsPaidReportPage {
    WebDriver driver;
    TestUtility testUtility;
    Select select;

    public InvoicedVsPaidReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    @FindBy(xpath = "//select[@title='Period']")
    WebElement periodDropDown;
    @FindBy(css = "input#sales_report_from")
    WebElement fromDateField;
    @FindBy(css = "input#sales_report_to")
    WebElement toDateField;
    @FindBy(xpath = "(//span[contains(text(),\"Show Report\")])")
    WebElement showReportButton;

    @FindAll(@FindBy(css = ".data>tbody>tr"))
    List<WebElement> invoicedReportTable;


    public void viewSalesInvoicedVsPaidReport(String fromDate,String toDate){
        testUtility.waitForElementPresent(periodDropDown);
        select=new Select(periodDropDown);
        select.selectByValue("month");
        testUtility.waitForElementPresent(fromDateField);
        fromDateField.sendKeys(fromDate);
        testUtility.waitForElementPresent(toDateField);
        toDateField.sendKeys(toDate);
        testUtility.waitForElementPresent(showReportButton);
        showReportButton.click();
    }

    public boolean verifyViewSalesInvoicedVsPaidReportSuccessfully() {
        testUtility.waitForElementPresent(invoicedReportTable.get(0));
        if(invoicedReportTable.get(0).isDisplayed()) {
            System.out.println("Reporting Manager see sales- TotalInvoiced Vs Paid Report Test is Passed!!!");
            return true;
        }else {
            System.out.println("Reporting Manager see sales- TotalInvoiced Vs Paid Report Test is Failed!!!");
            return false;
        }
    }
}

