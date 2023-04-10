package maganto.backendpages.catalogpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogDashboardPage {
    WebDriver driver;

    @FindBy (css=".logo")
    WebElement dashBoardPageLink;

    TestUtility utility;

    public CatalogDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }

    public void clickDashboardPage(){
        utility.waitForElementPresent(dashBoardPageLink);
        dashBoardPageLink.click();
    }
}
