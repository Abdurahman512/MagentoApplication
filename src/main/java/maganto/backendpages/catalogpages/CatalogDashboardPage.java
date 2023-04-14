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

    @FindBy(xpath ="//span[text()=\"Catalog\"]")
    WebElement catalogLink;

    @FindBy(xpath = "//span[text()=\"Manage Categories\"]")
    WebElement manageCategoriesLink;

    public CatalogDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
    }

    public void clickDashboardPage(){
        utility.waitForElementPresent(dashBoardPageLink);
        dashBoardPageLink.click();
    }

    public void clickOnCatalogLink(){
        utility.waitForElementPresent(catalogLink);
        catalogLink.click();
    }

    public void clickOnManageCategoriesLink(){
        utility.waitForElementPresent(manageCategoriesLink);
        manageCategoriesLink.click();
    }
}
