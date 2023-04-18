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

public class CouponsPage {

    WebDriver driver;
    TestUtility testUtility;
    Actions actions;
    BackEndLogin backEndLogin;

    public CouponsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        actions=new Actions(driver);
        backEndLogin=new BackEndLogin(driver);
    }
    @FindBy(xpath = "//span[text()='Reports']")
    WebElement Reports;
    @FindBy(xpath = "(//span[text()='Sales'])[2]")
    WebElement Sales;
    @FindBy(xpath = "//span[text()='Coupons']")
    WebElement Coupons;
    @FindBy(id = "sales_report_from")
    WebElement fromName;
    @FindBy(id ="sales_report_to")
    WebElement toName;
    @FindBy(xpath = "//span[text()='Show Report']")
    WebElement ShowReport;
    @FindAll(@FindBy(xpath = "(//span[contains(text(),'Show Report')])[1]"))
    public List<WebElement> verifyShowReport1;


    public void  couponsReports(String fromname,String toname){
        testUtility.waitForElementPresent(Reports);
        actions.moveToElement(Reports).click().perform();
        testUtility.waitForElementPresent(Sales);
        actions.moveToElement(Sales).click().perform();
        testUtility.waitForElementPresent(Coupons);
        Coupons.click();
        testUtility.waitForElementPresent(fromName);
        fromName.sendKeys(fromname);
        testUtility.waitForElementPresent(toName);
        toName.sendKeys(toname);
        testUtility.waitForElementPresent(ShowReport);
        ShowReport.click();
    }
    public boolean verifyCouponsReports(){
        if (verifyShowReport1.size()>=1);
        testUtility.sleep(2);
        System.out.println("Coupons Usage Report is disply");
        return true;
    }
    }


