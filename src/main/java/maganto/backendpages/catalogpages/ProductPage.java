package maganto.backendpages.catalogpages;

import com.github.javafaker.Faker;
import maganto.utility.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage  {

    // Add product

    WebDriver driver;


    @FindBy(xpath = "//span[text()='Catalog']")
    WebElement catalogLink;

    @FindBy(xpath = "//span[text()='Manage Products']")
    WebElement manageProductsLink;

    @FindBy(xpath = "(//span[text()='Add Product'])[1]")
    WebElement addProductLink;

    @FindBy(xpath = "(//span[.='Continue'])[2]")
    WebElement continueLink;

    @FindBy(id = "//div[@class='wrapper']//input[@id='name'] [@class=' required-entry input-text required-entry'][@type='text']")
    WebElement nameFiled;

    @FindBy(id = "textarea[id='description']")
    WebElement descriptionFiled;

    @FindBy(id = "textarea[id='short_description']")
    WebElement shortDescriptionFiled;

    @FindBy(id = "input[id='sku']")
    WebElement skuFiled;

    @FindBy(id = "input[id='weight']")
    WebElement weightFiled;

    @FindBy(id = "select[id='status']")
    WebElement statusDropdown;

    @FindBy(id = "select[id='visibility']")
    WebElement visibiltyDropdown;

    @FindBy(id = "//span[text()='Save']")
    WebElement saveButton;

    @FindBy(id = "input[id='price']")
    WebElement priceField;

    @FindBy(id = "select[id='tax_class_id']")
    WebElement taxClassField;


    @FindBy(id = "(//span[text()='Save'])[1]")
    WebElement savePriceButton;

    @FindBy(xpath = "//div[@class='wrapper']//span[.='The product has been saved.']")
    WebElement addProductSuccessMessage;

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
        //super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
        dashboardPage=new CatalogDashboardPage(driver);
    }
    public void userAddProduct(){
        testUtility.waitForElementPresent(catalogLink);
        catalogLink.click();
        testUtility.waitForElementPresent(manageProductsLink);
        manageProductsLink.click();
        testUtility.waitForElementPresent(addProductLink);
        addProductLink.click();
        testUtility.waitForElementPresent(continueLink);
        continueLink.click();
        nameFiled.sendKeys(generateProductName());
        descriptionFiled.sendKeys(generateDescription());
        shortDescriptionFiled.sendKeys(generateDescription());
        Select select=new Select(statusDropdown);
        select.selectByVisibleText("Enabled");
        Select select1=new Select(visibiltyDropdown);
        select1.selectByVisibleText("Catalog");
        saveButton.click();
        priceField.sendKeys(generatePrice());
        Select select2=new Select(taxClassField);
        select2.selectByVisibleText("None");
        savePriceButton.click();

    }
    public boolean verifyNewProductAdded() {
        testUtility.waitForElementPresent(addProductSuccessMessage);
        if (driver.getPageSource().contains(addProductSuccessMessage.getText())) ;
        System.out.println("The product has been saved.");
        return true;
    }

}
