package maganto.backendpages.reportingpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage {
    WebDriver driver;

    @FindBy(id = "store_switcher")
    WebElement showReportFor;
    @FindBy(xpath= "//span[text()='Search']")
    WebElement searchButton;
    @FindBy(xpath= "//*[text()[contains(.,'records found')]]")
    WebElement TotalRecordsFound;

    TestUtility utility;
    Select select;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }

    public void viewAbandonedCartsReport(){
        utility.waitForElementPresent(showReportFor);
        select=new Select(showReportFor);
        select.selectByVisibleText("All Websites");
        utility.waitForElementPresent(searchButton);
        searchButton.click();
    }
    public boolean isAbandonedCartsReportShowed(){
        utility.waitForElementPresent(TotalRecordsFound);
        utility.sleep(2);
        if(TotalRecordsFound.isDisplayed())
            return true;
        else return false;
    }
}
