package maganto.backendpages.reportingpages;

import maganto.backendpages.BackEndLogin;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesPage {


    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    BackEndLogin backEndLogin;



    @FindBy(xpath = "//span[text()='Reports']")
    WebElement reportsLink;

    @FindBy(xpath = "//span[text()='Sales']")
    WebElement salesLink;

    @FindBy(xpath = "//span[text()='Refunds']")
    WebElement refundsLink;

    @FindBy(xpath = "//input[@id='sales_report_from']")
    WebElement dateFromField;

    @FindBy(xpath = "//input[@id='sales_report_to']")
    WebElement dataToField;

    @FindBy(xpath = "(//span[text()='Show Report'])[1]")
    WebElement showReportLink;

    @FindBy(xpath = "//table//tbody//tr//td//h3[text()='Total Refunded Report']")
    WebElement totalRefundedPage;

   // @FindBy(xpath = "//table[@id='id_feb597d357f0fb5724bd894037a52e5d_table']//tbody//tr")
    //WebElement totalRefundsReportLink;

    @FindBy(xpath = "//tr[@class='totals']")
    WebElement totalLink;


    public SalesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
        actions=new Actions(driver);
        backEndLogin=new BackEndLogin(driver);
    }

    // Reporting Manager should be able to see Sales Total Refunds Report
    public  void showRefundsReport(String dateTo, String dateFrom){
        testUtility.waitForElementPresent(reportsLink);
        actions.moveToElement(reportsLink).click().perform();
        testUtility.waitForElementPresent(salesLink);
        actions.moveToElement(salesLink).click().perform();
        testUtility.waitForElementPresent(reportsLink);
        actions.moveToElement(refundsLink).click().perform();
        testUtility.waitForElementPresent(dateFromField);
        dateFromField.sendKeys(dateFrom);
        testUtility.waitForElementPresent(dataToField);
        dataToField.sendKeys(dateTo);
        testUtility.sleep(2);
        testUtility.waitForElementPresent(showReportLink);
        showReportLink.click();




    }

    public boolean isTotalRefundedReportShowed(){
        testUtility.sleep(2);
        testUtility.waitForElementPresent(totalLink);
        if(totalLink.isDisplayed())
            return true;
        else return false;
    }
}
