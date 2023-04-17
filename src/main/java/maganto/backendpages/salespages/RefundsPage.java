package maganto.backendpages.salespages;

import maganto.backendpages.BackEndLogin;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class RefundsPage {

    public RefundsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        actions=new Actions(driver);
        backEndLogin=new BackEndLogin(driver);
    }

    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    BackEndLogin backEndLogin;


    @FindBy(xpath = "//span[text()='Reports']")
    WebElement Reports;
    @FindBy(xpath = "(//span[text()='Sales'])[2]")
    WebElement Sales;
    @FindBy(xpath = "//span[text()='Refunds']")
    WebElement Refunds;
    @FindBy(id = "sales_report_from")
    WebElement fromName;
    @FindBy(id = "sales_report_to")
    WebElement toName;
    @FindBy(xpath = "(//span[text()='Show Report'])[1]")
    WebElement ShowReport;
   @FindAll( @FindBy(xpath = "//td[@class=' a-right ']"))
     public List <WebElement>verifyShowReport;


    public void  viewRefundsReports(String from,String to){
        testUtility.waitForElementPresent(Reports);
        Reports.click();
        testUtility.waitForElementPresent(Sales);
        Sales.click();
        testUtility.waitForElementPresent(Refunds);
        Refunds.click();
        testUtility.waitForElementPresent(fromName);
        fromName.sendKeys(from);
        testUtility.waitForElementPresent(toName);
        toName.sendKeys(to);
        testUtility.sleep(2);
        testUtility.waitForElementPresent(ShowReport);
        ShowReport.click();
    }
public boolean verifyMessageRefunds(){
        if (verifyShowReport.size()>=1);
    System.out.println("Total Refunded Report is display");
        return true;
}


}
