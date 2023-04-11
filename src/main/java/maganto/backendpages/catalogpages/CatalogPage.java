package maganto.backendpages.catalogpages;

import maganto.utility.ExcelUtility;
import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {

    WebDriver driver;
    TestUtility utility;
    @FindBy(xpath ="//span[text()=\"Catalog\"]")
    WebElement catalogLink;

    @FindBy(xpath = "//span[text()=\"Manage Categories\"]")
    WebElement manageCategoriesLink;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
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
