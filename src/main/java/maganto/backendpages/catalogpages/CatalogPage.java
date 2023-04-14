package maganto.backendpages.catalogpages;


import com.github.javafaker.Faker;
import maganto.utility.DataHelper;
import maganto.utility.TestUtility;

import org.openqa.selenium.Alert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;

public class CatalogPage {
    WebDriver driver;
    TestUtility utility;
    @FindBy(xpath ="//span[text()=\"Catalog\"]")
    WebElement catalogLink;

    @FindBy(xpath = "//span[text()=\"Manage Categories\"]")
    WebElement manageCategoriesLink;


    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

        utility =new TestUtility(driver);
    }

    // Add New SearchTerm
    @FindBy(xpath ="//span[text()='Catalog']" )
    WebElement CatalogButton;
    @FindBy(xpath = "//span[text()='Search Terms']")
    WebElement SearchTerm;
    @FindBy(xpath = "//span[text()='Add New Search Term']")
    WebElement addNewSearchTerm;
    @FindBy(id ="query_text")
    WebElement searchQueryField;
    @FindBy(id = "store_id")
   WebElement Store;
    @FindBy(id = "synonym_for")
    WebElement SynonymFor;
    @FindBy(id = "display_in_terms")
    WebElement DisPlayingSuggestedTerms;
    @FindBy(xpath = "//span[text()='Save Search']")
    WebElement SaveSearch;
    @FindBy(xpath = "//span[text()='You saved the search term.']")
    WebElement successfullyMessage;
    String searchName=null;

    // Edit Search Term

    @FindBy(xpath = "//input[@id='catalog_search_grid_filter_search_query']")
     WebElement queryField;
    @FindBy(css = ".scalable.task")
            WebElement searchButton;
    @FindBy(xpath ="//table[@id=\"catalog_search_grid_table\"]//tbody/tr/td[9]//a[.='Edit']" )
            WebElement editIcon;
    @FindBy(id = "catalog_search_grid_filter_search_query")
            WebElement editSearchQueryField;
    @FindBy(xpath ="//span[text()='Save Search']")
            WebElement editSaveButton;
    @FindBy(id = "catalog_search_grid_filter_search_query")
            WebElement SearchQueryFieldForEdit;
    String updatedSearchQuery;


    // Deleted Search Term

    @FindBy(xpath ="//span[text()='Catalog']" )
    WebElement CatalogDeleted;
    @FindBy(xpath = "//span[text()='Search Terms']")
            WebElement searchTermForDeleted;
    @FindBy(id = "catalog_search_grid_filter_search_query")
            WebElement query1;
    @FindBy(xpath = "(//span[text()='Search'])[1]")
            WebElement searchDeleted;
    @FindBy(xpath = "//tbody/tr/td[9]//a[.='Edit']")
            WebElement editIconDeleted;
    @FindBy(xpath = "//span[text()='Delete Search']")
            WebElement deleteSearch;

    Faker faker=new Faker();
    public String generateName(){
        String searchName=faker.name().title();
        return searchName;

    }
    public String generateNumber(){
        String searchNumber=faker.number().digit();
        return searchNumber;
    }
    public String generateSearchQueryName() {

        String searchQuery1=faker.book().title();
        return searchQuery1;

    }
    public void addNewSearchTerm1(){
        utility.sleep(1);
        utility.waitForElementPresent(CatalogButton);
        CatalogButton.click();
        utility.sleep(1);
        utility.waitForElementPresent(SearchTerm);
        SearchTerm.click();
       utility.sleep(1);
        utility.waitForElementPresent(addNewSearchTerm);
        addNewSearchTerm.click();
        utility.sleep(1);
        searchName=generateSearchQueryName();
        DataHelper.setQueryName(searchName);
        utility.waitForElementPresent(searchQueryField);
        searchQueryField.sendKeys(searchName);
        utility.sleep(1);
        utility.waitForElementPresent(Store);
        Select select=new Select(Store);
        select.selectByValue("159");
        Store.click();
        utility.sleep(1);
        utility.waitForElementPresent(SynonymFor);
        SynonymFor.sendKeys(generateNumber()+System.currentTimeMillis());
        Select select1=new Select(DisPlayingSuggestedTerms);
        select1.selectByValue("1");
        DisPlayingSuggestedTerms.click();
        SaveSearch.click();



    }
    public boolean verifySuccessfullyMessage(){

        utility.waitForElementPresent(successfullyMessage);
        if (successfullyMessage.isDisplayed());
        return true;
    }
    public void EditSearchTerm(){
        utility.sleep(2);
        utility.waitForElementPresent(SearchQueryFieldForEdit);
        SearchQueryFieldForEdit.sendKeys(DataHelper.getQueryName());
        utility.waitForElementPresent(searchButton);
        searchButton.click();
        utility.sleep(2);
        utility.waitForElementPresent(editIcon);
        editIcon.click();
        utility.sleep(1);
        utility.waitForElementPresent(searchQueryField);
        searchQueryField.clear();
        DataHelper.setQueryName1(utility.generateStateName()+System.currentTimeMillis());
        searchQueryField.sendKeys(DataHelper.getQueryName1());
        utility.sleep(1);
        utility.waitForElementPresent(editSaveButton);
        editSaveButton.click();



    }
    public boolean verifyYouSavedTheSearchTermMessage(){
        utility.waitForElementPresent(successfullyMessage);
        if (successfullyMessage.isDisplayed());
        return true;
    }
    public void deleteSearchTerm(){
        utility.sleep(2);
        utility.waitForElementPresent(SearchQueryFieldForEdit);
        SearchQueryFieldForEdit.clear();
        SearchQueryFieldForEdit.sendKeys(DataHelper.getQueryName1());
        utility.waitForElementPresent(searchButton);
        searchButton.click();
        utility.sleep(2);
        utility.waitForElementPresent(editIcon);
        editIcon.click();
        utility.sleep(1);
        utility.waitForElementPresent(deleteSearch);
        deleteSearch.click();
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }



  public void clickOnCatalogLink(){
        utility.waitForElementPresent(catalogLink);
        catalogLink.click();
    }

    public void clickOnManageCategoriesLink(){
        utility.waitForElementPresent(manageCategoriesLink);
        manageCategoriesLink.click();
    }

}


