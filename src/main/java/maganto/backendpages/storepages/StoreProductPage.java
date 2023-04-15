package maganto.backendpages.storepages;

import maganto.utility.TestUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class StoreProductPage {
    WebDriver driver;
    TestUtility utility;
    Actions actions;

    public StoreProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
        actions=new Actions(driver);
    }
    //add product
    @FindBy(xpath = "//button[@title='Add Product']//span[contains(text(),'Add Product')]")
    WebElement addProductButton;
    @FindBy(id = "attribute_set_id")
    WebElement attributeSetList;
    @FindBy(xpath = "//button[@title='Continue']//span[contains(text(),'Continue')]")
    WebElement continueButton;
    @FindBy(id = "name")
    WebElement nameField;
    @FindBy(id = "description")
    WebElement descriptionField;
    @FindBy(id = "short_description")
    WebElement shortDescriptionField;
    @FindBy(id = "sku")
    WebElement skuField;
    @FindBy(id = "weight")
    WebElement weightField;
    @FindBy(id = "status")
    WebElement statusList;
    @FindBy(id = "country_of_manufacture")
    WebElement countryOfManufactureList;
    @FindBy(xpath = "//a[@title='Prices']")
    WebElement pricesButton;
    @FindBy(id = "price")
    WebElement priceField;
    @FindBy(id = "tax_class_id")
    WebElement taxClassList;
    @FindBy(xpath = "//button[@title='Save']//span[contains(text(),'Save')]")
    WebElement saveButton;
    @FindBy(xpath = "//span[contains(text(),'The product has been saved.')]")
    WebElement confirmationMessageProductAdded;
    //update product
    @FindBy(id = "productGrid_product_filter_name")
    WebElement searchNameProductField;
    @FindBy(xpath = "//button[@title='Search']//span[contains(text(),'Search')]")
    WebElement searchButton;
    @FindBy(xpath = "//a[contains(text(),'Edit')]")
    WebElement editButton;
    //delete product
    @FindBy(xpath = "//input[@name='product']")
    WebElement checkBox;
    @FindBy(id = "productGrid_massaction-select")
    WebElement actionsDropDownList;
    @FindBy(xpath = "//button[@title='Submit']//span[contains(text(),'Submit')]")
    WebElement submitButton;
    @FindBy(xpath = "//div[@id='messages']//span[contains(text(),'Total of 1 record(s) have been deleted.')]")
    WebElement confirmationMessageProductDeleted;


    public void addProductsMethod(String name, String description, String shortDescription, String sku, String weight,String price){
        utility.waitForElementPresent(addProductButton);
        addProductButton.click();
        utility.waitForElementPresent(attributeSetList);
        Select select=new Select(attributeSetList);
        select.selectByValue("17");
        utility.sleep(1);
        utility.waitForElementPresent(continueButton);
        continueButton.click();
        utility.waitForElementPresent(nameField);
        nameField.sendKeys(name);
        utility.waitForElementPresent(descriptionField);
        descriptionField.sendKeys(description);
        utility.waitForElementPresent(shortDescriptionField);
        shortDescriptionField.sendKeys(shortDescription);
        utility.waitForElementPresent(skuField);
        skuField.sendKeys(sku);
        utility.waitForElementPresent(weightField);
        weightField.sendKeys(weight);
        Select select1=new Select(statusList);
        select1.selectByValue("1");
        Select select2=new Select(countryOfManufactureList);
        select2.selectByValue("IT");
        utility.sleep(1);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-250)");
        utility.sleep(1);
        utility.waitForElementPresent(pricesButton);
        pricesButton.click();
        utility.waitForElementPresent(priceField);
        priceField.sendKeys(price);
        Select select3=new Select(taxClassList);
        select3.selectByValue("11");
        utility.waitForElementPresent(saveButton);
        saveButton.click();
        utility.sleep(3);
    }
    public boolean confirmationProductAdded(){
        if (confirmationMessageProductAdded.isDisplayed())
            return true;
        else return false;
    }
    public void updateProductMethod(String name,String description){
        utility.waitForElementPresent(searchNameProductField);
        searchNameProductField.sendKeys(name);
        utility.waitForElementPresent(searchButton);
        searchButton.click();
        utility.sleep(4);
        utility.waitForElementPresent(editButton);
        editButton.click();
        utility.waitForElementPresent(descriptionField);
        descriptionField.sendKeys(description);
        utility.sleep(1);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,+250)");
        utility.sleep(1);
        Select select2=new Select(countryOfManufactureList);
        select2.selectByValue("TR");
        utility.sleep(1);
        utility.waitForElementPresent(saveButton);
        saveButton.click();
        utility.sleep(3);
    }
    public void deleteProductMethod(){
        utility.waitForElementPresent(checkBox);
        checkBox.click();
        Select select=new Select(actionsDropDownList);
        select.selectByValue("delete");
        utility.waitForElementPresent(submitButton);
        submitButton.click();
        utility.sleep(2);
        driver.switchTo().alert().accept();
    }
    public boolean confirmationProductDeleted(){
        if (confirmationMessageProductDeleted.isDisplayed())
            return true;
        else return false;
    }
}
