package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreViewPage {

    //Store Manager can create a store view
    WebDriver driver;
    TestUtility testUtility;
    String name;
    String code;
    String sortOrder;

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
    WebElement nameField;
    @FindBy(id = "store_code")
    WebElement codeField;
    @FindBy(id = "store_is_active")
    WebElement statusDropDown;
    @FindBy(id = "store_sort_order")
    WebElement sortOrderField;
    @FindBy(xpath = "//body[1]/div[1]/div[3]/div[1]/div[2]/p[1]/button[3]/span[1]/span[1]/span[1]")
    WebElement saveStoreViewLink;
    @FindBy(xpath ="//span[contains(text(),'The store view has been saved')]")
    WebElement successMessage;




    public void createStoreView(){


    }



}
