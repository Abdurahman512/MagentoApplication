package maganto.backendpages.storepages;

import maganto.utility.TestUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import java.util.Random;

public class StoreViewPage {

    //Store Manager can create a store view
    WebDriver driver;
    TestUtility testUtility;
    String storeName;
   String storeCode;
    String storeSortOrder;

    Random r = new java.util.Random();
    public StoreViewPage(WebDriver driver){
        this.driver=driver;
        testUtility = new TestUtility(driver);
        PageFactory.initElements(driver, this);
        storeName= testUtility.generateStoreName();
        storeCode= "a"+testUtility.generateNumber();
        storeSortOrder= testUtility.generateStoreSortOrder();

    }

    @FindBy(xpath = "//span[contains(text(),'System')]")
    WebElement systemLink;
    @FindBy(xpath = "//span[contains(text(),'Manage Stores')]")
    WebElement manageStoresLink;
    @FindBy(xpath = "//body[1]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/button[3]/span[1]/span[1]/span[1]")
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

    @FindBy(xpath = "//a[contains(text(),'Store View Name')]")
    WebElement storeViewNameLink;




    public void createStoreView(){
        testUtility.waitForElementPresent(systemLink);
        systemLink.click();
        testUtility.waitForElementPresent(manageStoresLink);
        manageStoresLink.click();

        testUtility.waitForElementPresent(createStoreViewLink);
        createStoreViewLink.click();

        testUtility.waitForElementPresent(storeNameField);
        storeNameField.sendKeys(testUtility.generateStoreName()+(int)(Math.random()*100));

        testUtility.waitForElementPresent(storeCodeField);

        storeCodeField.sendKeys("a"+testUtility.generateNumber()+(int)(Math.random()*100));

        testUtility.waitForElementPresent(storeSortOrderField);
        storeSortOrderField.sendKeys(testUtility.generateStoreSortOrder());

        testUtility.waitForElementPresent(saveStoreViewLink);
        saveStoreViewLink.click();



    }
    public String getStoreName(){
        return storeName;
    }
    public String getStoreCode(){
        return storeCode;

    }
    public String getStoreSortOrder(){
        return storeSortOrder;
    }
    public boolean verifyStoreViewSaved(){
        testUtility.waitForElementPresent(successMessage);
        if(successMessage.isDisplayed())
            return true;
        else return false;
    }

    // Store Manager can edit a store view

    @FindBy(xpath = "//span[contains(text(),'The store view has been saved')]")
    WebElement editStoreViewSavedMessage;

    @FindBy(id = "store_group_id")
    WebElement storeButton;
    @FindBy(id = "store_is_active")
    WebElement storeStatusButton;
    @FindBy(xpath = "//a[contains(text(),'mjmjmjmj')]")
    WebElement mjStoreView;



    public void editStoreView(){
        testUtility.waitForElementPresent(systemLink);
        systemLink.click();
        testUtility.waitForElementPresent(manageStoresLink);
        manageStoresLink.click();
        testUtility.waitForElementPresent(mjStoreView);
        mjStoreView.click();
       testUtility.waitForElementPresent(storeCodeField);
       storeCodeField.clear();
        testUtility.sleep(2);

        storeCodeField.sendKeys("a"+testUtility.generateNumber()+"8");
        testUtility.sleep(2);
        testUtility.waitForElementPresent(saveStoreViewLink);
        saveStoreViewLink.click();

    }

    public boolean verifyStoreViewEdit(){
        return editStoreViewSavedMessage.isDisplayed();
    }

//Store Manager can view all store
    public void viewAllStore(){
        testUtility.waitForElementPresent(systemLink);
        systemLink.click();
        testUtility.waitForElementPresent(manageStoresLink);
        manageStoresLink.click();

    }
    public boolean verifyViewAllStore(){
        return storeViewNameLink.isDisplayed();
    }
}

