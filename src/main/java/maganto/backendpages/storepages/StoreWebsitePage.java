package maganto.backendpages.storepages;

import maganto.utility.DataHelper;
import maganto.utility.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreWebsitePage {
    WebDriver driver;
    TestUtility testUtility;

    public StoreWebsitePage(WebDriver driver) {
        this.driver = driver;
        testUtility=new TestUtility(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//span[text()='System']")
    WebElement SystemButton;
    @FindBy(xpath = "//span[text()='Manage Stores']")
    WebElement ManageStore;
    @FindBy (xpath = "//span[text()='Create Website']")
    WebElement CreateWepsite;
    @FindBy(id = "website_name")
    WebElement WepsiteNameFiled;
    @FindBy(id = "website_code")
    WebElement wepsiteCodeFiled;
    @FindBy(id = "website_sort_order")
    WebElement sortOrder;
    @FindBy(xpath = "//span[text()='Save Website']")
    WebElement SaveWebsite;
    @FindBy(xpath = "//span[text()='The website has been saved.']")
    WebElement verifywepsite;
    //edit website
    @FindBy(xpath = "//span[text()='System']")
    WebElement SystemButtonEdit;
    @FindBy(xpath = "//span[text()='Manage Stores']")
    WebElement ManageStoreEdit;
    @FindBy(id = "website_name")
    WebElement clickWepsiteName;
    @FindBy (xpath = "//span[contains(text(),'Save Website')]")
    WebElement editSaveButton;
    @FindBy(xpath = "//span[text()='The website has been saved.']")
    WebElement editVerifyMessage;
    //Deleted website

    @FindBy(xpath = "//span[text()='System']")
    WebElement SystemButtonDeleted;
    @FindBy(xpath = "//span[text()='Manage Stores']")
    WebElement ManageStoreDeleted;
    @FindBy(xpath = "//span[text()='Delete Website']")
    WebElement DeletedButton;
    @FindBy(xpath = "//span[text()='The website has been deleted.']")
    WebElement deletedMessage;
    String websiteName;
    String updatedWebsiteName;

    public String CreateNewWepsite(){

        testUtility.waitForElementPresent(SystemButton);
        SystemButton.click();
        testUtility.waitForElementPresent(ManageStore);
        ManageStore.click();
        testUtility.waitForElementPresent(CreateWepsite);
        CreateWepsite.click();
        websiteName=testUtility.generateCityName();
        testUtility.waitForElementPresent(WepsiteNameFiled);
        DataHelper.setWebsiteName(testUtility.generateCityName());
        WepsiteNameFiled.sendKeys(DataHelper.getWebsiteName());
        String websiteCode="t"+testUtility.generateNumber()+System.currentTimeMillis();
        testUtility.waitForElementPresent(wepsiteCodeFiled);
        wepsiteCodeFiled.sendKeys(websiteCode);
        testUtility.waitForElementPresent(sortOrder);
        sortOrder.sendKeys("67");
        testUtility.waitForElementPresent(SaveWebsite);
        SaveWebsite.click();
        return websiteName;
    }
    public boolean VerifySuccessfulMessage(){
        testUtility.waitForElementPresent(verifywepsite);
        if (verifywepsite.isDisplayed());
        return true;
    }
    public void editWepsiye(){
        testUtility.waitForElementPresent(SystemButtonEdit);
        SystemButtonEdit.click();
        testUtility.waitForElementPresent(ManageStoreEdit);
        ManageStore.click();
        WebElement addedwebsite=driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]",DataHelper.getWebsiteName())));
        testUtility.waitForElementPresent(addedwebsite);
        addedwebsite.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(clickWepsiteName);
        clickWepsiteName.clear();
        updatedWebsiteName=testUtility.generateCountryName();
        clickWepsiteName.sendKeys(updatedWebsiteName);
        testUtility.waitForElementPresent(editSaveButton);
        editSaveButton.click();
    }
    public boolean VerifyEditWepsiteMessage(){
        testUtility.waitForElementPresent(editVerifyMessage);
        if (editVerifyMessage.isDisplayed()){
            return true;
        }
        else return false;

    }

    public void DeletedWepsite(){

        testUtility.waitForElementPresent(SystemButtonDeleted);
        SystemButtonDeleted.click();
        testUtility.sleep(2);
        testUtility.waitForElementPresent(ManageStoreDeleted);
        ManageStoreDeleted.click();
        testUtility.sleep(2);
        WebElement addedwebsite=driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]",updatedWebsiteName)));
        testUtility.waitForElementPresent(addedwebsite);
        addedwebsite.click();
        testUtility.waitForElementPresent(DeletedButton);
        DeletedButton.click();
    }

    public boolean DeletedWepsiteMessage(){
        testUtility.waitForElementPresent(deletedMessage);
        System.out.println("The website has been deleted.");
        if (deletedMessage.isDisplayed());
        return true;
    }

}


