package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.apache.tools.ant.types.selectors.SelectSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreViewPage {

    //Store Manager can create a store view
    WebDriver driver;
    TestUtility testUtility;
    String storeName;
    String storeCode;
    String storeSortOrder;

    public StoreViewPage(WebDriver driver){
        this.driver=driver;
        testUtility = new TestUtility(driver);
        PageFactory.initElements(driver, this);


    }

    @FindBy(xpath = "//span[contains(text(),'System')]")
    WebElement systemLink;
    @FindBy(xpath = "//span[contains(text(),'Manage Stores')]")
    WebElement manageStoresLink;
    @FindBy(xpath = "//span[text()='Save Store View'])[1]")
    WebElement createStoreViewLink;

    //field
    @FindBy(xpath = "//select[@id='store_group_id']")
    WebElement storeDropDown;
    @FindBy(id = "store_name")
    WebElement storeNameField;
    @FindBy(id = "store_code")
    WebElement storeCodeField;
    @FindBy(id = "store_is_active")
    WebElement statusDropDown;
    @FindBy(id = "store_sort_order")
    WebElement storeSortOrderField;
    @FindBy(xpath = "//body[1]/div[1]/div[3]/div[1]/div[2]/p[1]/button[3]/span[1]/span[1]/span[1]")
    WebElement saveStoreViewLink;
    @FindBy(xpath ="//span[contains(text(),'The store view has been saved')]")
    WebElement successMessage;




    public void createStoreView(String storeName, String storeCode, String storeSortOrder){
        testUtility.waitForElementPresent(createStoreViewLink);
        createStoreViewLink.click();
        testUtility.waitForElementPresent(storeNameField);
        storeCodeField.sendKeys(storeName);
        testUtility.waitForElementPresent(storeCodeField);
        storeCodeField.sendKeys(storeCode);
        testUtility.waitForElementPresent(storeSortOrderField);
        storeSortOrderField.sendKeys(storeSortOrder);
        testUtility.waitForElementPresent(saveStoreViewLink);
        saveStoreViewLink.click();



    }
    public boolean verifyStoreViewSaved(){
        testUtility.waitForElementPresent(successMessage);
        if(successMessage.isDisplayed())
            return true;
        else return false;
    }



    }




