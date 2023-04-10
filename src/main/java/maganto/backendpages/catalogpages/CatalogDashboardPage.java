package maganto.backendpages.catalogpages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogDashboardPage {
    WebDriver driver;
    Actions actions;
    TestUtility utility;

    @FindBy (css=".logo")
    WebElement dashBoardPageLink;

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
