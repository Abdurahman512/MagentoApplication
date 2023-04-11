package maganto.backendpages.catalogpages;

import maganto.utility.ExcelUtility;
import maganto.utility.TestUtility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CategoriesPage {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='Catalog']")
    WebElement catalogLink;
    @FindBy(xpath = "//span[text()='Manage Categories']")
    WebElement categoriesLink;
    @FindBy(id = "group_4name")
    WebElement CategoryNameField;
    @FindBy(xpath = "//span[text()='Save Category']")
    WebElement saveCategoryButton;
    @FindBy(xpath = "//span[text()='The category has been saved.']")
    WebElement categorySavedMessage;

    //sub category
    @FindBy(xpath = "//button[@title='Add Subcategory']")
    WebElement addSubCategoryLink;
    //edit category
    @FindBy(id = "group_4is_active")
    WebElement activeSelection;

    //delete category
    @FindBy(css = ".scalable.delete")
    WebElement deleteCategoryButton;
    @FindBy(xpath = "//span[text()='The category has been deleted.']")
    WebElement deleteCategoryMessage;

    @FindAll(
            @FindBy(xpath ="//ul[@class='x-tree-node-ct']//li")
    )
    List<WebElement> categories;

    @FindBy(xpath ="//span[text()='Category Products']")
    WebElement categoryProducts;

    @FindBy(id = "catalog_category_products_filter_name")
    WebElement categoryProductsNameFiled;

    @FindBy(id = "catalog_category_products_filter_price_from")
    WebElement categoryProductsPriceFiledFrom;

    @FindBy(id = "catalog_category_products_filter_price_to")
    WebElement categoryProductsPriceFiledTo;

    @FindBy(xpath = "//span[text()=\"Search\"]")
    WebElement searchButton;

    @FindAll(
            @FindBy(xpath = "//table[@class='data']//tbody//tr")
    )
    List<WebElement> verifyFilterProducts;
    TestUtility utility;
    CatalogDashboardPage dashboardPage;
    ExcelUtility excelUtility;
    String rootCategoryName;
    String subCategoryName;

    public CategoriesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        utility=new TestUtility(driver);
       excelUtility=new ExcelUtility();
       dashboardPage=new CatalogDashboardPage(driver);
    }

    public void addRootCategories(String fileName,String sheetName,int rowNo,int clmnNo){
        utility.waitForElementPresent(catalogLink);
        catalogLink.click();
        utility.waitForElementPresent(categoriesLink);
        categoriesLink.click();
        utility.sleep(2);
        utility.waitForElementPresent(CategoryNameField);
        rootCategoryName=excelUtility.readFromExcelCell(fileName,sheetName,rowNo,clmnNo);
        CategoryNameField.sendKeys(rootCategoryName);
        saveCategoryButton.click();
    }
    public boolean isRootCategoryAdded(){
        utility.waitForElementPresent(categorySavedMessage);
        if(categorySavedMessage.isDisplayed())
            return true;
        else return false;
    }

    public void addSubCategories(String fileName,String sheetName,int rowNo,int clmnNo){
        dashboardPage.clickDashboardPage();
        utility.waitForElementPresent(catalogLink);
        catalogLink.click();
        utility.waitForElementPresent(categoriesLink);
        categoriesLink.click();
        WebElement addedRootCategory= driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]",rootCategoryName)));
        utility.waitForElementPresent(addedRootCategory);
        addedRootCategory.click();
        utility.sleep(3);
        utility.waitForElementPresent(addSubCategoryLink);
        utility.javaScriptClick(addSubCategoryLink);
        utility.waitForElementPresent(CategoryNameField);
        subCategoryName=excelUtility.readFromExcelCell(fileName,sheetName,rowNo,clmnNo);
        utility.sleep(2);
        CategoryNameField.sendKeys(subCategoryName);
        saveCategoryButton.click();
    }
    public boolean isSubCategoryAdded(){
        utility.waitForElementPresent(categorySavedMessage);
        if(categorySavedMessage.isDisplayed())
            return true;
        else return false;
    }

    public void editRootCategory(String fileName,String sheetName,int rowNo,int clmnNo){
        WebElement addedRootCategory= driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]",rootCategoryName)));
        utility.waitForElementPresent(addedRootCategory);
        utility.sleep(2);
        addedRootCategory.click();
        utility.waitForElementPresent(activeSelection);
        Select select=new Select(activeSelection);
        String value=excelUtility.readFromExcelCell(fileName,sheetName,rowNo,clmnNo);
        utility.sleep(2);
        select.selectByVisibleText(value);
        saveCategoryButton.click();
    }
    public boolean isRootCategoryEdited(){
        utility.waitForElementPresent(categorySavedMessage);
        if(categorySavedMessage.isDisplayed())
            return true;
        else return false;
    }
    public void editSubCategory(String fileName,String sheetName,int rowNo,int clmnNo){
        WebElement addedSubCategory= driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]",subCategoryName)));
        utility.waitForElementPresent(addedSubCategory);
        utility.javaScriptClick(addedSubCategory);
        utility.waitForElementPresent(activeSelection);
        Select select=new Select(activeSelection);
        String value=excelUtility.readFromExcelCell(fileName,sheetName,rowNo,clmnNo);
        utility.sleep(2);
        select.selectByVisibleText(value);
        saveCategoryButton.click();
        utility.sleep(1);
    }
    public boolean isSubCategoryEdited(){
        utility.waitForElementPresent(categorySavedMessage);
        if(categorySavedMessage.isDisplayed())
            return true;
        else return false;
    }

    public void deleteSubCategory(){
        WebElement addedSubCategory= driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]",subCategoryName)));
        utility.waitForElementPresent(addedSubCategory);
        addedSubCategory.click();
        utility.waitForElementPresent(deleteCategoryButton);
        utility.javaScriptClick(deleteCategoryButton);
        utility.sleep(2);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isSubCategoryDeleted(){
        utility.waitForElementPresent(deleteCategoryMessage);
        if(deleteCategoryMessage.isDisplayed())
            return true;
        else return false;
    }

    public void deleteRootCategory(){
        WebElement addedRootCategory= driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]",rootCategoryName)));
        utility.waitForElementPresent(addedRootCategory);
        addedRootCategory.click();
        utility.waitForElementPresent(deleteCategoryButton);
        utility.javaScriptClick(deleteCategoryButton);
        utility.sleep(2);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isRootCategoryDeleted(){
        utility.waitForElementPresent(deleteCategoryMessage);
        if(deleteCategoryMessage.isDisplayed())
            return true;
        else return false;
    }

    public boolean viewAllCategories(){
        dashboardPage.clickOnCatalogLink();
        dashboardPage.clickOnManageCategoriesLink();
        return categories.size()>=1;
    }

    public void filterProducts(String productName,String priceFrome,String priceTo){
        catalogLink.click();
        categoriesLink.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        utility.javaScriptClick(categoryProducts);
        ///categoryProducts.click();
        categoryProductsNameFiled.sendKeys(productName);
        categoryProductsPriceFiledFrom.sendKeys(priceFrome);
        categoryProductsPriceFiledTo.sendKeys(priceTo);
        searchButton.click();
    }

    public boolean verifyFilter(){
        return verifyFilterProducts.size()>=2;
    }




}
