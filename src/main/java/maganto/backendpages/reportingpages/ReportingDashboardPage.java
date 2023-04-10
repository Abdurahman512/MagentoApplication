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
}
