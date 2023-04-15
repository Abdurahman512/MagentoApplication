package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class StorePage {

    WebDriver driver;
    TestUtility utility;
    StoreDashboardPage storeDashboardPage;
    String storeName;
    public StorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        storeDashboardPage=new StoreDashboardPage(driver);
    }

    @FindBy(xpath = "//button[@title=\"Create Store\"]")
    WebElement createStoreButton;

    @FindBy(xpath = "//option[text()=\"Magento eCommerce Application\"]")
    WebElement websiteMenu;

    @FindBy(id = "//input[@id=\"group_name\"]")
    WebElement nameFiled;

    @FindBy(id="//select[@id=\"group_website_id\"]")
    WebElement rootCategoryFiled;

    @FindBy(xpath = "(//span[text()=\"Save Store\"])[1]")
    WebElement saveButton;

    @FindBy(xpath = "//span[text()=\"The store has been saved.\"]")
    WebElement successfullyMessage;


    public void createStore(){
        storeDashboardPage.clickOnManagerStoreLink();
          utility.waitForElementPresent(createStoreButton);
          createStoreButton.click();
          utility.waitForElementPresent(websiteMenu);
          Select select1=new Select(websiteMenu);
          select1.selectByVisibleText("Magento eCommerce Application");
          utility.waitForElementPresent(nameFiled);
          nameFiled.sendKeys(storeName=utility.generateAdminName());
          utility.waitForElementPresent(rootCategoryFiled);
          Select select2=new Select(rootCategoryFiled);
          select2.selectByVisibleText("ShoesFar From the Madding Crowd");

    }







}
