package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class StoreProductCategoriesPage {

    WebDriver driver;
    Actions actions;

    @FindBy(xpath = "//span[contains(text(),'Catalog')]")
    WebElement catalogLink;

    @FindBy(xpath = "//span[contains(text(),'Manage Categories')]")
    WebElement manageCategoriesLink;

    @FindBy(xpath = "//span[contains(text(),'Add Root Category')]")
    WebElement addRootCategoryLink;

    @FindBy(xpath = "//span[contains(text(),'Add Root Category')]")
    WebElement updateRootCategoryLink;

    @FindBy(id = "group_4name")
    WebElement nameField;

    @FindBy(id = "group_4is_active")
    WebElement activeFieldDropDown;

    @FindBy(id = "group_4include_in_menu")
    WebElement navigationMenuDropDown;

    @FindBy(xpath = "//span[contains(text(),'Save Category')]")
    WebElement saveCategoryButton;

    @FindBy(xpath = "//span[contains(text(),'The category has been saved.')]")
    WebElement saveSuccessfulMessage;

    @FindBy(xpath = "//span[text()='Delete Category']")
    WebElement deleteCategoryButton;

    @FindBy(xpath = "//span[contains(text(),'The category has been deleted.')]")
    WebElement deleteCategorySuccessfulMessage;

    TestUtility utility;

    String categoriesName;

    public StoreProductCategoriesPage(WebDriver driver) {
        this.driver = driver;
        utility = new TestUtility(driver);
        PageFactory.initElements(driver, this);
    }

    //add product category
    public void addProductCatalog() {

        utility.waitForElementPresent(catalogLink);
        catalogLink.click();
        utility.waitForElementPresent(manageCategoriesLink);
        manageCategoriesLink.click();
        utility.sleep(2);
        utility.waitForElementPresent(nameField);
        categoriesName= utility.generateRootCategory();
        nameField.sendKeys(categoriesName);
        //nameField.sendKeys(Name);
        Select select1 = new Select(activeFieldDropDown);
        select1.selectByValue("1");
        activeFieldDropDown.click();
        Select select2 = new Select(navigationMenuDropDown);
        select2.selectByValue("1");
        navigationMenuDropDown.click();
        saveCategoryButton.click();
    }

    public boolean verifyAddedProductCatalog() {
        utility.waitForElementPresent(saveSuccessfulMessage);
        if (saveSuccessfulMessage.isDisplayed()) {
            return true;
        } else
            return false;

    }

    //update product category

    public void updateProductCatalog() {

        Select select3 = new Select(activeFieldDropDown);
        select3.selectByValue("0");
        saveCategoryButton.click();
    }

    public boolean verifyUpdatedProductCatalog() {
        utility.waitForElementPresent(saveSuccessfulMessage);
        if (saveSuccessfulMessage.isDisplayed()) {
            return true;
        } else
            return false;
    }

    //delete product category

    public void deleteProductCatalog() {

        utility.waitForElementPresent(deleteCategoryButton);
        utility.sleep(2);
        deleteCategoryButton.click();
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }


    public boolean verifyDeletedProductCatalog() {
        utility.waitForElementPresent(deleteCategorySuccessfulMessage);
        if (deleteCategorySuccessfulMessage.isDisplayed()) {
            return true;
        } else
            return false;
    }
}


