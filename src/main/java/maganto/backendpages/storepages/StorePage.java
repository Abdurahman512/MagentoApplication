package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.By;
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

    @FindBy(id = "group_website_id")
    WebElement websiteMenu;

    @FindBy(xpath = "//input[@id=\"group_name\"]")
    WebElement nameFiled;

    @FindBy(id="group_root_category_id")
    WebElement rootCategoryFiled;

    @FindBy(xpath = "(//span[text()=\"Save Store\"])[1]")
    WebElement saveButton;

    @FindBy(xpath = "//span[text()=\"The store has been saved.\"]")
    WebElement successfullyMessage;

    @FindBy(xpath = "(//span[text()='Delete Store'])[1]")
    WebElement deleteStoreButton;

    @FindBy(xpath = "//span[text()='The store has been deleted.']")
    WebElement successfullyDeleteMessage;



    public String createStore(){
        storeDashboardPage.clickOnManagerStoreLink();
          utility.waitForElementPresent(createStoreButton);
          createStoreButton.click();
          utility.waitForElementPresent(websiteMenu);
          Select select1=new Select(websiteMenu);
          select1.selectByVisibleText("Magento eCommerce Application");
          utility.waitForElementPresent(nameFiled);
          storeName=utility.generateAdminName();
          nameFiled.sendKeys(storeName);
          utility.waitForElementPresent(rootCategoryFiled);
          Select select2=new Select(rootCategoryFiled);
          select2.selectByVisibleText("ShoesFar From the Madding Crowd");
          utility.waitForElementPresent(saveButton);
          saveButton.click();
          return storeName;
    }

    public boolean addStoreSuccessfullyMessage(){
        utility.waitForElementPresent(successfullyMessage);
        if(successfullyMessage.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

       public void editStore(){
        WebElement editStoreName=driver.findElement(By.xpath(String.format("//a[contains(text(),\" %s\")]",storeName)));
        utility.waitForElementPresent(editStoreName);
        editStoreName.click();
        Select select1=new Select(rootCategoryFiled);
        select1.selectByVisibleText("Jam--Istanbul Team");
        utility.waitForElementPresent(saveButton);
        saveButton.click();

       }

        public boolean editStoreSuccessfullyMessage(){
        utility.waitForElementPresent(successfullyMessage);
        if(successfullyMessage.isDisplayed()){
            return true;
        }else{
            return false;
          }
        }


        public void deleteStore(){
        WebElement deleteStoreName=driver.findElement(By.xpath(String.format("//a[contains(text(),\" %s\")]",storeName)));
        utility.waitForElementPresent(deleteStoreName);
        deleteStoreName.click();
        utility.waitForElementPresent(deleteStoreButton);
        deleteStoreButton.click();
        }


        public boolean deleteStoreSuccessfullyMessage(){
        if(successfullyDeleteMessage.isDisplayed()){
            return true;
        }else{
            return false;
          }
        }
}
