package maganto.backendpages.catalogpages;

import com.github.javafaker.Faker;

import maganto.utility.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class ProductPage  {

    // Add product

    WebDriver driver;

    String productName;

    @FindBy(xpath = "//span[text()='Catalog']")
    public WebElement catalogLink;

    @FindBy(xpath = "//span[text()='Manage Products']")
    public WebElement manageProductsLink;

    @FindBy(xpath = "(//span[text()='Add Product'])[1]")
    public WebElement addProductLink;

    @FindBy(xpath = "(//span[.='Continue'])[2]")
    public WebElement continueLink;

    @FindBy(xpath = "//input[@id='name'] ")
     public WebElement nameFiled;

    @FindBy(id = "description")
    public WebElement descriptionFiled;

    @FindBy(id = "short_description")
   public WebElement shortDescriptionFiled;

    @FindBy(id = "sku")
    public WebElement skuFiled;

    @FindBy(id = "weight")
   public  WebElement weightFiled;

    @FindBy(id = "status")
    public WebElement statusDropdown;

    @FindBy(id = "visibility")
    public WebElement visibiltyDropdown;

    @FindBy(xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy(id ="price")
    public WebElement priceField;

    @FindBy(id = "tax_class_id")
   public WebElement taxClassField;


    @FindBy(xpath = "//span[text()='Save']")
    public WebElement savePriceButton;

    @FindBy(xpath = "//span[text()='The product has been saved.']")
    public WebElement addProductSuccessMessage;

    TestUtility testUtility;


    CatalogDashboardPage dashboardPage;
    Faker faker=new Faker();
    public String generateProductName(){
        String productName=faker.book().title();
        return productName;
    }
    public String generateShortDescription(){
        String productShortDescription=faker.educator().toString();
        return productShortDescription;
    }
    public String generatesku(){
        String productsku=faker.number().digit();
        return productsku;
    }
    public String generateweight(){
        String productweight=faker.number().digit();
        return productweight;
    }

    public String generateDescription(){
        String productDescription=faker.educator().toString();
        return productDescription;
    }

    public String generatePrice(){
        String productPrice=faker.number().digit();
        return productPrice;
    }

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        dashboardPage=new CatalogDashboardPage(driver);
    }
    public String  userAddProduct(){
        testUtility.waitForElementPresent(catalogLink);
        catalogLink.click();
        testUtility.waitForElementPresent(manageProductsLink);
        manageProductsLink.click();
        testUtility.waitForElementPresent(addProductLink);
        addProductLink.click();
        testUtility.waitForElementPresent(continueLink);
        continueLink.click();
        productName=generateProductName();
        nameFiled.sendKeys(productName);
        //nameFiled.sendKeys(productName);
        descriptionFiled.sendKeys(generateDescription());
        shortDescriptionFiled.sendKeys(generateShortDescription());
        skuFiled.sendKeys(generatesku()+System.currentTimeMillis());
        weightFiled.sendKeys(generateweight());

        Select select=new Select(statusDropdown);
        select.selectByVisibleText("Enabled");
        Select select1=new Select(visibiltyDropdown);
        select1.selectByVisibleText("Catalog");
        saveButton.click();
        priceField.sendKeys(generatePrice());
        Select select2=new Select(taxClassField);
        select2.selectByVisibleText("None");
        testUtility.waitForElementPresent(savePriceButton);
        savePriceButton.click();
        return productName;

    }
    public boolean verifyNewProductAdded() {
        testUtility.sleep(2);
        testUtility.waitForElementPresent(addProductSuccessMessage);
        if (addProductSuccessMessage.getText().contains("The product has been saved.")){
            System.out.println("The product has been added.");
            return true;
        }
        else
            return false;}



    //update product




    @FindBy(id = "name")
    WebElement updateProductNameField;
    @FindBy(xpath = "//span[text()='Save']")
    WebElement updateProductSaveButton;
    @FindBy(xpath = "//span[text()='The product has been saved.']")
    WebElement updatedProductSuccessMessage;




    public void updateProduct(){
        testUtility.waitForElementPresent(catalogLink);
        catalogLink.click();
        testUtility.waitForElementPresent(manageProductsLink);
        manageProductsLink.click();
        WebElement productButton = driver.findElement(By.xpath(String.format
                ("//table[@id=\"productGrid_table\"]//tbody/tr/td[contains(text(),' %s')]",productName)));
        testUtility.waitForElementPresent(productButton);
        productButton.click();
        updateProductNameField.sendKeys(generateProductName());
        updateProductSaveButton.click();
    }
    public boolean verifyUpdateProduct() {
        testUtility.sleep(2);
        testUtility.waitForElementPresent(updatedProductSuccessMessage);
        if (addProductSuccessMessage.getText().contains("The product has been saved.")) {
            System.out.println("The product has been added.");
            return true;
        }else
            return false;
    }

    //Delete Product

    @FindBy(xpath = "//span[text()='Delete']")
    WebElement deleteButton;
    @FindBy(xpath = "//span[text()='The product has been deleted.']")
    WebElement DeleteProductSuccessMessage;

    public void deleteProduct(){
        testUtility.waitForElementPresent(catalogLink);
        catalogLink.click();
        testUtility.waitForElementPresent(manageProductsLink);
        manageProductsLink.click();

        WebElement productButton = driver.findElement(By.xpath(String.format
                ("//table[@id=\"productGrid_table\"]//tbody/tr/td[contains(text(),' %s')]",productName)));
       testUtility.waitForElementPresent(productButton);
        productButton.click();
        deleteButton.click();
        testUtility.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean verifyDeleteProduct(){
        testUtility.sleep(2);
        testUtility.waitForElementPresent(DeleteProductSuccessMessage);
        if (DeleteProductSuccessMessage.getText().contains("The product has been deleted.")) {
            System.out.println("The product has been deleted..");
            return true;
        }else
            return false;



    }

}
